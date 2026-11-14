package com.ironhabit.gym.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ironhabit.gym.R;
import com.ironhabit.gym.data.AuthRepository;
import com.ironhabit.gym.util.ValidadorFormulario;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilEmail, tilPassword;
    private TextInputEditText etEmail, etPassword;
    private final AuthRepository auth = new AuthRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        MaterialButton btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(v -> intentarLogin());
    }

    private void intentarLogin() {
        String email = textoDe(etEmail);
        String pass = textoDe(etPassword);

        tilEmail.setError(null);
        tilPassword.setError(null);

        boolean valido = true;
        if (!ValidadorFormulario.esEmailValido(email)) {
            tilEmail.setError("Ingresa un correo válido");
            valido = false;
        }
        if (!ValidadorFormulario.esPasswordValida(pass)) {
            tilPassword.setError("Mínimo 6 caracteres");
            valido = false;
        }
        if (!valido) return;

        if (auth.autenticar(email, pass)) {
            startActivity(new Intent(this, SociosActivity.class));
            finish(); // evita volver al login con el botón atrás
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }

    private String textoDe(TextInputEditText campo) {
        return campo.getText() == null ? "" : campo.getText().toString().trim();
    }
}