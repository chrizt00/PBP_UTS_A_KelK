package com.hendrianto.uts_petshop.list;

import com.hendrianto.uts_petshop.entity.AboutUs;

import java.util.ArrayList;

public class ListAboutUs {
    public ArrayList<AboutUs> AboutUs;
    public ListAboutUs(){
        AboutUs = new ArrayList();
        AboutUs.add(ABOUTUS1);
    }
    public static final AboutUs ABOUTUS1 = new AboutUs("fb.com/oraclepetstop","oracle@petshop.com"
    ,"+555 555 555","Jl. Asia Afrika No.8, RT.1/RW.3, Gelora, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270");
}
