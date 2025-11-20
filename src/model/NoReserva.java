package model;

public class NoReserva {
    private Reserva reserva;
    private NoReserva proximo;

    public NoReserva(Reserva reserva) {
        this.reserva = reserva;
        this.proximo = null;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public NoReserva getProximo() {
        return proximo;
    }

    public void setProximo(NoReserva proximo) {
        this.proximo = proximo;
    }
}
