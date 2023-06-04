package edu.poniperro.nowait.core.profile.user.application.login;

public class UserResponse {
    private String email;
    private String token;

    // Constructor
    public UserResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }

    // Getters y setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

