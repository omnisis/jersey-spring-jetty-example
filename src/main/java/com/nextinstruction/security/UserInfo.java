package com.nextinstruction.security;

import java.security.Principal;

public class UserInfo implements Principal {
    private final String name;
    private final String role;
    private final int accessLevel;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getAccessLevel() { return accessLevel; }

    public UserInfo(String name, String role, int accessLevel) {
        this.name = name;
        this.role = role;
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", accessLevel=").append(accessLevel);
        sb.append('}');
        return sb.toString();
    }
}
