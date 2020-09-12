package com.example.gads_lbd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubmissionActivity extends AppCompatActivity {

    static Context context = null;
    Dialog dialog;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        context = this;
        submit = (Button) findViewById(R.id.submit_project);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAreYouSureDialog();
            }
        });
    }

    public void trySubmit(){


    }

    public void back(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }

    public void openAreYouSureDialog() {
        dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.dialog_confirm);
        dialog.show();
    }
    public void openSuccessDialog() {
        dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.dialog_success);
        dialog.show();
    }
    public void openFailedDialog() {
        dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.dialog_failed);
        dialog.show();
    }

    public void cancel(View view) {
        dialog.cancel();
    }
}