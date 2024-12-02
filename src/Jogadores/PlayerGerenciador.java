package Jogadores;

import Tabuleiro.Imovel;

import java.util.ArrayList;

public class PlayerGerenciador {
    private ArrayList<Jogador> jogadores;

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public PlayerGerenciador(){
        this.jogadores = new ArrayList<>();
    }

    public int getNumeroDeJogadores() {
        return jogadores.size();
    }

    public void cadastrarJogador(Jogador jogador){
        jogadores.add(jogador);
        System.out.println("Jogador(a) " + jogador.getNome() + " cadastrado(a) com sucesso!");
    }

    public void listarJogadores(){
        if (jogadores.isEmpty()){
            System.out.println("Nenhum jogador cadastrado.");
        } else{
            System.out.println("Jogadores cadastrados: ");
            for (Jogador jogador : jogadores){
                System.out.println(jogador);
            }
        }
    }

    public void atualizarJogador(int indice, Jogador jogadorAtualizado) {
        if (indice >= 0 && indice < jogadores.size()) {
            jogadores.set(indice, jogadorAtualizado);
            System.out.println("Jogador atualizado com sucesso!");
        } else {
            System.out.println("Índice inválido! Não foi possível atualizar o jogador.");
        }
    }

    public void removerJogadorPorIndice(int indice) {
        if (indice >= 0 && indice < jogadores.size()) {
            Jogador jogadorRemovido = jogadores.remove(indice);
            System.out.println("Jogador " + jogadorRemovido.getNome() + " removido com sucesso!");
        } else {
            System.out.println("Índice inválido! Não foi possível remover o jogador.");
        }
    }


    public Jogador getJogador(int indice) {
        if (indice >= 0 && indice < jogadores.size()) {
            return jogadores.get(indice);
        }
        return null;
    }

    public void removerJogador(Jogador jogador){
        if (jogadores.remove(jogador)){
            System.out.println("Jogador " + jogador.getNome());
        } else {
            System.out.println("Jogador não foi encontrado.");
        }
    }
}
