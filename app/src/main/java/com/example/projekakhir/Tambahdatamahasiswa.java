package com.example.projekakhir;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Tambahdatamahasiswa extends AppCompatActivity {
    EditText editTextNamaMahasiswa, editTextAlamat, editTextHoby;
    Button btnSimpan;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahdatamahasiswa);

        // Inisialisasi EditText dan Button
        editTextNamaMahasiswa = findViewById(R.id.editTextNamaMahasiswa);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextHoby = findViewById(R.id.editTextHoby);
        btnSimpan = findViewById(R.id.btnSimpan);
        myDB = new DatabaseHelper(this);

        // OnClickListener ke Button Simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan nilai dari EditText
                String nama = editTextNamaMahasiswa.getText().toString();
                String alamat = editTextAlamat.getText().toString();
                String hoby = editTextHoby.getText().toString();

                // Memeriksa apakah semua EditText sudah diisi
                if (nama.equals("") || alamat.equals("") || hoby.equals("")) {
                    Toast.makeText(Tambahdatamahasiswa.this, "Isi semua kolom", Toast.LENGTH_SHORT).show();
                } else {
                    // Menyimpan data mahasiswa ke database
                    boolean isInserted = myDB.addMahasiswa(nama, alamat, hoby);
                    if (isInserted) {
                        Toast.makeText(Tambahdatamahasiswa.this, "Data Mahasiswa Tersimpan", Toast.LENGTH_SHORT).show();
                        // Mengosongkan EditText setelah data tersimpan
                        editTextNamaMahasiswa.setText("");
                        editTextAlamat.setText("");
                        editTextHoby.setText("");
                        // Beralih ke aktivitas Datamahasiswa
                        Intent intent = new Intent(Tambahdatamahasiswa.this, Datamahasiswa.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Tambahdatamahasiswa.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
