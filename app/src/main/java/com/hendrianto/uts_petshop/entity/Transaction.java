package com.hendrianto.uts_petshop.entity;

public class Transaction {
    private int id;
    private int jumlah;
    private String nama_barang;
    private String jenis;
    private String email;
    private double harga;

    public Transaction(int id, String nama_barang, String jenis, String email, double harga, int jumlah){
        this.id = id;
        this.nama_barang = nama_barang;
        this.jenis = jenis;
        this.email = email;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
