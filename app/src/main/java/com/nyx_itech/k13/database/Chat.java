package com.nyx_itech.k13.database;

/**
 * Created by NYX_RIFAL on 8/10/2016.
 */
public class Chat {

    //private variables

    private int _id;
    private String _guru_id;
    private String _pesan;
    private String _status;
    private String _date;
    private String _time;
    private String _me;


    //empty constructor

    public Chat() {
    }

    //constructor

    public Chat(String guru_id, String pesan, String status, String date, String time, String me) {
        this._guru_id = guru_id;
        this._pesan = pesan;
        this._status = status;
        this._date = date;
        this._time = time;
        this._me = me;

    }

    public int get_id() {
        return this._id;
    }

    public String get_guru_id() {
        return this._guru_id;
    }

    public String get_pesan() {
        return this._pesan;
    }

    public String get_status() {
        return this._status;
    }

    public String get_date() {
        return this._date;
    }

    public String get_time() {
        return this._time;
    }

    public String get_me() {
        return this._me;
    }

    // SET

    public void set_id(int id) {
        this._id = id;
    }

    public void set_guru_id(String guru_id) {
        this._guru_id = guru_id;
    }

    public void set_pesan(String pesan) {
        this._pesan = pesan;
    }

    public void set_status(String status) {
        this._status = status;
    }

    public void set_date(String date) {
        this._date = date;
    }

    public void set_time(String time) {
        this._time = time;
    }

    public void set_me(String me) {
        this._me = me;
    }

}
