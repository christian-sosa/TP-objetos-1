package modelo;

import java.time.LocalTime;

public class RetiroLocal {

    private LocalTime horaEntrega;

    public RetiroLocal(LocalTime horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public LocalTime getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(LocalTime horaEntrega) {
        this.horaEntrega = horaEntrega;
    }
}
