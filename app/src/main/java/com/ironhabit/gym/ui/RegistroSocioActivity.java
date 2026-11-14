package com.ironhabit.gym.ui;

import android.os.Bundle;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ironhabit.gym.R;
import com.ironhabit.gym.data.SocioRepository;
import com.ironhabit.gym.util.CalculadoraIMC;
import com.ironhabit.gym.util.SimpleTextWatcher;
import com.ironhabit.gym.util.ValidadorFormulario;

import java.util.Locale;

public class RegistroSocioActivity extends AppCompatActivity {

    private static final String[] MEMBRESIAS = {
            "Plan Mensual General", "Plan Trimestral", "Plan Anual VIP"
    };

    private TextInputLayout tilNombre, tilEmailSocio, tilTelefono, tilPeso, tilEstatura;
    private TextInputEditText etNombre, etEmailSocio, etTelefono, etPeso, etEstatura;
    private Spinner spMembresia;
    private TextView tvImcPreview;
    private final SocioRepository repo = SocioRepository.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_socio);

        tilNombre = findViewById(R.id.tilNombre);
        tilEmailSocio = findViewById(R.id.tilEmailSocio);
        tilTelefono = findViewById(R.id.tilTelefono);
        tilPeso = findViewById(R.id.tilPeso);
        tilEstatura = findViewById(R.id.tilEstatura);
        etNombre = findViewById(R.id.etNombre);
        etEmailSocio = findViewById(R.id.etEmailSocio);
        etTelefono = findViewById(R.id.etTelefono);
        etPeso = findViewById(R.id.etPeso);
        etEstatura = findViewById(R.id.etEstatura);
        spMembresia = findViewById(R.id.spMembresia);
        tvImcPreview = findViewById(R.id.tvImcPreview);
        MaterialButton btnGuardar = findViewById(R.id.btnGuardarSocio);

        spMembresia.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, MEMBRESIAS));

        SimpleTextWatcher vistaPrevia = new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                actualizarVistaPreviaIMC();
            }
        };
        etPeso.addTextChangedListener(vistaPrevia);
        etEstatura.addTextChangedListener(vistaPrevia);

        btnGuardar.setOnClickListener(v -> guardarSocio());
    }

    private void actualizarVistaPreviaIMC() {
        double peso = ValidadorFormulario.parsearPositivo(textoDe(etPeso));
        double estatura = ValidadorFormulario.parsearPositivo(textoDe(etEstatura));
        if (peso < 0 || estatura < 0) {
            tvImcPreview.setText("IMC: ingresa peso y estatura");
        } else {
            double imc = CalculadoraIMC.calcular(peso, estatura);
            tvImcPreview.setText(String.format(Locale.US, "IMC: %.1f · %s",
                    imc, CalculadoraIMC.clasificar(imc)));
        }
    }

    private void guardarSocio() {
        String nombre = textoDe(etNombre);
        String email = textoDe(etEmailSocio);
        String telefono = textoDe(etTelefono);
        double peso = ValidadorFormulario.parsearPositivo(textoDe(etPeso));
        double estatura = ValidadorFormulario.parsearPositivo(textoDe(etEstatura));

        limpiarErrores();
        boolean valido = true;

        if (!ValidadorFormulario.noVacio(nombre)) {
            tilNombre.setError("El nombre es obligatorio");
            valido = false;
        }
        if (!ValidadorFormulario.esEmailValido(email)) {
            tilEmailSocio.setError("Correo no válido");
            valido = false;
        }
        if (!ValidadorFormulario.esTelefonoValido(telefono)) {
            tilTelefono.setError("Entre 7 y 10 dígitos");
            valido = false;
        }
        if (peso < 0) {
            tilPeso.setError("Peso inválido");
            valido = false;
        }
        if (estatura < 0) {
            tilEstatura.setError("Estatura inválida");
            valido = false;
        }
        if (!valido) {
            Toast.makeText(this, "Revisa los campos marcados", Toast.LENGTH_SHORT).show();
            return;
        }

        String membresia = (String) spMembresia.getSelectedItem();
        repo.guardar(nombre, email, telefono, membresia, peso, estatura);
        Toast.makeText(this, "Socio registrado", Toast.LENGTH_SHORT).show();
        finish(); // regresa a SociosActivity, que refresca la lista en onResume()
    }

    private void limpiarErrores() {
        tilNombre.setError(null);
        tilEmailSocio.setError(null);
        tilTelefono.setError(null);
        tilPeso.setError(null);
        tilEstatura.setError(null);
    }

    private String textoDe(TextInputEditText campo) {
        return campo.getText() == null ? "" : campo.getText().toString().trim();
    }
}