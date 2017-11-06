package com.example.ruslanio.experienceexchange.network.body;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public class RegistrationBody {
    private String login;
    private String password;
    private String email;
    private String firstname;
    private String lastname;

    public RegistrationBody() {
    }

    public RegistrationBody(String login, String password, String email, String firstname, String lastname) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
