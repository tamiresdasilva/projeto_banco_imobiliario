import Jogadores.Jogador;
import Jogadores.PlayerGerenciador;
import Tabuleiro.Imobiliaria;
import Tabuleiro.Imovel;
import Tabuleiro.Tabuleiro;

import java.util.Random;
import java.util.Scanner;

public class Jogo {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        PlayerGerenciador playerGerenciador = new PlayerGerenciador();
        Imobiliaria imobiliaria = new Imobiliaria(40);
        Tabuleiro<Imovel> tabuleiro = new Tabuleiro<Imovel>();

        int numeroDeRodadas = 10;
        double saldoInicial = 5000;
        int numJogadores = 0;
        int numImoveis = 0;

        adicionarImoveisIniciais(imobiliaria, tabuleiro);

        //Configurações Iniciais
        while (true) {
            System.out.println("Bem-vindo(a) ao Banco Imobiliário!");
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Cadastrar imóveis");
            System.out.println("2 - Listar imóveis cadastrados");
            System.out.println("3 - Cadastrar jogadores");
            System.out.println("4 - Listar jogadores cadastrados");
            System.out.println("5 - Iniciar o jogo");
            System.out.println("6 - Sair");

            int opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    while (imobiliaria.getQuantidadeImoveis() < 40) {
                        System.out.println("Digite o nome do imóvel: ");
                        String nomeImovel = leitor.nextLine();

                        if (nomeImovel.isEmpty()){
                            System.out.println("O nome do imóvel não pode estar vazio. Digite novamente!");
                            continue;
                        }
                        double valorImovel = 0;
                        boolean valorValido = false;
                        while (!valorValido){
                            System.out.println("Digite o valor do imóvel: ");
                            try{
                                valorImovel = leitor.nextDouble();
                                leitor.nextLine();
                                valorValido = true;
                            } catch (Exception e){
                                System.out.println("Valor inválido! Por favor, digite um número válido.");
                                leitor.nextLine();
                            }
                        }

                        double aluguelImovel = 0;
                        boolean aluguelValido = false;

                        while (!aluguelValido) {
                            System.out.println("Digite o valor do aluguel: ");
                            try {
                                aluguelImovel = leitor.nextDouble();
                                leitor.nextLine(); // Limpa a quebra de linha deixada por nextDouble
                                aluguelValido = true; // Se a conversão for bem-sucedida, sai do loop
                            } catch (Exception e) {
                                System.out.println("Valor inválido! Por favor, digite um número válido.");
                                leitor.nextLine(); // Limpa o buffer de entrada
                            }
                        }

                        Imovel imovel = new Imovel(nomeImovel, valorImovel, aluguelImovel, "", "Imóvel");
                        imobiliaria.cadastrarImovel(imovel);
                        // Pergunta se o usuário deseja cadastrar outro imóvel
                        if (imobiliaria.getQuantidadeImoveis() < 40) {
                            System.out.println("Deseja cadastrar outro imóvel? (S/N)");
                            String resposta = leitor.nextLine().toUpperCase();

                            if (resposta.equals("N")) {
                                System.out.println("Cadastro de imóveis finalizado.");
                                break; // Sai do loop se a resposta for "N"
                            }
                        } else {
                            System.out.println("Número máximo de imóveis cadastrados atingido.");
                            break; // Sai do loop se o número máximo for atingido
                        }
                    }
                    break;
                case 2:
                    imobiliaria.listarImoveis();
                    break;
                case 3:
                    while (playerGerenciador.getNumeroDeJogadores() < 6) {
                        System.out.println("Digite o nome do jogador: ");
                        String nomeJogador = leitor.nextLine();

                        if (!nomeJogador.isEmpty()) {
                            Jogador jogador = new Jogador(nomeJogador, saldoInicial);
                            playerGerenciador.cadastrarJogador(jogador);

                            System.out.println("Deseja cadastrar outro jogador? (S/N)");
                            String resposta = leitor.nextLine().toUpperCase();
                            if (resposta.equals("N")){
                                if (playerGerenciador.getNumeroDeJogadores() >= 2){
                                    break;
                                } else {
                                    System.out.println("É preciso ter no mínimo 2 jogadores cadastrados!");
                                    break;
                                }
                            } else if (resposta.equals("S")){
                                continue;
                            }else{
                                System.out.println("Opção inválida! Digite a letra S para sim ou N para não.");
                                break;
                            }
                        } else {
                            System.out.println("O nome não pode estar vazio!");
                        }
                    }
                    break;
                case 4:
                    playerGerenciador.listarJogadores();
                    break;
                case 5:
                    numImoveis = imobiliaria.getQuantidadeImoveis();
                    numJogadores = playerGerenciador.getNumeroDeJogadores();

                    if (numImoveis < 10 || numJogadores < 2){
                        System.out.println("Imóveis ou jogadores insuficientes para iniciar o jogo!");
                        break;
                    }
                    for (int rodada = 0; rodada < numeroDeRodadas; rodada++){
                        System.out.println("\nRodada: " + (rodada +1));

                        for (Jogador jogador : playerGerenciador.getJogadores()){
                            System.out.println("Vez de: " + jogador.getNome());

                            int dado = new Random().nextInt(6) + 1;
                            System.out.println("O dado tirado foi: " + dado);

                            int novaPosicao = (jogador.getPosicao() + dado) % tabuleiro.getTamanho();

                            verificarInicio(jogador, novaPosicao, tabuleiro.getTamanho());


                            Imovel casaAtual = tabuleiro.moverJogador(dado);
                            System.out.println("Você caiu na casa: " + casaAtual.getNome());

                            if (casaAtual.getTipo().equals("Início")){
                                jogador.setSaldoBancario(jogador.getSaldoBancario() + 10000);
                                System.out.println("Você recebeu RS$ 10.000 de salário.");
                            }else if (casaAtual.getTipo().equals(jogador.getNome())){
                                System.out.println("Você já é o proprietário desse imóvel.");
                            }else if (casaAtual.getTipo().equals("Imóvel") && casaAtual.getProprietario().isEmpty()){
                                System.out.println("Deseja comprar o imóvel? (S/N)");
                                String resposta = leitor.nextLine();

                                if (resposta.equalsIgnoreCase("S") && jogador.getSaldoBancario() >= casaAtual.getValorImovel()){
                                    jogador.setSaldoBancario(jogador.getSaldoBancario() - casaAtual.getValorImovel());
                                    jogador.adicionarPropriedade(casaAtual);
                                    casaAtual.setProprietario(jogador.getNome());
                                    System.out.println("Imóvel comprado!\n");
                                } else{
                                    System.out.println("Saldo insuficiente ou compra cancelada.\n");
                                }
                            } else if (casaAtual.getTipo().equals("Imposto")) {
                                double imposto = jogador.getSaldoBancario() * 0.05;
                                jogador.setSaldoBancario(jogador.getSaldoBancario() - imposto);
                                System.out.println("Você pagou R$ " + imposto + " de imposto.\n");
                            } else if (casaAtual.getTipo().equals("\nRestituição")) {
                                double restituicao = 1000 * 0.1;
                                jogador.setSaldoBancario(jogador.getSaldoBancario() + restituicao);
                                System.out.println("\nVocê recebeu R$ " + restituicao + " de restituição.");
                            }
                        }
                        for (Jogador jogador : playerGerenciador.getJogadores()){
                            System.out.println(jogador);
                        }
                    }
                    System.out.println("O jogo acabou!");
                    break;
                case 6:
                    System.out.println("Fechando o jogo...");
                    return;
                default:
                    System.out.println("Opção digitada inválida, tente novamente!");
                    break;
            }
        }

    }

    private static void adicionarImoveisIniciais (Imobiliaria imobiliaria, Tabuleiro<Imovel> tabuleiro){
        Imovel imovel1 = new Imovel("Casa do Bosque", 20000, 1100, "", "Imóvel");
        Imovel imovel2 = new Imovel("Apartamento Central", 35000, 1800, "", "Imóvel");
        Imovel imovel3 = new Imovel("Vila das Flores", 400000, 2200, "", "Imóvel");
        Imovel imovel4 = new Imovel("Pousada da Praia", 500000, 2700, "", "Imóvel");
        Imovel imovel5 = new Imovel("Mansão da Colina", 60000, 3300, "", "Imóvel");
        Imovel imovel6 = new Imovel("Residência do Lago", 450000, 2500, "", "Imóvel");
        Imovel imovel7 = new Imovel("Cobertura Diamante", 70000, 3700, "", "Imóvel");
        Imovel imovel8 = new Imovel("Edifício Horizonte", 550000, 2900, "", "Imóvel");
        Imovel imovel9 = new Imovel("Chácara do Sol", 300000, 1600, "", "Imóvel");
        Imovel imovel10 = new Imovel("Fazenda Boa Vista", 25000, 1300, "", "Imóvel");
        Imovel inicio = new Imovel("Início", 0, 0, "", "Início");
        Imovel imposto = new Imovel("Imposto", 0, 0, "", "Imposto");
        Imovel restituicao = new Imovel("Restituição", 0, 0, "", "Restituição");

        imobiliaria.cadastrarImovel(imovel1);
        imobiliaria.cadastrarImovel(imovel2);
        imobiliaria.cadastrarImovel(imovel3);
        imobiliaria.cadastrarImovel(imovel4);
        imobiliaria.cadastrarImovel(imovel5);
        imobiliaria.cadastrarImovel(imovel6);
        imobiliaria.cadastrarImovel(imovel7);
        imobiliaria.cadastrarImovel(imovel8);
        imobiliaria.cadastrarImovel(imovel9);
        imobiliaria.cadastrarImovel(imovel10);
        imobiliaria.cadastrarImovel(inicio);
        imobiliaria.cadastrarImovel(imposto);
        imobiliaria.cadastrarImovel(restituicao);

        tabuleiro.adicionarCasa(imovel1);
        tabuleiro.adicionarCasa(imovel2);
        tabuleiro.adicionarCasa(imovel3);
        tabuleiro.adicionarCasa(imovel4);
        tabuleiro.adicionarCasa(imovel5);
        tabuleiro.adicionarCasa(imovel6);
        tabuleiro.adicionarCasa(imovel7);
        tabuleiro.adicionarCasa(imovel8);
        tabuleiro.adicionarCasa(imovel9);
        tabuleiro.adicionarCasa(imovel10);
        tabuleiro.adicionarCasa(inicio);
        tabuleiro.adicionarCasa(imposto);
        tabuleiro.adicionarCasa(restituicao);
    }

    public static void verificarInicio(Jogador jogador, int novaPosicao, int tamanhoTabuleiro) {
        int posicaoAtual = jogador.getPosicao();
        jogador.setPosicao(novaPosicao);

        if (novaPosicao < posicaoAtual) { // Passou pelo início (deu uma volta completa)
            jogador.setSaldoBancario(jogador.getSaldoBancario() + 55000); // Adiciona salário
            System.out.println(jogador.getNome() + " passou pelo Início e recebeu R$ 1000 de salário.\n");
        }
    }

}