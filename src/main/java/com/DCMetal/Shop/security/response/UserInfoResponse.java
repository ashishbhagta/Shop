package com.DCMetal.Shop.security.response;

import java.util.List;

public class UserInfoResponse {
    private Long id;
    private String jwtCookie;
    private String username;
    private List<String> roles;

    public UserInfoResponse(Long id, String username, List<String> roles, String jwtCookie) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.jwtCookie = jwtCookie;
    }

    public UserInfoResponse(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJwtCookie() {
        return jwtCookie;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtCookie = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

