package com.example.gads_lbd.services;

import com.example.gads_lbd.models.Submission;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SubmitService {

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Submission> submitProject(@Body Submission newSubmission);

}
