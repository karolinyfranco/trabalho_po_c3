package ordenacao;

import model.Reserva;
import utils.Comparador;

public class Heapsort {
    Comparador comp = new Comparador();

    public void ordenar(Reserva[] lista) {
        int n = lista.length;

        // Constrói o heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            refazHeap(lista, n, i);
        }

        // Extrai elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            trocar(lista, 0, i);
            refazHeap(lista, i, 0);
        }
    }

    private void refazHeap(Reserva[] lista, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && comp.comparar(lista[esq], lista[maior]) > 0) {
            maior = esq;
        }

        if (dir < n && comp.comparar(lista[dir], lista[maior]) > 0) {
            maior = dir;
        }

        if (maior != i) {
            trocar(lista, i, maior);
            refazHeap(lista, n, maior);
        }
    }

    private void trocar(Reserva[] lista, int i, int j) {
        Reserva temp = lista[i];
        lista[i] = lista[j];
        lista[j] = temp;
    }
}
