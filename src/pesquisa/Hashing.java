package pesquisa;

import java.util.ArrayList;

import model.Reserva;

public class Hashing {
    private ArrayList<NoHashing>[] tabela;
    private int tamanho;

    public Hashing(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new ArrayList[tamanho];

        // Inicializa a tabela hash
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ArrayList<>();
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    private int hash(String nome) {
        char carac;
        int i, soma = 0;
        for (i = 0; i < nome.length(); i++){
            carac = nome.charAt(i);
            soma += Character.getNumericValue(carac);
        }
        return soma % tamanho;
    }

    public void inserir(Reserva reserva) {
        int indice = hash(reserva.getNome());
        tabela[indice].add(new NoHashing(reserva));
    }

    public ArrayList<Reserva> pesquisar(String nome) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        int indice = hash(nome);

        // Percorre a lista encadeada naquela posição da tabela
        for (NoHashing no : tabela[indice]) {
            if (no.getReserva().getNome().equalsIgnoreCase(nome)) {
                resultado.add(no.getReserva());
            }
        }
        return resultado;
    }
}
