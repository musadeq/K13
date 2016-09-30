package com.nyx_itech.k13.database;

/**
 * Created by NYX_RIFAL on 8/10/2016.
 */
public class Ortu {


    //private variables
    int _id;
    String _nama;
    String _email;
    String _password;
    String _foto;
    String _sekolah_id;
    String _sekolah_name;
    String _kelas_id;
    String _anak_id;
    String _anak_name;
    String _gcm_token;
    String _api_token;

    //empty constructor
    public Ortu() {

    }

    public int get_id() {
        return this._id;
    }

    public String get_nama() {
        return this._nama;
    }

    public String get_email() {
        return this._email;
    }

    public String get_password() {
        return this._password;
    }

    public String get_foto() {
        return this._foto;
    }

    public String get_sekolah_id() {
        return this._sekolah_id;
    }

    public String get_sekolah_name() {
        return this._sekolah_name;
    }

    public String get_kelas_id() {
        return this._kelas_id;
    }

    public String get_anak_id() {
        return this._anak_id;
    }

    public String get_anak_name() {
        return this._anak_name;
    }

    public String get_gcm_token() {
        return this._gcm_token;
    }

    public String get_api_token() {
        return this._api_token;
    }

    /** SET
     * String _email;
     * String _password;
     * String _foto;
     * String _sekolah_id;
     * String _sekolah_name;
     * String _kelas_id;
     * String _anak_id;
     * String _anak_name;
     * String _gcm_token;
     * String _api_token;
     **/
    public void set_id(int id){
        this._id = id;
    }

    public void set_nama(String nama){
        this._nama = nama;
    }
    public void set_email(String email){
        this._email = email;
    }
    public void set_password(String password){
        this._password = password;
    }
    public void set_foto(String foto){
        this._foto = foto;
    }
    public void set_sekolah_id(String sekolah_id){
        this._sekolah_id = sekolah_id;
    }
    public void set_sekolah_name(String sekolah_name){
        this._sekolah_name = sekolah_name;
    }
    public void set_kelas_id(String kelas_id){
        this._kelas_id = kelas_id;
    }
    public void set_anak_id(String anak_id){
        this._anak_id = anak_id;
    }
    public void set_anak_name(String anak_name){
        this._anak_name = anak_name;
    }
    public void set_gcm_token(String gcm_token){
        this._gcm_token = gcm_token;
    }
    public void set_api_token(String api_token){
        this._api_token = api_token;
    }

}
