package com;

/**
 * Created by 啊Q on 2018/4/2.
 */
public class Admin {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    private String id;
    private String username;
    private String userpwd;

    @Override
    public String toString() {
        String a = " id = " + id + " name = " + username + " pwd = " + userpwd;
        return a;
    }
}
