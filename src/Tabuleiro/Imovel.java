package Tabuleiro;

public class Imovel {
    private String tipo;
    private String nome;
    private double valorImovel;
    private double aluguel;
    private String proprietario;

    public Imovel(String nome, double valorImovel, double aluguel, String proprietario, String tipo) {
        this.tipo = tipo;
        this.nome = nome;
        this.valorImovel = valorImovel;
        this.aluguel = aluguel;
        this.proprietario = proprietario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
    }

    public double getAluguel() {
        return aluguel;
    }

    public void setAluguel(double aluguel) {
        this.aluguel = aluguel;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }


    @Override
    public String toString() {
        return "Tipo: " + tipo + " | Nome: " + nome + " | Valor: R$ " + valorImovel + " | Aluguel: R$ " + aluguel + " | Proprietário: " + (proprietario.isEmpty() ? "Nenhum" : proprietario);
    }
}
