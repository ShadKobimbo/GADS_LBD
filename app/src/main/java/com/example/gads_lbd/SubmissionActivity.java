package com.example.gads_lbd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gads_lbd.models.Submission;
import com.example.gads_lbd.services.ServiceBuilder;
import com.example.gads_lbd.services.SubmitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;

    static Context context = null;
    Dialog dialog;
    Button submit;

    EditText firstName,lastName,emailAddress,githubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        context = this;
        submit = (Button) findViewById(R.id.submit_project);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        emailAddress = findViewById(R.id.email_address);
        githubLink = findViewById(R.id.github_link);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( TextUtils.isEmpty(firstName.getText())){
                    firstName.setError( "Enter First Name" );
                }
                if( TextUtils.isEmpty(lastName.getText())){
                    lastName.setError( "Enter Last Name" );
                }
                if( TextUtils.isEmpty(emailAddress.getText())){
                    emailAddress.setError( "Enter Email Address" );
                }
                if( TextUtils.isEmpty(githubLink.getText())){
                    githubLink.setError( "Enter GitHub Link" );
                }
                else {
                    openAreYouSureDialog();
                }
            }
        });
    }

    public void trySubmit(View view){

        cancel();

        String first_name = firstName.getText().toString();
        String last_name = lastName.getText().toString();
        String email_address = emailAddress.getText().toString();
        String github_link = githubLink.getText().toString();

        SubmitService submitService = ServiceBuilder.buildService(SubmitService.class);
        Call<Void> call = submitService.submitProject(first_name, last_name, email_address, github_link);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> request, Response<Void> response) {
                if (response.isSuccessful()) {
                    openSuccessDialog();

                    System.out.println(response.message().toString());

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(SubmissionActivity.this, HomeActivity.class);
                            SubmissionActivity.this.startActivity(i);
                            SubmissionActivity.this.finish();
                        }
                    }, SPLASH_TIME_OUT);

                } else {
                    openFailedDialog();
                }
            }

            @Override
            public void onFailure(Call<Void> request, Throwable t) {
                openFailedDialog();
            }
        });
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

    public void cancelDialog(View view) {
        dialog.cancel();
    }
    public void cancel() {
        dialog.cancel();
    }
}