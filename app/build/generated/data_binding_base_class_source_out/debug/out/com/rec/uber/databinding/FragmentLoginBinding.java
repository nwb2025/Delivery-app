// Generated by view binder compiler. Do not edit!
package com.rec.uber.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputLayout;
import com.rec.uber.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText email;

  @NonNull
  public final ConstraintLayout layout;

  @NonNull
  public final Button login;

  @NonNull
  public final EditText password;

  @NonNull
  public final TextInputLayout tlEmail;

  @NonNull
  public final TextInputLayout tlPassword;

  @NonNull
  public final TextView tvForgotPassword;

  @NonNull
  public final TextView tvHaveAnAccount;

  @NonNull
  public final TextView tvRegister;

  @NonNull
  public final TextView tvTitle;

  @NonNull
  public final View vBackground;

  private FragmentLoginBinding(@NonNull ConstraintLayout rootView, @NonNull EditText email,
      @NonNull ConstraintLayout layout, @NonNull Button login, @NonNull EditText password,
      @NonNull TextInputLayout tlEmail, @NonNull TextInputLayout tlPassword,
      @NonNull TextView tvForgotPassword, @NonNull TextView tvHaveAnAccount,
      @NonNull TextView tvRegister, @NonNull TextView tvTitle, @NonNull View vBackground) {
    this.rootView = rootView;
    this.email = email;
    this.layout = layout;
    this.login = login;
    this.password = password;
    this.tlEmail = tlEmail;
    this.tlPassword = tlPassword;
    this.tvForgotPassword = tvForgotPassword;
    this.tvHaveAnAccount = tvHaveAnAccount;
    this.tvRegister = tvRegister;
    this.tvTitle = tvTitle;
    this.vBackground = vBackground;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.email;
      EditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      ConstraintLayout layout = (ConstraintLayout) rootView;

      id = R.id.login;
      Button login = ViewBindings.findChildViewById(rootView, id);
      if (login == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.tlEmail;
      TextInputLayout tlEmail = ViewBindings.findChildViewById(rootView, id);
      if (tlEmail == null) {
        break missingId;
      }

      id = R.id.tlPassword;
      TextInputLayout tlPassword = ViewBindings.findChildViewById(rootView, id);
      if (tlPassword == null) {
        break missingId;
      }

      id = R.id.tvForgotPassword;
      TextView tvForgotPassword = ViewBindings.findChildViewById(rootView, id);
      if (tvForgotPassword == null) {
        break missingId;
      }

      id = R.id.tvHaveAnAccount;
      TextView tvHaveAnAccount = ViewBindings.findChildViewById(rootView, id);
      if (tvHaveAnAccount == null) {
        break missingId;
      }

      id = R.id.tvRegister;
      TextView tvRegister = ViewBindings.findChildViewById(rootView, id);
      if (tvRegister == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      id = R.id.vBackground;
      View vBackground = ViewBindings.findChildViewById(rootView, id);
      if (vBackground == null) {
        break missingId;
      }

      return new FragmentLoginBinding((ConstraintLayout) rootView, email, layout, login, password,
          tlEmail, tlPassword, tvForgotPassword, tvHaveAnAccount, tvRegister, tvTitle, vBackground);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
