package principal;

import java.io.IOException;
import java.util.ArrayList;
import model.Reserva;
import ordenacao.*;
import utils.*;

public class Principal {
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
    private static LeitorGravadorArquivo leitorGravador = new LeitorGravadorArquivo();

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

        // ETAPA 5: AVL (a ser implementada)
        System.out.println("\n[5/6] AVL - A IMPLEMENTAR");

        // ETAPA 6: Hashing (a ser implementada)
        System.out.println("\n[6/6] Hashing - A IMPLEMENTAR");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("PROCESSAMENTO CONCLUÍDO!");
        System.out.println("=".repeat(60));
    }

    private static void executarHeapSort(String caminhoArquivo, Heapsort heap) throws IOException {
        String nomeArquivo = extrairNomeArquivo(caminhoArquivo);
        String nomeSaida = "arquivos_saida/heap" + nomeArquivo.substring(7);

        long tempoTotal = 0;

        for (int i = 0; i < 5; i++) {
            long inicio = System.nanoTime();

            ArrayList<Reserva> lista = leitorGravador.carregar(caminhoArquivo);
            Reserva[] vetor = lista.toArray(new Reserva[0]);

            heap.ordenar(vetor);

            leitorGravador.gravar(vetor, nomeSaida);

            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
        }

        double media = tempoTotal / 5.0 / 1_000_000.0;
        System.out.printf("  %-25s -> %.3f ms%n", nomeArquivo, media);
    }

    private static void executarQuickSort(String caminhoArquivo, Quicksort quick) throws IOException {
        String nomeArquivo = extrairNomeArquivo(caminhoArquivo);
        String nomeSaida = "arquivos_saida/quick" + nomeArquivo.substring(7);

        long tempoTotal = 0;

        for (int i = 0; i < 5; i++) {
            long inicio = System.nanoTime();

            ArrayList<Reserva> lista = leitorGravador.carregar(caminhoArquivo);
            Reserva[] vetor = lista.toArray(new Reserva[0]);

            quick.ordenar(vetor, 0, vetor.length - 1);

            leitorGravador.gravar(vetor, nomeSaida);

            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
        }
        double media = tempoTotal / 5.0 / 1_000_000.0;
        System.out.printf("  %-25s -> %.3f ms%n", nomeArquivo, media);
    }

    private static void executarQuickSortInsercao(String caminhoArquivo, QuicksortInsercao quickInsercao) throws IOException {
        String nomeArquivo = extrairNomeArquivo(caminhoArquivo);
        String nomeSaida = "arquivos_saida/QuickIns" + nomeArquivo.substring(7);

        long tempoTotal = 0;

        for (int i = 0; i < 5; i++) {
            long inicio = System.nanoTime();

            ArrayList<Reserva> lista = leitorGravador.carregar(caminhoArquivo);
            Reserva[] vetor = lista.toArray(new Reserva[0]);

            quickInsercao.ordenar(vetor, 0, vetor.length - 1);

            leitorGravador.gravar(vetor, nomeSaida);

            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
        }
        double media = tempoTotal / 5.0 / 1_000_000.0;
        System.out.printf("  %-25s -> %.3f ms%n", nomeArquivo, media);
    }

    private static String extrairNomeArquivo(String caminho) {
        return caminho.substring(caminho.lastIndexOf("/") + 1);
    }
}

