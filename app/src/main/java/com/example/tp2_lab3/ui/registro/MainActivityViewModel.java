package com.example.tp2_lab3.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tp2_lab3.models.Usuario;
import com.example.tp2_lab3.request.ApiClient;
import com.example.tp2_lab3.ui.login.LoginActivity;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient apiClient;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = new ApiClient();
    }

    public void registrarUsuario(String nombre, String apellido, String dni, String mail, String clave) {
        try {
            Long dniLong = Long.parseLong(dni);

            // Validar si el correo electrónico ya está en uso
            Usuario usuarioRegistrado = apiClient.login(context, mail, clave);
            if (usuarioRegistrado != null) {
                Toast.makeText(context, "Ya existe un usuario registrado con ese correo electrónico", Toast.LENGTH_SHORT).show();
                return;
            }

            Usuario usuario = new Usuario(nombre, apellido, dniLong, mail, clave);
            apiClient.registrar(context, usuario);

            Intent intent = new Intent(context, LoginActivity.class );
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al convertir el DNI a número. Asegúrate de ingresar un DNI válido.", Toast.LENGTH_SHORT).show();
        }
    }
}
