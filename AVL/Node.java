package AVL;

public class Node<T> {
    protected int altura;
    protected T valor;
    protected Node<T> esquerda, direita;


    public Node(T valor, Node esq, Node dir) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        altura = 1;
    }

    public Node(T valor){
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
}
