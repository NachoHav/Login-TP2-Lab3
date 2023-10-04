package com.example.tp2_lab3.ui.perfil;

import androidx.lifecycle.ViewModel;

public class PerfilActivityViewModel extends ViewModel {

    private String nombre = "";
    private String apellido = "";
    private String dni = "";
    private String email = "";
    private String clave = "";

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
