package pesquisa;

import model.Reserva;

public class NoHashing {
    private Reserva reserva;
    private NoHashing proximo;

    public NoHashing(Reserva reserva) {
        this.reserva = reserva;
        this.proximo = null;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public NoHashing getProximo() {
        return proximo;
    }

    public void setProximo(NoHashing proximo) {
        this.proximo = proximo;
    }
}
