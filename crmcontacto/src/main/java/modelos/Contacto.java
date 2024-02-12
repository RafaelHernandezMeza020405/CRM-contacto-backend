package modelos;


public class Contacto {
    protected String nombres;
    protected String apellidos;
    protected String email;
    protected String celular;
    protected String fechadenacimiento;
    protected String direccion;
    protected String tipodecontacto;
    protected String origen;
    protected String tareas;
    protected String comentarios;
    


    public Contacto(){
    }
    public Contacto(String nombres, String apellidos, String email, String celular, String fechadenacimiento, String direccion, String tipodecontacto, String origen, String tareas, String comentarios){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.celular = celular;
        this.fechadenacimiento = fechadenacimiento;
        this.direccion = direccion;
        this.tipodecontacto = tipodecontacto;
        this.origen = origen;
        this.tareas = tareas;
        this.comentarios = comentarios;

    }


    public String getNombres(){
        return nombres;

    }
    
    public void setNombres(String nombres){
        this.nombres = nombres;

    }

    
    public String getApellidos(){
        return apellidos;

    }
    
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;

    }
    
    public String getEmail(){
        return email;

    }
    
    public void setEmail(String email){
        this.email = email;

    }

    public String getTipodecontacto() {
        return tipodecontacto;
    }

    public void setTipodecontacto(String tipodecontacto) {
        this.tipodecontacto = tipodecontacto;
    }

    public String getTareas() {
        return tareas;
    }

    public void setTareas(String tareas) {
        this.tareas = tareas;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    public String getCelular(){
        return celular;

    }
    
    public void setCelular(String celular){
        this.celular = celular;

    }

    
    public String getfechaDeNacimiento(){
        return fechadenacimiento;

    }
    
    public void setfechaDeNacimiento(String fechadenacimiento){
        this.fechadenacimiento = fechadenacimiento;

    }
    
    public String getDireccion(){
        return direccion;

    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion;

    }
    
    public String getTipoDeContacto(){
        return tipodecontacto;

    }
    
    public void setTipoDeContacto(String tipodecontacto){
        this.tipodecontacto = tipodecontacto;


    }
    
    public String getOrigen(){
        return origen;

    }
    
    public void setOrigen(String origen){
        this.origen = origen;

    }
}
