package com.rec.go.ui.login;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.rec.uber.R;

import org.jetbrains.annotations.NotNull;

/**
 * Fragment Responsible for Logging in an existing user
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister, tvForgotPassword;

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_login, container, false);
        else
            container.removeView(view);

        return view;
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeViews();
    }

    /**
     * Sends an email to the email that's on the email input for the user to reset the password
     */
    /*private void forgotPassword() {
        if (etEmail.getText().toString().trim().length() > 0)
            FirebaseAuth.getInstance().sendPasswordResetEmail(etEmail.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Snackbar.make(view.findViewById(R.id.layout), "Email Sent to the following address " + etEmail.getText().toString(), Snackbar.LENGTH_LONG).show();
                        } else
                            Snackbar.make(view.findViewById(R.id.layout), "Something went wrong", Snackbar.LENGTH_LONG).show();
                    });
        else {
            Toast.makeText(getContext(), "Please provide your email address", Toast.LENGTH_SHORT).show();
        }
    }*/

    /**
     * Logs in the user
     */
    private void login() {
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        if(etEmail.getText().length() == 0) {
            etEmail.setError("Please fill this field");
            return;
        }
        if(etPassword.getText().length() == 0) {
            etPassword.setError("Please fill this field");
            return;
        }

        if(etPassword.getText().length() < 8) {
            etPassword.setError("Password has to be at least 8 characters");
            return;
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), task -> {
            if (!task.isSuccessful()) {
                Snackbar.make(view.findViewById(R.id.layout), "sign in error", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvForgotPassword:
                //forgotPassword();
                break;
            case R.id.login:
                login();
                break;
            case R.id.tvRegister:
                ((AuthenticationActivity) getActivity()).registrationClick();
                break;
        }
    }

    private void initializeViews() {
        etEmail = view.findViewById(R.id.email);
        etPassword = view.findViewById(R.id.password);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        btnLogin = view.findViewById(R.id.login);
        tvRegister = view.findViewById(R.id.tvRegister);

        tvForgotPassword.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }
}