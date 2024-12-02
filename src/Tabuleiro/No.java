package Tabuleiro;

public class No <T>{
    private T casa;
    private No<T> proximo;

    public No(T casa) {
        this.casa = casa;
        this.proximo = null;
    }

    public T getCasa() {
        return casa;
    }

    public void setCasa(T casa) {
        this.casa = casa;
    }

    public No<T> getProximo() {
        return proximo;
    }

    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return casa.toString();
    }
}
