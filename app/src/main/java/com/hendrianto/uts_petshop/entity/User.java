package com.hendrianto.uts_petshop.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.hendrianto.uts_petshop.BR;

public class User extends BaseObservable {
    private String username;
    private String email;
    private String telphone;
    private String address;

    public User(){

    }

    public User(String username, String email, String telphone, String address){
        this.username = username;
        this.email = email;
        this.telphone = telphone;
        this.address = address;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
        notifyPropertyChanged(BR.telphone);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }
}
