package RubroNegra;

public class No<T> {
    protected  T info;
    public No<T> esq,dir,pai;
    protected Cor cor;

    public No(){
        this.info = null;
        this.esq = null;
        this.dir = null;
        this.pai = null;
        this.cor = Cor.NEGRA;
    }

}
