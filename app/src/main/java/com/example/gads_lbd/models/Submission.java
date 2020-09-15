package com.example.gads_lbd.models;

public class Submission {

    private int id;
    private String firstname;
    private String secondname;
    private String email;
    private String githublink;

    public Submission() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getEmail() {
        return email;
    }

    public String getGithublink() {
        return githublink;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGithublink(String githublink) {
        this.githublink = githublink;
    }
}
