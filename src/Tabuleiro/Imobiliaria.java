package Tabuleiro;

public class Imobiliaria {
    private Imovel[] imoveis;
    private int quantidadeImoveis;

    public Imobiliaria(int capacidade) {
        this.imoveis = new Imovel[capacidade];
        this.quantidadeImoveis = 0;
    }

    public int getQuantidadeImoveis() {
        return quantidadeImoveis;
    }

    public void cadastrarImovel(Imovel imovel){
        if (quantidadeImoveis < imoveis.length){
            imoveis[quantidadeImoveis] = imovel;
            quantidadeImoveis++;
        }else{
            System.out.println("Não foi possível realizar o cadastro do imóvel.");
        }
    }

    public Imovel[] getImoveis() {
        return imoveis;
    }

    public void setImoveis() {
        this.imoveis = imoveis;
    }

    public void setQuantidadeImoveis(int quantidadeImoveis) {
        this.quantidadeImoveis = quantidadeImoveis;
    }

    public void listarImoveis(){
        if (quantidadeImoveis == 0){
            System.out.println("Nenhum imóvel foi cadastrado.");
            return;
        } else {
            System.out.println("Lista de imóveis cadastrados: ");
            for (int i = 0; i < quantidadeImoveis; i++){
                System.out.println((i + 1) + " - " + imoveis[i].toString());
            }
        }
    }

    public void atualizarImovel(int indice, Imovel novoImovel){
        if (indice >= 0 && indice < quantidadeImoveis){
            imoveis[indice] = novoImovel;
            System.out.println("Imóvel atualizado com sucesso!");
        }else{
            System.out.println("Índice inválido! Não foi possível atualizar o imóvel.");
        }
    }

    public void removerImovel(int indice){
        if (indice >= 0 && indice < quantidadeImoveis) {
            for (int i = indice; i < quantidadeImoveis - 1; i ++){
                imoveis[i] = imoveis[i + 1];
            }
            imoveis[quantidadeImoveis - 1] = null;
            quantidadeImoveis --;
            System.out.println("Imóvel removido com sucesso!");;
        } else {
            System.out.println("índice inválido! Não foi possível remover o imóvel.");
        }

    }

    public void validarImoveis(){

        if (quantidadeImoveis < 10){
            System.out.println("Não foi possível inicializar o jogo. É preciso ter no mínimo 10 imóveis cadastrados.");
        } else {
            System.out.println("Jogo iniciado com sucesso!");
        }
    }
}
