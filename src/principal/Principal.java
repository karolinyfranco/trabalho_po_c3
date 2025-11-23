package principal;

import java.io.IOException;
import java.util.ArrayList;
import model.Reserva;
import ordenacao.*;
import pesquisa.AVL;
import pesquisa.Hashing;
import utils.*;

public class Principal {
    private static final String ARQUIVO_NOMES = "arquivos_entrada/nome.txt";
    private static final String[] ARQUIVOS_ENTRADA = {
            "arquivos_entrada/reserva1000alea.txt",
            "arquivos_entrada/reserva1000inv.txt",
            "arquivos_entrada/reserva1000ord.txt",
            "arquivos_entrada/reserva5000alea.txt",
            "arquivos_entrada/reserva5000inv.txt",
            "arquivos_entrada/reserva5000ord.txt",
            "arquivos_entrada/reserva10000alea.txt",
            "arquivos_entrada/reserva10000inv.txt",
            "arquivos_entrada/reserva10000ord.txt",
            "arquivos_entrada/reserva50000alea.txt",
            "arquivos_entrada/reserva50000inv.txt",
            "arquivos_entrada/reserva50000ord.txt"
    };
    private static LeitorArquivo leitor = new LeitorArquivo();
    private static GravadorArquivo gravador = new GravadorArquivo();
    private static PrimoUtils primo = new PrimoUtils();

