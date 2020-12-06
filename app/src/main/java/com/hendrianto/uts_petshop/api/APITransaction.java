package com.hendrianto.uts_petshop.api;

public class APITransaction {
    public static final String ROOT_URL   = "https://pbp.inipunyaku.my.id/public/";
    public static final String ROOT_API   = ROOT_URL+ "api/";
    //Tambahkan api buku disini
    public static final String URL_ADD       = ROOT_API+"transaction"; //POST
    public static final String URL_UPDATE    = ROOT_API+"transaction/"; //PUT
    public static final String URL_DELETE    = ROOT_API+"transaction/"; //DELETE
    public static final String URL_SELECT    = ROOT_API+"transaction"; //GET
    public static final String URL_SELECTBYID   = ROOT_API+"transaction/"; //GET
}
