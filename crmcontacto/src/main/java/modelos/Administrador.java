/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author User
 */
public class Administrador extends Contacto {

    private String contraseña;

    public Administrador() {
    }

    public Administrador(String contraseña) {
        this.contraseña = contraseña;
    }

    public Administrador(String email, String contraseña) {

        this.email = email;
        this.contraseña = contraseña;
    }

    public Administrador(String nombres, String apellidos, String email, String celular, String fechadenacimiento, String direccion, String tipodecontacto, String origen, String contraseña, String tareas, String comentarios) {
        super(nombres, apellidos, email, celular, fechadenacimiento, direccion, tipodecontacto, origen, tareas, comentarios);
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.celular = celular;
        this.fechadenacimiento = fechadenacimiento;
        this.direccion = direccion;
        this.tipodecontacto = tipodecontacto;
        this.origen = origen;

    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
