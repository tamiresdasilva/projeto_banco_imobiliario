package Tabuleiro;

import Jogadores.Jogador;

public class Tabuleiro<T> {
    private Jogador jogador;
    private No<Imovel> inicio;
    private No<Imovel> atual;
    private int tamanho;

    public Tabuleiro(){
        this.inicio = null;
        this.atual = null;
        this.tamanho = 0;
    }

    //
    public static boolean verificarInicio(Jogador jogador, int novaPosicao, int tamanhoTabuleiro) {
        // Verifica se o jogador está na posição inicial (posição 0)
        if (novaPosicao == 0) {
            return true;
        }
        return false;
    }

    public void adicionarCasa(Imovel casa){
        No<Imovel> novoNo = new No<>(casa);
        if (inicio == null) {
            inicio = novoNo;
            novoNo.setProximo(inicio);
            atual = inicio;
        }
        else {
            No<Imovel> noAtual = inicio;
            while (noAtual.getProximo() != inicio){
                noAtual = noAtual.getProximo();
            }
            noAtual.setProximo(novoNo);
            novoNo.setProximo(inicio);
        }
        tamanho++;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Imovel obterCasa (int posicao){
        if (posicao < 0 || posicao >= tamanho){
            System.out.println("Posição inválida!");
        }
        No<Imovel> noAtual =  inicio;
        for (int i = 0; i < posicao; i++){
            noAtual = noAtual.getProximo();
        }
        return noAtual.getCasa();
    }

    public Imovel moverJogador(int passos){
        if (inicio == null || tamanho == 0){
            System.out.println("Tabuleiro vazio, não é possível mover.");
            return null;
        } if (atual == null){
            atual = inicio;
        }
        for (int i = 0; i < passos; i ++){
            atual = atual.getProximo();
        }
        return atual.getCasa();
    }






    public void listarCasas(){
        if (inicio == null){
            System.out.println("O tabuleiro está vazio!");
            return;
        }
        No<Imovel> noAtual = inicio;
        System.out.println("Casas no tabuleiro: ");
        do {
            System.out.println(noAtual.getCasa().toString());
            noAtual = noAtual.getProximo();
        } while (noAtual != inicio);
    }
}
