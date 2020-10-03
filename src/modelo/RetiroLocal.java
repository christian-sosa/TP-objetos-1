package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class RetiroLocal {

    private LocalTime horaEntrega;

    public LocalTime getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(LocalTime horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    //Segun el Date que le pases es la hora que devuelve

    public LocalTime traerHoraRetiro(LocalDate fecha) {
        return getHoraEntrega();
    }

    @Override
    public String toString() {
        return "RetiroLocal [horaEntrega=" + horaEntrega + "]";
    }
}
