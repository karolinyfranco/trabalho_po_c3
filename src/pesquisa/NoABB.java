package pesquisa;

import model.Reserva;

import java.util.ArrayList;

public class NoABB {
    private String nome;
    private ArrayList<Reserva> reservas; // Lista para tratar nomes iguais
    private NoABB esq, dir;

    public NoABB(Reserva reserva) {
        this.nome = reserva.getNome();
        this.reservas = new ArrayList<>();
        this.reservas.add(reserva);
        this.esq = null;
        this.dir = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public NoABB getEsq() {
        return esq;
    }

    public void setEsq(NoABB esq) {
        this.esq = esq;
    }

    public NoABB getDir() {
        return dir;
    }

    public void setDir(NoABB dir) {
        this.dir = dir;
    }
}
