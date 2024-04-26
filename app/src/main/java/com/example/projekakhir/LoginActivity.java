package com.example.projekakhir;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.projekakhir.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Membuat instance dari DatabaseHelper untuk mengakses database
        databaseHelper = new DatabaseHelper(this);

        // Mendengarkan tombol login untuk proses autentikasi
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mengambil nilai email dan password yang dimasukkan oleh pengguna
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                // Memeriksa apakah email dan password kosong
                if(email.equals("")||password.equals(""))
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    // Memeriksa kredensial dalam database
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);

                    // Jika kredensial valid, mengalihkan ke MainActivity
                    if(checkCredentials == true){
                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        // Jika kredensial tidak valid, menampilkan pesan kesalahan
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Mendengarkan tombol redirect untuk menuju ke halaman pendaftaran
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
