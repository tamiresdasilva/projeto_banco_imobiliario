package Jogadores;

import Tabuleiro.Imovel;

import java.util.ArrayList;

public class Jogador {
    private String nome;
    private double saldoBancario;
    private int posicao;
    private ArrayList<Imovel> propriedadesPossuidas;

    public Jogador(String nome, double saldoInicial) {
        this.nome = nome;
        this.saldoBancario = saldoInicial;
        this.posicao = 0; // Começa na posição inicial
        this.propriedadesPossuidas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldoBancario() {
        return saldoBancario;
    }

    public void setSaldoBancario(double saldoBancario) {
        this.saldoBancario = saldoBancario;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public ArrayList<Imovel> getPropriedadesPossuidas() {
        return propriedadesPossuidas;
    }

    public void setPropriedadesPossuidas(ArrayList<Imovel> propriedadesPossuidas) {
        this.propriedadesPossuidas = propriedadesPossuidas;
    }

    public void adicionarPropriedade(Imovel imovel){
        propriedadesPossuidas.add(imovel);
    }

    public void removerPropriedade(Imovel imovel){
        propriedadesPossuidas.remove(imovel);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append("\n").append("Saldo: R$ ").append(saldoBancario).append("\n").append("Posição: ").append(posicao).append("\n").append("Propriedades: \n");

        if (propriedadesPossuidas.isEmpty()){
            sb.append("Nenhuma\n");
        } else{
            for (Imovel propriedade : propriedadesPossuidas){
                sb.append("  - ").append(propriedade).append("\n");
            }
        }
        return sb.toString();
    }
}
