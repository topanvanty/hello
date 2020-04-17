package com.example.hello;

public class mahasiswa {
    private String Nama;
    private String Nim;
    private String Prodi;

    public mahasiswa() {
    }

    public mahasiswa(String nama, String nim, String prodi) {
        Nama = nama;
        Nim = nim;
        Prodi = prodi;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getNim() {
        return Nim;
    }

    public void setNim(String nim) {
        Nim = nim;
    }

    public String getProdi() {
        return Prodi;
    }

    public void setProdi(String prodi) {
        Prodi = prodi;
    }
}
