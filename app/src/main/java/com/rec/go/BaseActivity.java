package com.rec.go;

import android.app.Dialog;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.rec.uber.R;

public class BaseActivity extends AppCompatActivity {
    private Dialog progressDialog;

    private void showProgressDialog(String text) {
        progressDialog = new Dialog(this);
        progressDialog.setContentView(R.layout.progress_dialog);
        TextView textView = progressDialog.findViewById(R.id.tvProgressText);
        textView.setText(text);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    private void hideProgressDialog() {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
        else {
            Log.d("BaseActivity ", "Progess dialog wasn't initialized ");
        }
    }


}