    public static void main(String[] args) throws IOException {
        Heapsort heap = new Heapsort();
        Quicksort quick = new Quicksort();
        QuicksortInsercao quickInsercao = new QuicksortInsercao();

        System.out.println("=".repeat(60));
        System.out.println("TRABALHO DE PESQUISA E ORDENAÇÃO");
        System.out.println("=".repeat(60));

        // ETAPA 1: HeapSort
        System.out.println("\n[1/6] Executando HeapSort...");
        for (String arquivo : ARQUIVOS_ENTRADA) {
            executarHeapSort(arquivo, heap);
        }

        // ETAPA 2: QuickSort
        System.out.println("\n[2/6] Executando QuickSort...");
        for (String arquivo : ARQUIVOS_ENTRADA) {
            executarQuickSort(arquivo, quick);
        }

        // ETAPA 3: QuickSort com Inserção
        System.out.println("\n[3/6] Executando QuickSort com Inserção...");
        for (String arquivo : ARQUIVOS_ENTRADA) {
            executarQuickSortInsercao(arquivo, quickInsercao);
        }

        // ETAPA 4: ABB (a ser implementada)
        System.out.println("\n[4/6] ABB - A IMPLEMENTAR");

        // ETAPA 5: AVL
        System.out.println("\n[5/6] Executando AVL...");
        for (String arquivo : ARQUIVOS_ENTRADA) {
            executarAVL(arquivo);
        }

        // ETAPA 6: Hashing
        System.out.println("\n[6/6] Executando Hashing...");
        for (String arquivo : ARQUIVOS_ENTRADA) {
            executarHashing(arquivo);
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("PROCESSAMENTO CONCLUÍDO!");
        System.out.println("=".repeat(60));
    }

    public static void executarHeapSort(String caminhoArquivo, Heapsort heap) throws IOException {
        String nomeArquivo = leitor.extrairNomeArquivo(caminhoArquivo);
        String nomeSaida = "arquivos_saida/heap" + nomeArquivo.substring(7);

        long tempoTotal = 0;

        for (int i = 0; i < 5; i++) {
            long inicio = System.nanoTime();

            ArrayList<Reserva> lista = leitor.carregar(caminhoArquivo);
            Reserva[] vetor = lista.toArray(new Reserva[0]);

            heap.ordenar(vetor);

            gravador.gravarArquivo(vetor, nomeSaida);

            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
        }

        double media = tempoTotal / 5.0 / 1_000_000.0;
        System.out.printf("  %-25s -> %.3f ms%n", nomeArquivo, media);
    }

    public static void executarQuickSort(String caminhoArquivo, Quicksort quick) throws IOException {
        String nomeArquivo = leitor.extrairNomeArquivo(caminhoArquivo);
        String nomeSaida = "arquivos_saida/quick" + nomeArquivo.substring(7);

        long tempoTotal = 0;

        for (int i = 0; i < 5; i++) {
            long inicio = System.nanoTime();

            ArrayList<Reserva> lista = leitor.carregar(caminhoArquivo);
            Reserva[] vetor = lista.toArray(new Reserva[0]);

            quick.ordenar(vetor, 0, vetor.length - 1);

            gravador.gravarArquivo(vetor, nomeSaida);

            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
        }
        double media = tempoTotal / 5.0 / 1_000_000.0;
        System.out.printf("  %-25s -> %.3f ms%n", nomeArquivo, media);
    }

    public static void executarQuickSortInsercao(String caminhoArquivo, QuicksortInsercao quickInsercao) throws IOException {
        String nomeArquivo = leitor.extrairNomeArquivo(caminhoArquivo);
        String nomeSaida = "arquivos_saida/QuickIns" + nomeArquivo.substring(7);

        long tempoTotal = 0;

        for (int i = 0; i < 5; i++) {
            long inicio = System.nanoTime();

            ArrayList<Reserva> lista = leitor.carregar(caminhoArquivo);
            Reserva[] vetor = lista.toArray(new Reserva[0]);

            quickInsercao.ordenar(vetor, 0, vetor.length - 1);

            gravador.gravarArquivo(vetor, nomeSaida);

            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
        }
        double media = tempoTotal / 5.0 / 1_000_000.0;
        System.out.printf("  %-25s -> %.3f ms%n", nomeArquivo, media);
    }

    public static void executarAVL(String caminhoArquivo) throws IOException {
        String nomeArquivo = leitor.extrairNomeArquivo(caminhoArquivo);
        String nomeSaida = "arquivos_saida/AVL" + nomeArquivo.substring(7);

        ArrayList<String> nomesPesquisa = leitor.carregarNomes(ARQUIVO_NOMES);

        long tempoTotal = 0;

        for (int i = 0; i < 5; i++) {
            long inicio = System.nanoTime();

            ArrayList<Reserva> lista = leitor.carregar(caminhoArquivo);

            AVL avl = new AVL();

            for (Reserva reserva : lista) {
                avl.inserir(reserva);
            }

            ArrayList<ArrayList<Reserva>> resultados = new ArrayList<>();
            for (String nome : nomesPesquisa) {
                ArrayList<Reserva> resultado = avl.pesquisar(nome);
                resultados.add(resultado);
            }

            gravador.gravarResultadoPesquisa(nomesPesquisa, resultados, nomeSaida);

            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
        }

        double media = tempoTotal / 5.0 / 1_000_000.0;
        System.out.printf("  %-25s -> %.3f ms%n", nomeArquivo, media);
    }

    public static void executarHashing(String caminhoArquivo) throws IOException {
        String nomeArquivo = leitor.extrairNomeArquivo(caminhoArquivo);
        String nomeSaida = "arquivos_saida/Hash" + nomeArquivo.substring(7);

        ArrayList<String> nomesPesquisa = leitor.carregarNomes(ARQUIVO_NOMES);

        long tempoTotal = 0;

        for (int i = 0; i < 5; i++) {
            long inicio = System.nanoTime();

            ArrayList<Reserva> lista = leitor.carregar(caminhoArquivo);

            int tamanhoTabela = primo.proximoPrimo(lista.size());
            Hashing hash = new Hashing(tamanhoTabela);

            for (Reserva reserva : lista) {
                hash.inserir(reserva);
            }

            ArrayList<ArrayList<Reserva>> resultados = new ArrayList<>();
            for (String nome : nomesPesquisa) {
                ArrayList<Reserva> resultado = hash.pesquisar(nome);
                resultados.add(resultado);
            }

            gravador.gravarResultadoPesquisa(nomesPesquisa, resultados, nomeSaida);

            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
        }

        double media = tempoTotal / 5.0 / 1_000_000.0;
        System.out.printf("  %-25s -> %.3f ms%n", nomeArquivo, media);
    }
}

