// Generated by view binder compiler. Do not edit!
package com.rec.uber.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.rec.uber.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailsBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout layout;

  @NonNull
  public final SegmentedButtonGroup radioRealButtonGroup;

  @NonNull
  public final Button register;

  private ActivityDetailsBinding(@NonNull LinearLayout rootView, @NonNull LinearLayout layout,
      @NonNull SegmentedButtonGroup radioRealButtonGroup, @NonNull Button register) {
    this.rootView = rootView;
    this.layout = layout;
    this.radioRealButtonGroup = radioRealButtonGroup;
    this.register = register;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      LinearLayout layout = (LinearLayout) rootView;

      id = R.id.radioRealButtonGroup;
      SegmentedButtonGroup radioRealButtonGroup = ViewBindings.findChildViewById(rootView, id);
      if (radioRealButtonGroup == null) {
        break missingId;
      }

      id = R.id.register;
      Button register = ViewBindings.findChildViewById(rootView, id);
      if (register == null) {
        break missingId;
      }

      return new ActivityDetailsBinding((LinearLayout) rootView, layout, radioRealButtonGroup,
          register);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}