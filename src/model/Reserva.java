package model;

public class Reserva {
    private String codigo;
    private String nome;
    private String voo;
    private String data;
    private String assento;

    public Reserva(String linha) {
        String[] partes = linha.split(";");
        this.codigo = partes[0];
        this.nome = partes[1];
        this.voo = partes[2];
        this.data = partes[3];
        this.assento = partes[4];
    }

    public String getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getVoo() {
        return voo;
    }
    public String getData() {
        return data;
    }
    public String getAssento() {
        return assento;
    }

    @Override
    public String toString() {
        return codigo + ";" + nome + ";" + voo + ";" + data + ";" + assento;
    }

    // Método para formato de pesquisa
    public String toStringPesquisa() {
        return "Reserva: " + codigo + " Voo: " + voo + " Data: " + data + " Assento: " + assento;
    }
}
