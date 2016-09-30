package com.nyx_itech.k13.database;

/**
 * Created by NYX_RIFAL on 8/10/2016.
 */
public class Guru {
    private int _id;
    private String _nama;
    private String _mapel;
    private String _foto;

    //empty constructor
    public Guru() {

    }

    //constructor
    public Guru(int id, String nama, String mapel, String foto) {
        this._id = id;
        this._nama = nama;
        this._mapel = mapel;
        this._foto = foto;
    }

    public int get_id() {
        return this._id;
    }

    public String get_nama() {
        return this._nama;
    }

    public String get_foto() {
        return this._foto;
    }

    public String get_mapel() {
        return this._mapel;
    }


    public void set_id(int id) {
        this._id = id;
    }

    public void set_nama(String nama) {
        this._nama = nama;
    }

    public void set_foto(String foto) {
        this._foto = foto;
    }

    public void set_mapel(String mapel) {
        this._mapel = mapel;
    }
}
