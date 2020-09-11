package modelo;

public class Cliente {
    private String apellido;
    private String nombre;
    private int dni;

    public Cliente(String apellido, String nombre, int dni) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "cliente{" +
                "dni=" + dni +
                ", nombre=" + nombre +
                ", apellido=" + apellido +
                '}';
    }
}
