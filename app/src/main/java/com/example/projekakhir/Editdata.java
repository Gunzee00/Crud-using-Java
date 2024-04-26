package com.example.projekakhir;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editdata extends AppCompatActivity {
    EditText editUpdateTextNamaMahasiswa, editUpdateTextAlamat, editUpdateTextHoby;
    Button btnUpdateSimpan;
    DatabaseHelper myDB;
    int mahasiswaID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        // Inisialisasi EditText, Button, dan DatabaseHelper
        editUpdateTextNamaMahasiswa = findViewById(R.id.editUpdateTextNamaMahasiswa);
        editUpdateTextAlamat = findViewById(R.id.editUpdateTextAlamat);
        editUpdateTextHoby = findViewById(R.id.editUpdateTextHoby);
        btnUpdateSimpan = findViewById(R.id.btnUpdateSimpan);
        myDB = new DatabaseHelper(this);

        // Mendapatkan ID mahasiswa dari intent sebelumnya
        mahasiswaID = getIntent().getIntExtra("mahasiswaID", -1);

        // Memuat data mahasiswa berdasarkan ID yang diterima


        // Menerapkan OnClickListener ke Button Update
        btnUpdateSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan nilai dari EditText
                String nama = editUpdateTextNamaMahasiswa.getText().toString();
                String alamat = editUpdateTextAlamat.getText().toString();
                String hoby = editUpdateTextHoby.getText().toString();

                // Memeriksa apakah semua EditText sudah diisi
                if (nama.equals("") || alamat.equals("") || hoby.equals("")) {
                    Toast.makeText(Editdata.this, "Isi semua kolom", Toast.LENGTH_SHORT).show();
                } else {
                    // Memperbarui data mahasiswa di database
                    boolean isUpdated = myDB.updateMahasiswa(mahasiswaID, nama, alamat, hoby);
                    if (isUpdated) {
                        Toast.makeText(Editdata.this, "Data Mahasiswa Diperbarui", Toast.LENGTH_SHORT).show();
                        // Kembali ke aktivitas Datamahasiswa
                        finish();
                    } else {
                        Toast.makeText(Editdata.this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}
