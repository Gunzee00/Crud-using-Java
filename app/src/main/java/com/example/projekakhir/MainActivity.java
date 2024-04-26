package com.example.projekakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tentukan tombol btnTambahData menggunakan findViewById
        Button btnTambahData = findViewById(R.id.btnTambahData);
        btnTambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol "Tambah Data" ditekan, buka halaman form data mahasiswa
                Intent intent = new Intent(MainActivity.this, Tambahdatamahasiswa.class);
                startActivity(intent);
            }
        });

        // Tentukan tombol btnInformasi menggunakan findViewById
        Button btnLihatData = findViewById(R.id.btnLihatData);
        btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat intent untuk memulai activity_infomasi.xml
                Intent intent = new Intent(MainActivity.this, Datamahasiswa.class);

                // Mulai activity baru menggunakan intent
                startActivity(intent);
            }
        });

        // Tentukan tombol btnLihatData menggunakan findViewById
        Button btnInformasi = findViewById(R.id.btnInformasi);
        btnInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat intent untuk memulai activity_datamahasiswa.xml
                Intent intent = new Intent(MainActivity.this, Informasi.class);

                // Mulai activity baru menggunakan intent
                startActivity(intent);
            }
        });
    }
}
