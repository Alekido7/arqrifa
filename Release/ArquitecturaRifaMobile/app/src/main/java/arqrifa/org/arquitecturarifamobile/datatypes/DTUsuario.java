package arqrifa.org.arquitecturarifamobile.datatypes;


import java.io.Serializable;

public class DTUsuario implements Serializable{

    public static String ADMIN = "Admin";
    public static String ENCARGADO = "Encargado";
    public static String ESTUDIANTE = "Estudiante";

    private int ci;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String email;
    private String rol;
    private int generacion;
    private int inasistencias;

    //<editor-fold defaultstate="collapsed" desc="Getters&Setters">
    public int getCi() {
        return ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public int getGeneracion() {
        return generacion;
    }

    public int getInasistencias() {
        return inasistencias;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setGeneracion(int generacion) {
        this.generacion = generacion;
    }

    public void setInasistencias(int inasistencias) {
        this.inasistencias = inasistencias;
    }
    //</editor-fold>

    public DTUsuario() {
        this(0, "n/d", "n/d", "n/d", "n/d", "n/d", 0, 0);
    }

    public DTUsuario(int ci, String nombre, String apellido, String contrasena, String email, String rol, int generacion, int inasistencias) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.email = email;
        this.rol = rol;
        this.generacion = generacion;
    }

}