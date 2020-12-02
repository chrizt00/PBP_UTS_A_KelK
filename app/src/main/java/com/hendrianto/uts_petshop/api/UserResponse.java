package com.hendrianto.uts_petshop.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hendrianto.uts_petshop.entity.Users;

import java.util.List;

public class UserResponse {
    @SerializedName("data")
    @Expose
    private List<Users> users = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<Users> getUsers(){
        return users;
    }

    public String getMessage(){
        return message;
    }

    public void setUsers(List<Users> users){
        this.users = users;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
