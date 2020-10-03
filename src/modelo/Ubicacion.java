package modelo;

public class Ubicacion {
    private double latitud;
    private double longitud;

    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    //preguntar por que devolver un Ubicacion
    public Ubicacion traerUbicacion() {

        //return new Ubicacion (latitud,longitud);
        return this;
    }

    @Override
    public String toString() {
        return "Ubicacion [latitud=" + latitud + ", longitud=" + longitud + "]";
    }
}



