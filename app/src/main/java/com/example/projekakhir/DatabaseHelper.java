package com.example.projekakhir;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "db_projek.db";

    // inisialisasi DatabaseHelper
    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    // panggil ketika database pertama kali dibuat
    public void onCreate(SQLiteDatabase MyDatabase) {
        // Membuat tabel users dan mahasiswa
        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT)");
        MyDatabase.execSQL("create Table mahasiswa(id INTEGER primary key AUTOINCREMENT, nama TEXT, alamat TEXT, hoby TEXT)");
    }

    // Metode yang dipanggil ketika diperlukan upgrade pada skema database
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        // Menghapus tabel users dan mahasiswa jika sudah ada sebelumnya
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists mahasiswa");
    }

    // Memasukkan data baru ke dalam tabel users
    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Memeriksa apakah email tertentu sudah ada dalam tabel users
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    // Memeriksa apakah kombinasi email dan password cocok dengan data yang ada dalam tabel users
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    // Menambahkan data mahasiswa ke dalam tabel mahasiswa
    public boolean addMahasiswa(String nama, String alamat, String hoby) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("alamat", alamat);
        contentValues.put("hoby", hoby);
        long result = MyDatabase.insert("mahasiswa", null, contentValues);
        return result != -1;
    }

    // Mengambil semua data mahasiswa dari tabel mahasiswa
    public Cursor getAllMahasiswa() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from mahasiswa", null);
        return res;
    }

    // Menghapus data mahasiswa berdasarkan ID yang diberikan
    public boolean deleteMahasiswa(int id) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        int result = MyDatabase.delete("mahasiswa", "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }


    public boolean updateMahasiswa(Integer id, String nama, String alamat, String hoby) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("alamat", alamat);
        contentValues.put("hoby", hoby);

        long result = MyDatabase.update("mahasiswa", contentValues, "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
