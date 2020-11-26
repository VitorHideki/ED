package BTree;

public class Pagina<T> {
    protected T[] chave;
    protected Pagina[] filhos;
    protected int numChaves;
    protected boolean folha;
    protected int nivel;

    public T[] getChave(int i) {
        return chave;
    }

    public void setChave(T[] chave) {
        this.chave =  chave;
    }

    public Pagina[] getFilhos() {
        return filhos;
    }

    public void setFilhos(Pagina[] filhos) {
        this.filhos = filhos;
    }

    public int getNumChaves() {
        return numChaves;
    }

    public void setNumChaves(int numChaves) {
        this.numChaves = numChaves;
    }

    public boolean isFolha() {
        return folha;
    }

    public void setFolha(boolean folha) {
        this.folha = folha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Pagina(int ordem){
        this.chave = (T[]) new Comparable[(ordem*2)-1];
        this.filhos = new Pagina[ordem*2];
        this.numChaves = 0;
        this.folha = false;
        this.nivel = 0;
    }
}
