package utils;

import java.io.*;
import java.util.ArrayList;
import model.Reserva;

public class LeitorGravadorArquivo {
    public ArrayList<Reserva> carregar(String nomeArquivo) throws IOException {
        ArrayList<Reserva> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                lista.add(new Reserva(linha));
            }
        }
        return lista;
    }

    public ArrayList<String> carregarNomes(String nomeArquivo) throws IOException {
        ArrayList<String> nomes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                nomes.add(linha.trim());
            }
        }
        return nomes;
    }

    public void gravar(Reserva[] vetor, String nomeArquivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Reserva r : vetor) {
                bw.write(r.toString());
                bw.newLine();
            }
        }
    }
}
