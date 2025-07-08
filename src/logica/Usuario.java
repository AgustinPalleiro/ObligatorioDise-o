/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import observador.Observable;

/**
 *
 * @author emili
 */
public abstract class Usuario extends Observable{
    
    private String nombreCompleto;
    private String password;
    private String cedula;

    public Usuario(String nombreCompleto, String password, String cedula) {
        this.nombreCompleto = nombreCompleto;
        this.password = password;
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getPassword() {
        return password;
    }

    public String getCedula() {
        return cedula;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    
    
}
