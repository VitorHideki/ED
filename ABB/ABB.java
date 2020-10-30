package ABB;

public class ABB<T extends Comparable<T>> {
    public Node<T> getRaiz() {
        return raiz;
    }

    private Node<T> raiz = new Node<T>(null);
    private Node info;

    public ABB() {
        this.info = null;
        this.raiz = new Node<T>(null);
    }

    public boolean insere(T valor) {
        Node<T> no = raiz;

        while (no.info != null) {
            if (no.info.compareTo(valor) < 0) {
                no = no.dir;
            } else if (no.info.compareTo(valor) > 0) {
                no = no.esq;
            } else {
                return false;
            }
        }
        no.info = valor;
        no.esq = new Node<T>(null);
        no.dir = new Node<T>(null);
        return true;
    }

    public void removeUmUnicoFilho(Node ponteiro) {
        ponteiro.info = ponteiro.esq.info;
        ponteiro.dir = ponteiro.esq.dir;
        ponteiro.esq = ponteiro.esq.esq;
    }

    public void removeNoFolha(Node<T> ponteiro) {
        if (ponteiro.dir.info == null) {
            ponteiro.info = ponteiro.esq.info;
            ponteiro.dir = ponteiro.esq.dir;
            ponteiro.esq = ponteiro.esq.esq;
        } else {
            ponteiro.info = ponteiro.dir.info;
            ponteiro.esq = ponteiro.dir.esq;
            ponteiro.dir = ponteiro.dir.dir;
        }
    }

    public Object remove(T valor) {
        Object ret = null;
        Node<T> ponteiro = this.raiz;
        while (ponteiro.info != null) {
            if (ponteiro.info.compareTo(valor) < 0) {
                ponteiro = ponteiro.dir;
            } else if (ponteiro.info.compareTo(valor) > 0) {
                ponteiro = ponteiro.esq;
            } else {
                ret = ponteiro.info;
                if (ponteiro.esq.info != null && ponteiro.dir.info != null) {
                    ponteiro.info = (T) remMaiorEsq(ponteiro);
                } else if (ponteiro.dir.info == null) {
                    ponteiro.info = ponteiro.esq.info;
                    ponteiro.dir = ponteiro.esq.dir;
                    ponteiro.esq = ponteiro.esq.esq;
                } else {
                    ponteiro.info = ponteiro.dir.info;
                    ponteiro.esq = ponteiro.dir.esq;
                    ponteiro.dir = ponteiro.dir.dir;
                }
            }
        }
        return (T) ret;
    }

    private Object remMaiorEsq(Node<T> ponteiro) {
        ponteiro = ponteiro.esq;
        while (ponteiro.dir.info != null) {
            ponteiro = ponteiro.dir;
        }
        Object ret = ponteiro.info;
        ponteiro.info = ponteiro.esq.info;
        ponteiro.dir = ponteiro.esq.dir;
        ponteiro.esq = ponteiro.esq.esq;
        return ret;
    }

    public void impressaoPreOrdem(Node no) {
        if (no.info != null) {
            System.out.print(no.info + " ");
            if (no.esq.info != null) {
                impressaoPreOrdem(no.esq);
            }
            if (no.dir != null) {
                impressaoPreOrdem(no.dir);
            }
        }
    }
}



