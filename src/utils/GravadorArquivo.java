package utils;

import java.io.*;
import java.util.ArrayList;
import model.Reserva;

public class GravadorArquivo {
    public void gravarArquivo(Reserva[] vetor, String nomeArquivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Reserva r : vetor) {
                bw.write(r.toString());
                bw.newLine();
            }
        }
    }

    public void gravarResultadoPesquisa(ArrayList<String> nomes,
                                        ArrayList<ArrayList<Reserva>> resultados,
                                        String nomeArquivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (int i = 0; i < nomes.size(); i++) {
                String nome = nomes.get(i);
                ArrayList<Reserva> reservas = resultados.get(i);

                bw.write("NOME " + nome + ":");
                bw.newLine();

                if (reservas.isEmpty()) {
                    bw.write("NÃO TEM RESERVA");
                    bw.newLine();
                } else {
                    for (Reserva r : reservas) {
                        bw.write(r.toStringPesquisa());
                        bw.newLine();
                    }
                    bw.write("TOTAL: " + reservas.size() + " reserva" + (reservas.size() > 1 ? "s" : ""));
                    bw.newLine();
                }

                bw.newLine(); // Linha em branco entre nomes
            }
        }
    }
}
