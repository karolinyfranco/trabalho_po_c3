package pesquisa;

import model.Reserva;

import java.util.ArrayList;

public class NoAVL {
    private String nome; // Chave de pesquisa
    private ArrayList<Reserva> reservas; // Lista de reservas com o mesmo nome
    private NoAVL esquerda;
    private NoAVL direita;
    private int altura;

    public NoAVL(Reserva reserva) {
        this.nome = reserva.getNome();
        this.reservas = new ArrayList<>();
        this.reservas.add(reserva);
        this.esquerda = null;
        this.direita = null;
        this.altura = 1; // Nova folha tem altura 1
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public NoAVL getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoAVL esquerda) {
        this.esquerda = esquerda;
    }

    public NoAVL getDireita() {
        return direita;
    }

    public void setDireita(NoAVL direita) {
        this.direita = direita;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
