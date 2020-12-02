package com.hendrianto.uts_petshop.entity;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("nama")
    private String nama;

    @SerializedName("telp")
    private String telp;

    @SerializedName("verifCode")
    private String verifCode;

    @SerializedName("isVerified")
    private int isVerified;

    public Users(int id, String email,String password, String nama, String telp, String verifCode,int isVerified){
        this.id = id;
        this.email = email;
        this.password = password;
        this.nama = nama;
        this.telp = telp;
        this.verifCode = verifCode;
        this.isVerified = isVerified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getVerifCode() {
        return verifCode;
    }

    public void setVerifCode(String verifCode) {
        this.verifCode = verifCode;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
