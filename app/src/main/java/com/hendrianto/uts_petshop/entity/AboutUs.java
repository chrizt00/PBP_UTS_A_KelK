package com.hendrianto.uts_petshop.entity;

public class AboutUs {
    private String sosmed;
    private String email;
    private String phone;
    private String location;

    public AboutUs(String sosmed, String email, String phone, String location){
        this.setSosmed(sosmed);
        this.setEmail(email);
        this.setPhone(phone);
        this.setLocation(location);
    }

    public String getSosmed() {
        return sosmed;
    }

    public void setSosmed(String sosmed) {
        this.sosmed = sosmed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
