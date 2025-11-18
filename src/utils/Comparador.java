package utils;

import model.Reserva;

public class Comparador {
    /**
     * Compara duas reservas: primeiro por nome, depois por código
     * Retorna: negativo se a < b, zero se iguais, positivo se a > b
     */
    public int comparar(Reserva a, Reserva b) {
        int compNome = a.getNome().compareToIgnoreCase(b.getNome());

        if (compNome != 0) {
            return compNome;
        }
        return a.getCodigo().compareTo(b.getCodigo());
    }
}
