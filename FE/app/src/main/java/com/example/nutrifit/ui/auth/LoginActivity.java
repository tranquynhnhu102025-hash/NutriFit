package com.example.nutrifit.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nutrifit.MainActivity;
import com.example.nutrifit.R;
import com.example.nutrifit.data.local.SharedPrefs;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private RequestQueue queue;
    private SharedPrefs sharedPrefs;

    private static final String BASE_URL = "http://10.0.2.2:5104";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefs = new SharedPrefs(this);

        if (sharedPrefs.isLoggedIn()) {
            goToMain();
            return;
        }

        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        queue = Volley.newRequestQueue(this);

        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject body = new JSONObject();
            body.put("Email", email);
            body.put("Password", password);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    BASE_URL + "/login",
                    body,
                    response -> {
                        try {
                            if (response.getBoolean("success")) {
                                JSONObject user = response.getJSONObject("user");
                                sharedPrefs.saveLogin(
                                        user.getInt("Id"),
                                        user.getString("Name"),
                                        user.getString("Email")
                                );
                                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                goToMain();
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "Lỗi xử lý dữ liệu", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        Toast.makeText(this, "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
            );
            queue.add(request);
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
