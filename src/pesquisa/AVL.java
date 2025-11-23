package pesquisa;

import model.Reserva;

import java.util.ArrayList;

public class AVL {
    private NoAVL raiz;

    public AVL() {
        this.raiz = null;
    }

    // Métodos auxiliares
    private int altura(NoAVL no) {
        return (no == null) ? 0 : no.getAltura();
    }

    private int fatorBalanceamento(NoAVL no) {
        return (no == null) ? 0 : altura(no.getEsquerda()) - altura(no.getDireita());
    }

    private void atualizarAltura(NoAVL no) {
        no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));
    }

    // Rotações
    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.getEsquerda();
        NoAVL T2 = x.getDireita();

        // Realiza a rotação
        x.setDireita(y);
        y.setEsquerda(T2);


        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.getDireita();
        NoAVL T2 = y.getEsquerda();

        // Realiza a rotação
        y.setEsquerda(x);
        x.setDireita(T2);


        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    // Inserção
    public void inserir(Reserva reserva) {
        raiz = inserirRecursivo(raiz, reserva);
    }

    private NoAVL inserirRecursivo(NoAVL no, Reserva reserva) {
        if (no == null) {
            return new NoAVL(reserva);
        }

        int comparacao = reserva.getNome().compareToIgnoreCase(no.getNome());

        if (comparacao < 0) {
            no.setEsquerda(inserirRecursivo(no.getEsquerda(), reserva));
        } else if (comparacao > 0) {
            no.setDireita(inserirRecursivo(no.getDireita(), reserva));
        } else {
            // Nomes iguais: adiciona a reserva à lista do nó existente
            no.getReservas().add(reserva);
            // Não precisa rebalancear, apenas retorna o nó
            return no;
        }

        // Atualiza a altura do nó atual
        atualizarAltura(no);

        // Obtém o fator de balanceamento
        int balanceamento = fatorBalanceamento(no);

        // Caso Esquerda-Esquerda (LL)
        if (balanceamento > 1 && reserva.getNome().compareToIgnoreCase(no.getEsquerda().getNome()) < 0) {
            return rotacaoDireita(no);
        }

        // Caso Direita-Direita (RR)
        if (balanceamento < -1 && reserva.getNome().compareToIgnoreCase(no.getDireita().getNome()) > 0) {
            return rotacaoEsquerda(no);
        }

        // Caso Esquerda-Direita (LR)
        if (balanceamento > 1 && reserva.getNome().compareToIgnoreCase(no.getEsquerda().getNome()) > 0) {
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            return rotacaoDireita(no);
        }

        // Caso Direita-Esquerda (RL)
        if (balanceamento < -1 && reserva.getNome().compareToIgnoreCase(no.getDireita().getNome()) < 0) {
            no.setDireita(rotacaoDireita(no.getDireita()));
            return rotacaoEsquerda(no);
        }

        // Retorna o nó (balanceado ou não)
        return no;
    }

    // Pesquisa
    public ArrayList<Reserva> pesquisar(String nome) {
        return pesquisarRecursivo(raiz, nome);
    }

    private ArrayList<Reserva> pesquisarRecursivo(NoAVL no, String nome) {
        if (no == null) {
            return new ArrayList<>(); // Não encontrado
        }

        int comparacao = nome.compareToIgnoreCase(no.getNome());

        if (comparacao < 0) {
            return pesquisarRecursivo(no.getEsquerda(), nome);
        } else if (comparacao > 0) {
            return pesquisarRecursivo(no.getDireita(), nome);
        } else {
            // Encontrado: retorna a lista de reservas
            return no.getReservas();
        }
    }
}
