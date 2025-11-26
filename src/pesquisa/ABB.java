package pesquisa;

import model.Reserva;

import java.util.ArrayList;

public class ABB {
    private NoABB raiz;

    public ABB() {
        this.raiz = null;
    }

    // Carrega o arquivo na ABB
    public void inserir(Reserva reserva) {
        if (raiz == null) {
            raiz = new NoABB(reserva);
        } else {
            inserirRecursivo(raiz, reserva);
        }
    }

    private void inserirRecursivo(NoABB no, Reserva reserva) {
        int comparacao = reserva.getNome().compareToIgnoreCase(no.getNome());

        if (comparacao < 0) {
            if (no.getEsq() == null) {
                no.setEsq(new NoABB(reserva));
            } else {
                inserirRecursivo(no.getEsq(), reserva);
            }
        } else if (comparacao > 0) {
            if (no.getDir() == null) {
                no.setDir(new NoABB(reserva));
            } else {
                inserirRecursivo(no.getDir(), reserva);
            }
        } else {
            // Nomes iguais: adiciona à lista do nó
            no.getReservas().add(reserva);
        }
    }

    public void balancear() {
        ArrayList<NoABB> nosOrdenados = new ArrayList<>();
        emOrdem(raiz, nosOrdenados); // Lineariza a árvore
        this.raiz = construirArvoreBalanceada(nosOrdenados, 0, nosOrdenados.size() - 1); // Reconstrói balanceada
    }

    private void emOrdem(NoABB no, ArrayList<NoABB> lista) {
        if (no != null) {
            emOrdem(no.getEsq(), lista);
            lista.add(no);
            emOrdem(no.getDir(), lista);
        }
    }

    private NoABB construirArvoreBalanceada(ArrayList<NoABB> lista, int inicio, int fim) {
        if (inicio > fim) {
            return null;
        }
        int meio = (inicio + fim) / 2;
        NoABB no = lista.get(meio);

        no.setEsq(construirArvoreBalanceada(lista, inicio, meio - 1));
        no.setDir(construirArvoreBalanceada(lista, meio + 1, fim));

        return no;
    }

    // Realiza a pesquisa na ABB
    public ArrayList<Reserva> pesquisar(String nome) {
        NoABB resultado = buscarRecursivo(raiz, nome);
        if (resultado != null) {
            return resultado.getReservas();
        }
        return new ArrayList<>();
    }

    private NoABB buscarRecursivo(NoABB no, String nome) {
        if (no == null) {
            return null;
        }
        int comparacao = nome.compareToIgnoreCase(no.getNome());

        if (comparacao == 0) {
            return no;
        } else if (comparacao < 0) {
            return buscarRecursivo(no.getEsq(), nome);
        } else {
            return buscarRecursivo(no.getDir(), nome);
        }
    }
}
