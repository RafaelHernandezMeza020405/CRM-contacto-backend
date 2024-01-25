package mnContacto;


public class Contacto {
    private String nombres;
    private String apellidos;
    private String email;
    private String celular;
    private String fechadenacimiento;
    private String direccion;
    private String tipodecontacto;
    private String origen;
    


    public Contacto(){
    }
    public Contacto(String nombres, String apellidos, String email, String celular, String fechadenacimiento, String direccion, String tipodecontacto, String origen){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.celular = celular;
        this.fechadenacimiento = fechadenacimiento;
        this.direccion = direccion;
        this.tipodecontacto = tipodecontacto;
        this.origen = origen;

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
