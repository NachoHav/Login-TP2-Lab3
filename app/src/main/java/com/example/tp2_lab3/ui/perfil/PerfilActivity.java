package com.example.tp2_lab3.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp2_lab3.databinding.ActivityPerfilBinding;
import com.example.tp2_lab3.models.Usuario;
import com.example.tp2_lab3.ui.login.LoginActivity;
import com.example.tp2_lab3.ui.login.LoginActivityViewModel;

public class PerfilActivity extends AppCompatActivity {

    private ActivityPerfilBinding binding;
    private LoginActivityViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        if (usuario != null) {
            // Precargar los datos del usuario en los campos
            binding.etNombre.setText(usuario.getNombre());
            binding.etApellido.setText(usuario.getApellido());
            binding.etDni.setText(String.valueOf(usuario.getDni()));
            binding.etEmail.setText(usuario.getMail());
            binding.etClave.setText(usuario.getClave());
        }

        binding.btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos modificados y actualizar el usuario
                String nuevoNombre = binding.etNombre.getText().toString();
                String nuevoApellido = binding.etApellido.getText().toString();
                String nuevoDni = binding.etDni.getText().toString();
                String nuevoMail = binding.etEmail.getText().toString();
                String nuevaClave = binding.etClave.getText().toString();

                // Lógica para validar y actualizar los datos
                // ...

                // Crear un nuevo usuario con los datos actualizados
                Usuario usuarioActualizado = new Usuario(nuevoNombre, nuevoApellido, Long.parseLong(nuevoDni), nuevoMail, nuevaClave);

                // Actualizar el usuario en la API y en la sesión actual
                loginViewModel.actualizarUsuario(usuarioActualizado);

                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                // Puedes cerrar la actividad o realizar otras acciones después de guardar
                // finish();
            }
        });
    }
}
