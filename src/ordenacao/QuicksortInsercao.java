package ordenacao;

import model.Reserva;
import utils.Comparador;

public class QuicksortInsercao {
    Comparador comp = new Comparador();

    public void ordenar(Reserva[] lista, int esq, int dir) {
        if (esq < dir) {
            // Se o tamanho da partição for <= 20, usa inserção direta
            if (dir - esq + 1 <= 20) {
                insercaoDireta(lista, esq, dir);
            } else {
                int p = particionar(lista, esq, dir);
                ordenar(lista, esq, p - 1);
                ordenar(lista, p + 1, dir);
            }
        }
    }

    private void insercaoDireta(Reserva[] lista, int esq, int dir) {
        for (int i = esq + 1; i <= dir; i++) {
            Reserva chave = lista[i];
            int j = i - 1;

            while (j >= esq && comp.comparar(lista[j], chave) > 0) {
                lista[j + 1] = lista[j];
                j--;
            }
            lista[j + 1] = chave;
        }
    }

    // O método de particionamento é o mesmo do Quicksort padrão
    private int particionar(Reserva[] lista, int esq, int dir) {
        int meio = (esq + dir) / 2;
        Reserva pivo = lista[meio];

        trocar(lista, meio, dir);

        int i = esq - 1;

        for (int j = esq; j < dir; j++) {
            if (comp.comparar(lista[j], pivo) <= 0) {
                i++;
                trocar(lista, i, j);
            }
        }

        trocar(lista, i + 1, dir);
        return i + 1;
    }

    private void trocar(Reserva[] lista, int i, int j) {
        Reserva temp = lista[i];
        lista[i] = lista[j];
        lista[j] = temp;
    }
}
