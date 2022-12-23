package com.rec.go.ui.login;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rec.go.models.User;
import com.rec.uber.R;

import org.jetbrains.annotations.NotNull;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText etEmail, etPassword, etConfPassword, etFistName, etLastName;
    private Button btnRegister;
    private TextView tvLogin;
    private AppCompatCheckBox cbTerms;
    private static String  TAG = "RegisterActivityTagging";

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_registration, container, false);
        else
            container.removeView(view);

        initViews(view);

        return view;
    }
    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Register the user, but before that check if every field is correct.
     * After that registers the user and creates an entry for it oin the database
     */

    // convert kotlin code to java code
    // register and valid

   /* private void register(){
        if(mEmail.getText().length() == 0) {
            mEmail.setError("please fill this field");
            return;
        }
        if(mPassword.getText().length() == 0) {
            mPassword.setError("please fill this field");
            return;
        }
        if(mPassword.getText().length() < 6) {
            mPassword.setError("password must have at least 6 characters");
            return;
        }

        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), task -> {
            if(!task.isSuccessful()){
                Snackbar.make(view.findViewById(R.id.layout), "sign up error", Snackbar.LENGTH_SHORT).show();
            }
        });
    }*/

    private void  registerUser() {
        if (isValidInputData()) {
            // showProgressDialog("");
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String firstName = etFistName.getText().toString();
            String lastName = etLastName.getText().toString();

            // Create an instance of Firebase
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), task -> {
                if(task.isSuccessful()) {
                    FirebaseUser firebaseUser = task.getResult().getUser();

                    User user = new User(
                            firebaseUser.getUid(),
                            firstName,
                            lastName,
                            email
                    );

                } else{
                    Log.d(TAG, "Unsuccessful registering: ${task.exception!!.message.toString()}");
                    Snackbar.make(view.findViewById(R.id.layout), "Sign up error  ${task.exception!!.message.toString()}", Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }

    private Boolean isValidInputData() {

        if (TextUtils.isEmpty(etFistName.getText().toString())) {
            etFistName.setError("Please provide your first name");
          // Toast.makeText(getContext(), "Please provide your first name", Toast.LENGTH_SHORT).show();
           return false;
        }
        if (TextUtils.isEmpty(etLastName.getText().toString()))  {
            etLastName.setError("Please provide your last name");
            //Toast.makeText(getContext(), "Please provide your last name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(etFistName.getText().toString())) {
            etFistName.setError("Please provide your first name");
            //Toast.makeText(getContext(), "Please provide your first name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError("Please provide your email address");
            //Toast.makeText(getContext(), "Please provide an email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("Please provide password");
            //Toast.makeText(getContext(), "Please provide a password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPassword.getText().toString().length() < 8) {
            etPassword.setError("Password should be at least 8 characters");
            //Toast.makeText(getContext(), "Please provide a password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etConfPassword.getText().toString()) ) {
            etConfPassword.setError("Please provide the second password");
            //Toast.makeText(getContext(), "Please provide the second password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!etPassword.getText().toString().equals(etConfPassword.getText().toString())) {
            Toast.makeText(getContext(), "Passwords don't match " + etPassword.getText().toString() + " " + etConfPassword.getText().toString() , android.widget.Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!cbTerms.isChecked()) {
            cbTerms.setError("Please accept terms and conditions");
            //Toast.makeText(getContext(), "Please accept terms and conditions", android.widget.Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view != null) {
            switch(view.getId()){
                case R.id.btnRegister:
                    registerUser();
                    break;

                case R.id.tvLogin:
                    ((AuthenticationActivity) getActivity()).loginClick();
                    break;
            }
        }
    }

    private void initViews(View view) {
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etConfPassword = view.findViewById(R.id.etConfpassword);
        etFistName = view.findViewById(R.id.etFname);
        etLastName = view.findViewById(R.id.etLname);
        cbTerms = view.findViewById(R.id.cbTerms);
        tvLogin = view.findViewById(R.id.tvLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        // Click assigned to the views
        tvLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }
}