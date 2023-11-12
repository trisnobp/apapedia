package com.apapedia.user.model;

public enum Role {
    CUSTOMER("CUSTOMER"),
    SELLER("SELLER");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

