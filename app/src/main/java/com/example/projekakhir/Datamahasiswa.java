package com.example.projekakhir;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import android.content.Intent;
public class Datamahasiswa extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView listViewMahasiswa;
    ArrayList<String> listMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datamahasiswa);

        // Inisialisasi DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Inisialisasi elemen UI
        listViewMahasiswa = findViewById(R.id.listViewMahasiswa);

        // Mengambil data mahasiswa dari database
        listMahasiswa = new ArrayList<>();
        Cursor dataMahasiswa = databaseHelper.getAllMahasiswa();
        if (dataMahasiswa.getCount() == 0) {
            // Tampilkan pesan jika tidak ada data
            Toast.makeText(this, "Tidak ada data mahasiswa", Toast.LENGTH_SHORT).show();
        } else {
            // Ambil data dari kursor dan tambahkan ke dalam list
            while (dataMahasiswa.moveToNext()) {
                String namaMahasiswa = dataMahasiswa.getString(1);
                String alamat = dataMahasiswa.getString(2);
                String hobi = dataMahasiswa.getString(3);
                listMahasiswa.add("Nama: " + namaMahasiswa + "\nAlamat: " + alamat + "\nHobi: " + hobi);
            }
            // Tampilkan data dalam ListView menggunakan ArrayAdapter
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listMahasiswa);
            listViewMahasiswa.setAdapter(adapter);
        }

        // Tutup kursor setelah selesai menggunakannya
        dataMahasiswa.close();

        // Menambahkan listener untuk item di ListView
        listViewMahasiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showOptionsDialog(position);
            }
        });
    }

    // Method untuk menampilkan dialog pilihan (Edit atau Hapus)
    // Method untuk menampilkan dialog pilihan (Edit atau Hapus)
    private void showOptionsDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilihan");
        builder.setItems(new CharSequence[]{"Edit", "Hapus"}, (dialog, which) -> {
            switch (which) {
                case 0: // Edit
                    // Ambil data mahasiswa yang dipilih
                    String selectedMahasiswa = listMahasiswa.get(position);
                    String[] data = selectedMahasiswa.split("\n");
                    String nama = data[0].substring(6);
                    String alamat = data[1].substring(8);
                    String hobi = data[2].substring(6);

                    // Buat intent untuk membuka activity EditMahasiswa
                    Intent intent = new Intent(Datamahasiswa.this, Editdata.class);
                    intent.putExtra("nama", nama);
                    intent.putExtra("alamat", alamat);
                    intent.putExtra("hobi", hobi);
                    intent.putExtra("id", position + 1); // Tambahkan 1 karena ID dimulai dari 1, bukan 0
                    startActivity(intent);
                    break;

                case 1: // Hapus
                    // Implementasi kode untuk menghapus data
                    boolean isDeleted = databaseHelper.deleteMahasiswa(position + 1); // Tambahkan 1 karena ID dimulai dari 1, bukan 0
                    if (isDeleted) {
                        Toast.makeText(
                                Datamahasiswa.this, "Data mahasiswa berhasil dihapus", Toast.LENGTH_SHORT).show();
                        // Refresh ListView
                        refreshListView();
                    } else {
                        Toast.makeText(Datamahasiswa.this, "Gagal menghapus data mahasiswa", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        });
        builder.create().show();
    }


    // Method untuk memperbarui ListView setelah menghapus data
    private void refreshListView() {
        listMahasiswa.clear();
        Cursor dataMahasiswa = databaseHelper.getAllMahasiswa();
        if (dataMahasiswa.getCount() != 0) {
            while (dataMahasiswa.moveToNext()) {
                String namaMahasiswa = dataMahasiswa.getString(1);
                String alamat = dataMahasiswa.getString(2);
                String hobi = dataMahasiswa.getString(3);
                listMahasiswa.add("Nama: " + namaMahasiswa + "\nAlamat: " + alamat + "\nHobi: " + hobi);
            }
        }
        dataMahasiswa.close();
        ((ArrayAdapter) listViewMahasiswa.getAdapter()).notifyDataSetChanged();
    }


}