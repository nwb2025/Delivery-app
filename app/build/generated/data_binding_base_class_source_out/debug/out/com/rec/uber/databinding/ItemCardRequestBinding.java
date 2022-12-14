// Generated by view binder compiler. Do not edit!
package com.rec.uber.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.rec.uber.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemCardRequestBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CircularProgressBar circularProgressBar;

  @NonNull
  public final TextView distance;

  @NonNull
  public final ImageView image;

  @NonNull
  public final TextView ratingText;

  @NonNull
  public final TextView time;

  private ItemCardRequestBinding(@NonNull LinearLayout rootView,
      @NonNull CircularProgressBar circularProgressBar, @NonNull TextView distance,
      @NonNull ImageView image, @NonNull TextView ratingText, @NonNull TextView time) {
    this.rootView = rootView;
    this.circularProgressBar = circularProgressBar;
    this.distance = distance;
    this.image = image;
    this.ratingText = ratingText;
    this.time = time;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemCardRequestBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemCardRequestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item__card_request, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemCardRequestBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.circularProgressBar;
      CircularProgressBar circularProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (circularProgressBar == null) {
        break missingId;
      }

      id = R.id.distance;
      TextView distance = ViewBindings.findChildViewById(rootView, id);
      if (distance == null) {
        break missingId;
      }

      id = R.id.image;
      ImageView image = ViewBindings.findChildViewById(rootView, id);
      if (image == null) {
        break missingId;
      }

      id = R.id.ratingText;
      TextView ratingText = ViewBindings.findChildViewById(rootView, id);
      if (ratingText == null) {
        break missingId;
      }

      id = R.id.time;
      TextView time = ViewBindings.findChildViewById(rootView, id);
      if (time == null) {
        break missingId;
      }

      return new ItemCardRequestBinding((LinearLayout) rootView, circularProgressBar, distance,
          image, ratingText, time);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
