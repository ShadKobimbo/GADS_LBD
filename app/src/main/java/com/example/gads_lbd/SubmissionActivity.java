package com.example.gads_lbd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gads_lbd.models.Submission;
import com.example.gads_lbd.services.ServiceBuilder;
import com.example.gads_lbd.services.SubmitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionActivity extends AppCompatActivity {

    static Context context = null;
    Dialog dialog;
    Button submit;

    EditText firstName,lastName,emailAddress,githubLink;

    private static final String BASE_URL = "https://docs.google.com/forms/d/e/";

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
                openAreYouSureDialog();
            }
        });
    }

    public void trySubmit(){

        Submission newSubmission = new Submission();
        newSubmission.setFirstname(firstName.getText().toString());
        newSubmission.setSecondname(lastName.getText().toString());
        newSubmission.setEmail(emailAddress.getText().toString());
        newSubmission.setGithublink(githubLink.getText().toString());

        SubmitService submitService = ServiceBuilder.buildService(SubmitService.class);
        Call<Submission> call = submitService.submitProject(newSubmission);

        call.enqueue(new Callback<Submission>() {
            @Override
            public void onResponse(Call<Submission> request, Response<Submission> response) {
                openSuccessDialog();
            }

            @Override
            public void onFailure(Call<Submission> request, Throwable t) {
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

    public void cancel(View view) {
        dialog.cancel();
    }
}