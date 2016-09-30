package com.nyx_itech.k13.database;

/**
 * Created by NYX_RIFAL on 8/10/2016.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "orangtua3";

    //  table name
    private static final String TABLE_ORTU = "ortu";
    private static final String TABLE_CHAT = "chat";
    private static final String TABLE_GURU = "guru";


    // ORTU Table Columns names
    private static final String KEY_ORTU_ID = "id";
    private static final String KEY_ORTU_NAMA = "nama";
    private static final String KEY_ORTU_EMAIL = "email";
    private static final String KEY_ORTU_PASSWORD = "password";
    private static final String KEY_ORTU_FOTO = "foto";
    private static final String KEY_ORTU_SEKOLAH_ID = "sekolah_id";
    private static final String KEY_ORTU_SEKOLAH_NAME = "sekolah_name";
    private static final String KEY_ORTU_KELAS_ID = "kelas_id";
    private static final String KEY_ORTU_ANAK_ID = "anak_id";
    private static final String KEY_ORTU_ANAK_NAME = "anak_name";
    private static final String KEY_ORTU_GCM_TOKEN = "gcm_token";
    private static final String KEY_ORTU_API_TOKEN = "api_token";

    //Chat Table Column Name
    private static final String KEY_CHAT_ID = "id";
    private static final String KEY_CHAT_GURU_ID = "guru_id";
    private static final String KEY_CHAT_PESAN = "pesan";
    private static final String KEY_CHAT_STATUS = "status";
    private static final String KEY_CHAT_DATE = "date";
    private static final String KEY_CHAT_TIME = "time";
    private static final String KEY_CHAT_ME = "me";


    //Guru Table Column Name
    private static final String KEY_GURU_ID = "id";
    private static final String KEY_GURU_NAMA = "nama";
    private static final String KEY_GURU_FOTO = "foto";
    private static final String KEY_GURU_MAPEL = "mapel";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables GURU
    private static final String CREATE_TABLE_GURU = "CREATE TABLE " + TABLE_GURU + "("
            + KEY_GURU_ID + " INTEGER PRIMARY KEY,"
            + KEY_GURU_NAMA + " TEXT,"
            + KEY_GURU_FOTO + " TEXT,"
            + KEY_GURU_MAPEL + " TEXT"
            + ")";

    // Creating Tables CHAT
    private static final String CREATE_CHAT_TABLE = "CREATE TABLE " + TABLE_CHAT + "("
            + KEY_CHAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CHAT_GURU_ID + " TEXT,"
            + KEY_CHAT_PESAN + " TEXT,"
            + KEY_CHAT_STATUS + " TEXT,"
            + KEY_CHAT_DATE + " TEXT,"
            + KEY_CHAT_TIME + " TEXT,"
            + KEY_CHAT_ME + " TEXT"
            + ")";


    // Creating Tables ORTU
    private static final String CREATE_ORTU_TABLE = "CREATE TABLE " + TABLE_ORTU + "("
            + KEY_ORTU_ID + " INTEGER PRIMARY KEY,"
            + KEY_ORTU_NAMA + " TEXT,"
            + KEY_ORTU_EMAIL + " TEXT,"
            + KEY_ORTU_PASSWORD + " TEXT,"
            + KEY_ORTU_FOTO + " TEXT,"
            + KEY_ORTU_SEKOLAH_ID + " TEXT,"
            + KEY_ORTU_SEKOLAH_NAME + " TEXT,"
            + KEY_ORTU_KELAS_ID + " TEXT,"
            + KEY_ORTU_ANAK_ID + " TEXT,"
            + KEY_ORTU_ANAK_NAME + " TEXT,"
            + KEY_ORTU_GCM_TOKEN + " TEXT,"
            + KEY_ORTU_API_TOKEN + " TEXT"
            + ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ORTU_TABLE);
        db.execSQL(CREATE_CHAT_TABLE);
        db.execSQL(CREATE_TABLE_GURU);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GURU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORTU);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addOrtu(Ortu ortu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ORTU_ID, ortu.get_id());
        values.put(KEY_ORTU_NAMA, ortu.get_nama());
        values.put(KEY_ORTU_EMAIL, ortu.get_email());
        values.put(KEY_ORTU_PASSWORD, ortu.get_password());
        values.put(KEY_ORTU_FOTO, ortu.get_foto());
        values.put(KEY_ORTU_SEKOLAH_ID, ortu.get_sekolah_id());
        values.put(KEY_ORTU_SEKOLAH_NAME, ortu.get_sekolah_name());
        values.put(KEY_ORTU_KELAS_ID, ortu.get_kelas_id());
        values.put(KEY_ORTU_ANAK_ID, ortu.get_anak_id());
        values.put(KEY_ORTU_ANAK_NAME, ortu.get_anak_name());
        values.put(KEY_ORTU_GCM_TOKEN, ortu.get_gcm_token());
        values.put(KEY_ORTU_API_TOKEN, ortu.get_api_token());

        // Inserting Row
        db.insert(TABLE_ORTU, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Contacts

    public Ortu getOrtu() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ORTU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        Ortu ortu = new Ortu();
        if (cursor.moveToFirst()) {


            ortu.set_id(Integer.parseInt(cursor.getString(0)));
            ortu.set_nama(cursor.getString(1));
            ortu.set_email(cursor.getString(2));
            ortu.set_password(cursor.getString(3));
            ortu.set_foto(cursor.getString(4));
            ortu.set_sekolah_id(cursor.getString(5));
            ortu.set_sekolah_name(cursor.getString(6));
            ortu.set_kelas_id(cursor.getString(7));
            ortu.set_anak_id(cursor.getString(8));
            ortu.set_anak_name(cursor.getString(9));
            ortu.set_gcm_token(cursor.getString(10));
            ortu.set_api_token(cursor.getString(11));
        }

        // return contact list
        return ortu;
    }

    // Updating single contact
    public int updateOrtu(Ortu ortu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ORTU_NAMA, ortu.get_nama());
        values.put(KEY_ORTU_EMAIL, ortu.get_email());
        values.put(KEY_ORTU_PASSWORD, ortu.get_password());
        values.put(KEY_ORTU_FOTO, ortu.get_foto());
        values.put(KEY_ORTU_SEKOLAH_ID, ortu.get_sekolah_id());
        values.put(KEY_ORTU_SEKOLAH_NAME, ortu.get_sekolah_name());
        values.put(KEY_ORTU_KELAS_ID, ortu.get_kelas_id());
        values.put(KEY_ORTU_ANAK_ID, ortu.get_anak_id());
        values.put(KEY_ORTU_ANAK_NAME, ortu.get_anak_name());
        values.put(KEY_ORTU_GCM_TOKEN, ortu.get_gcm_token());
        values.put(KEY_ORTU_API_TOKEN, ortu.get_api_token());

        // updating row
        return db.update(TABLE_ORTU, values, KEY_ORTU_ID + " = ?",
                new String[]{String.valueOf(ortu.get_id())});
    }

    // Deleting single contact
    public void deleteOrtu(Ortu ortu) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ORTU, KEY_ORTU_ID + " = ?",
                new String[]{String.valueOf(ortu.get_id())});
        db.close();
    }


    /**
     * All CRUD(Create, Read, Update, Delete) GURU
     * <p/>
     * G U R U
     * <p/>
     * <p/>
     * private static final String KEY_GURU_ID = "id";
     * private static final String KEY_GURU_NAMA = "nama";
     * private static final String KEY_GURU_MAPEL = "mapel";
     */


    public void addGuru(Guru guru) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GURU_ID, guru.get_id());
        values.put(KEY_GURU_NAMA, guru.get_nama());
        values.put(KEY_GURU_FOTO, guru.get_foto());
        values.put(KEY_GURU_MAPEL, guru.get_mapel());
        values.put(KEY_GURU_MAPEL, guru.get_mapel());
        // Inserting Row
        db.insert(TABLE_GURU, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Contacts

    public List<Guru> getGuru() {
        // Select All Query
        List<Guru> guruList = new ArrayList<Guru>();

        String selectQuery = "SELECT  * FROM " + TABLE_GURU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                Guru guru = new Guru();
                guru.set_id(Integer.parseInt(cursor.getString(0)));
                guru.set_nama(cursor.getString(1));
                guru.set_foto(cursor.getString(2));
                guru.set_mapel(cursor.getString(3));
                guruList.add(guru);

            } while (cursor.moveToNext());
        }

        // return contact list
        return guruList;
    }

    /**
     * C H A T
     * private static final String KEY_CHAT_ID = "id";
     * private static final String KEY_CHAT_GURU_ID = "guru_id";
     * private static final String KEY_CHAT_PESAN = "pesan";
     * private static final String KEY_CHAT_STATUS = "status";
     * private static final String KEY_CHAT_DATE = "date";
     * private static final String KEY_CHAT_TIME = "time"
     */


    // Adding new contact
    public void addChat(Chat chat) {
        SQLiteDatabase db = this.getWritableDatabase();


        //Chat Table Column Name



        ContentValues values = new ContentValues();
        values.put(KEY_CHAT_GURU_ID, chat.get_guru_id());
        values.put(KEY_CHAT_PESAN, chat.get_pesan());
        values.put(KEY_CHAT_STATUS, chat.get_status());
        values.put(KEY_CHAT_DATE, chat.get_date());
        values.put(KEY_CHAT_TIME, chat.get_time());
        values.put(KEY_CHAT_ME, chat.get_me());

        // Inserting Row
        db.insert(TABLE_CHAT, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Contacts

    public List<Chat> getChat(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Chat> chats = new ArrayList<Chat>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CHAT + " WHERE " + KEY_CHAT_GURU_ID + " = '" + String.valueOf(id) + "'", null);
        if (cursor.moveToFirst()) {
            do {

                Chat chat = new Chat();
                chat.set_id(Integer.parseInt(cursor.getString(0)));
                chat.set_guru_id(cursor.getString(1));
                chat.set_pesan(cursor.getString(2));
                chat.set_status(cursor.getString(3));
                chat.set_date(cursor.getString(4));
                chat.set_time(cursor.getString(5));
                chat.set_me(cursor.getString(6));
                chats.add(chat);
            } while (cursor.moveToNext());
        }


        return chats;
    }

    public Chat getLast(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CHAT + " WHERE " + KEY_CHAT_GURU_ID + " = '" + id + "' ORDER BY " + KEY_CHAT_ID + " DESC LIMIT 1", null);
        Chat chat= new Chat();
        if (cursor.moveToFirst()) {
            chat.set_id(Integer.parseInt(cursor.getString(0)));
            chat.set_guru_id(cursor.getString(1));
            chat.set_pesan(cursor.getString(2));
            chat.set_status(cursor.getString(3));
            chat.set_date(cursor.getString(4));
            chat.set_time(cursor.getString(5));
            chat.set_me(cursor.getString(6));
        }

        return chat;

    }

}

