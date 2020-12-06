package com.hendrianto.uts_petshop.api;

public class APICart {
    public static final String ROOT_URL   = "https://pbp.inipunyaku.my.id/public/";
    public static final String ROOT_API   = ROOT_URL+ "api/";
    //    public static final String URL_LOGIN  = ROOT_URL+"cart/";
    //Tambahkan api buku disini
    public static final String URL_ADD       = ROOT_API+"cart"; //POST
    public static final String URL_UPDATE    = ROOT_API+"cart/"; //PUT
    public static final String URL_DELETE    = ROOT_API+"cart"; //DELETE
    public static final String URL_SELECT    = ROOT_API+"cart"; //GET
    public static final String URL_SELECTBYID   = ROOT_API+"cart/"; //GET
}
