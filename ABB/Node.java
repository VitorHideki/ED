package ABB;

public class Node<T>{
    public T info;
    public Node<T> esq,dir;

    public Node(T info){
        this.info = info;
        this.esq = null;
        this.dir = null;
    }
}
