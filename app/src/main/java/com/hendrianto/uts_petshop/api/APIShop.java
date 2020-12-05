package com.hendrianto.uts_petshop.api;

public class APIShop {
    public static final String ROOT_URL   = "https://pbp.inipunyaku.my.id/public/";
    public static final String ROOT_API   = ROOT_URL+ "api/";
    //    public static final String URL_LOGIN  = ROOT_URL+"pet/";
    //Tambahkan api buku disini
    public static final String URL_ADD       = ROOT_API+"pet"; //POST
    public static final String URL_UPDATE    = ROOT_API+"pet/"; //PUT
    public static final String URL_DELETE    = ROOT_API+"pet/"; //DELETE
    public static final String URL_SELECT    = ROOT_API+"pet"; //GET
    public static final String URL_SELECTBYID   = ROOT_API+"pet/"; //GET
}
