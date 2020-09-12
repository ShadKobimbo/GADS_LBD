package com.example.gads_lbd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SubmissionActivity extends AppCompatActivity {

    static Context context = null;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
    }

    public void back(View view){

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }

    public void openAreYouSureDialog() {
        dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.dialog_confirm);
        dialog.setTitle(R.string.user_name);
        dialog.show();
    }
    public void openSuccessDialog() {
        dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.dialog_success);
        dialog.setTitle(R.string.user_name);
        dialog.show();
    }
    public void openFailedDialog() {
        dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.dialog_failed);
        dialog.setTitle(R.string.user_name);
        dialog.show();
    }

    public void cancel(View view) {
        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_LONG).show();
        dialog.cancel();
    }
}