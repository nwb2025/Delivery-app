package com.rec.go.ui.login;

import android.content.Intent;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;
import com.rec.go.ui.customer.CustomerMapActivity;
import com.rec.go.ui.driver.DriverMapActivity;
import com.rec.uber.R;
import com.stripe.android.PaymentConfiguration;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * This Activity controls the display of auth fragments of the app:
 * -MenuFragment
 * -LoginFragment
 * -RegisterFragment
 * <p>
 * It is also responsible for taking the user to the main activity if the login or register process is successful
 */
public class AuthenticationActivity extends AppCompatActivity {

    FragmentManager fm = getSupportFragmentManager();

    MenuFragment menuFragment = new MenuFragment();

    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        //Listens for changes in the auth state
        firebaseAuthListener = firebaseAuth -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(AuthenticationActivity.this, LauncherActivity.class);
                startActivity(intent);
                finish();
            }
        };

        fm.beginTransaction()
                .replace(R.id.container, menuFragment, "StartFragment")
                .addToBackStack(null)
                .commit();
    }

    /**
     * Displays the RegisterFragment
     */
    public void registrationClick() {
        fm.beginTransaction()
                .replace(R.id.container, new RegisterFragment(), "RegisterFragment")
                .addToBackStack(null)
                .commit();
    }

    /**
     * Displays the LoginFragment
     */
    public void loginClick() {
        fm.beginTransaction()
                .replace(R.id.container, new LoginFragment(), "LoginFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(firebaseAuthListener);
    }

    /**
     * Fragment Responsible for registering a new user
     */
    public static class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

        private SegmentedButtonGroup mRadioGroup;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_details);
            initializeObjects();
        }


        /**
         * Register the user, but before that check if every field is correct.
         * After that registers the user and creates an entry for it oin the database
         */
        private void register() {
            final String accountType;
            int selectId = mRadioGroup.getPosition();

            if (selectId == 1) {
                accountType = "Drivers";
            } else {
                accountType = "Customers";
            }

            String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Map<String, Object> newUserMap = new HashMap<>();
            newUserMap.put("name", ""); // TODO: mock name
            newUserMap.put("profileImageUrl", "default");
            if (accountType.equals("Drivers")) {
                newUserMap.put("service", "type_1");
                newUserMap.put("activated", true);
            }
            FirebaseDatabase.getInstance().getReference().child("Users").child(accountType).child(user_id).updateChildren(newUserMap).addOnCompleteListener((OnCompleteListener<Void>) task -> {
                Intent intent = new Intent(DetailsActivity.this, LauncherActivity.class);
                startActivity(intent);
                finish();
            });
        }

        /**
         * Initializes the design Elements and calls clickListeners for them
         */
        private void initializeObjects() {
            Button mRegister = findViewById(R.id.register);
            mRadioGroup = findViewById(R.id.radioRealButtonGroup);

            mRadioGroup.setPosition(0, false);
            mRegister.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.register) {
                register();
            }
        }
    }

    /**
     * First activity of the app.
     * <p>
     * Responsible for checking if the user is logged in or not and call
     * the AuthenticationActivity or MainActivity depending on that.
     */
    public static class LauncherActivity extends AppCompatActivity {
        int triedTypes = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            if (mAuth.getCurrentUser() != null) {
                checkUserAccType();
            } else {
                Intent intent = new Intent(LauncherActivity.this, AuthenticationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }

        /**
         * Check user account type, either customer or driver.
         * If it doesn't have a type then start the DetailsActivity for the
         * user to be able to pick one.
         */
        private void checkUserAccType() {
            String userID;

            userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID);
            mCustomerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                        startApis("Customers");
                        Intent intent = new Intent(getApplication(), CustomerMapActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        checkNoType();
                    }
                }

                @Override
                public void onCancelled(@NotNull DatabaseError databaseError) {
                }
            });
            DatabaseReference mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(userID);
            mDriverDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                        startApis("Drivers");
                        Intent intent = new Intent(getApplication(), DriverMapActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        checkNoType();
                    }
                }

                @Override
                public void onCancelled(@NotNull DatabaseError databaseError) {
                }
            });
        }

        /**
         * checks if both types have not been fetched meaning the DetailsActivity must be called
         */
        void checkNoType() {
            triedTypes++;
            if (triedTypes == 2) {
                Intent intent = new Intent(getApplication(), DetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }

        /**
         * starts up onesignal and stripe apis
         * @param type - type of the user (customer, driver)
         */
        void startApis(String type) {
            OneSignal.initWithContext(this);
            OneSignal.sendTag("User_ID", FirebaseAuth.getInstance().getCurrentUser().getUid());
            OneSignal.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            OSDeviceState deviceState = OneSignal.getDeviceState();
            String userId = deviceState != null ? deviceState.getUserId() : null;
            //OneSignal.setInFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification);
            FirebaseDatabase.getInstance().getReference().child("Users").child(type).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("notificationKey").setValue(userId);
            PaymentConfiguration.init(
                    getApplicationContext(),
                    getResources().getString(R.string.publishablekey)
            );
        }
    }
}
