package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class RetiroLocal extends Entrega {

    public RetiroLocal(int id, LocalDate fecha, boolean efectivo, LocalTime horaEntrega) {
        super(id, fecha, efectivo);
        this.horaEntrega = horaEntrega;
    }

    private LocalTime horaEntrega;

    public LocalTime getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(LocalTime horaEntrega) {
        this.horaEntrega = horaEntrega;
    }


    public LocalTime traerHoraRetiro(LocalDate fecha) {
        return getHoraEntrega();
    }

    @Override
    public String toString() {
        return "RetiroLocal [horaEntrega=" + horaEntrega + "]";
    }
}
