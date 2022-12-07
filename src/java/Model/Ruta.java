package Model;

public class Ruta {

    String codigo;
    String nombre;
    String direccion;
    String dificultad;
    String clima;
    String horario;
    String duracion;

    public Ruta() {
    }

    public Ruta(String codigo, String nombre, String direccion, String dificultad, String clima, String horario, String duracion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.dificultad = dificultad;
        this.clima = clima;
        this.horario = horario;
        this.duracion = duracion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
    

}
