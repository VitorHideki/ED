package AVL;

public class AVL<T extends Comparable<T>> {
    public Node<T> raiz = null;
    private Node valor;

    public AVL() {
        this.valor = null;
    }

    private static int altura(Node no) {
        if (no == null) {
            return -1;
        } else return no.altura;
    }


    private static int max(int esq, int dir) {
        if (esq > dir) {
            return esq;
        } else {
            return dir;
        }
    }

    private int fatorBalanceamento(Node no) {
        return altura(no.esquerda) - altura(no.direita);
    }

    public boolean inserir(T x) {
        raiz = inserir(x, raiz);
        return true;
    }

    private Node inserir(T x, Node<T> no) {
        if (no == null) {
            no = new Node(x, new Node<>(null), new Node<>(null));
        } else if (x.compareTo(no.valor) < 0) {
            no.esquerda = inserir(x, no.esquerda);
        } else if (x.compareTo(no.valor) > 0) {
            no.direita = inserir(x, no.direita);

        }
        no = balancear(no);
        return no;
    }

    public Node balancear(Node no) {
        if (fatorBalanceamento(no) == 2) {
            if (fatorBalanceamento(no.esquerda) > 0) {
                no = rotacionaDireita(no);
            } else {
                no = rotacaoDuplaDireita(no);
            }
        } else if (fatorBalanceamento(no) == -2) {
            if (fatorBalanceamento(no.direita) < 0) {
                no = rotacionaEsquerda(no);
            } else {
                no = rotacaoDuplaEsquerda(no);

            }
        }
        no.altura = max(altura(no.esquerda), altura(no.direita)) + 1;
        return no;
    }

    private static Node rotacionaDireita(Node no) {
        Node noAux = no.esquerda;
        no.esquerda = noAux.direita;
        noAux.direita = no;
        no.altura = max(altura(no.esquerda), altura(no.direita)) ;
        noAux.altura = max(altura(noAux.esquerda), no.altura) ;
        return noAux;
    }

    private static Node rotacionaEsquerda(Node no) {
        Node noAux = no.direita;
        no.direita = noAux.esquerda;
        noAux.esquerda = no;
        no.altura = max(altura(no.esquerda), altura(no.direita)) ;
        noAux.altura = max(altura(noAux.direita), no.altura) ;
        return noAux;
    }

    private Node rotacaoDuplaDireita(Node no) {
        no.esquerda = rotacionaEsquerda(no.esquerda);
        Node noAux = no.esquerda;
        no.esquerda = noAux.direita;
        noAux.direita = no;
        no.altura = max(altura(no.esquerda), altura(no.direita)) + 1;
        noAux.altura = max(altura(noAux.esquerda), no.altura) + 1;
        return noAux;
    }

    private Node rotacaoDuplaEsquerda(Node no) {
        no.direita = rotacionaDireita(no.direita);
        Node noAux = no.direita;
        no.direita = noAux.esquerda;
        noAux.esquerda = no;
        no.altura = max(altura(no.esquerda), altura(no.direita)) + 1;
        noAux.altura = max(altura(noAux.direita), no.altura) + 1;
        return noAux;
    }

    public void impressaoPreOrdem() {
        System.out.println(">>>>>>AVL<<<<<<");
        impressaoPreOrdem(this.raiz);
    }

    public void impressaoPreOrdem(Node no) {
        if (no != null) {
            System.out.println(no.valor + "-> altura " + (no.altura + 1));
            if (no.esquerda != null) {
                impressaoPreOrdem(no.esquerda);
            }
            if (no.direita != null) {
                impressaoPreOrdem(no.direita);
            }
        }
    }


    public Node remove(T valor, Node no,Node pai) {
        Node ret = null;

        if (no.valor != null) {
            if (valor.compareTo((T) no.valor) > 0) {
                pai = no;
                ret = remove(valor, no.direita, pai);
            } else if (valor.compareTo((T) no.valor) < 0) {
                pai = no;
                ret = remove(valor, no.esquerda, pai);
            } else {
                ret = no;
                if (no.esquerda != null && no.direita != null) {
                    no.valor = retornaMaiorEsq(no);

                } else if (no.esquerda == null && no.direita == null) {
                    if (pai.direita.valor == null) {
                        pai.valor = pai.esquerda.valor;
                        pai.direita = pai.esquerda.direita;
                        pai.esquerda = pai.esquerda.esquerda;
                    } else {
                        pai.direita = pai.direita.direita;

                    }

                } else if (no.direita.valor == null) {
                    no.valor = no.esquerda.valor;
                    no.direita = no.esquerda.direita;
                    no.esquerda = no.esquerda.esquerda;
                } else {
                    no.valor = no.direita.valor;
                    no.esquerda = no.direita.esquerda;
                    no.direita = no.direita.direita;
                }
                no = balancear(no);
                return no;
            }

        }
        return ret;
    }

    private Object retornaMaiorEsq(Node no) {
        no = no.esquerda;
        while (no.direita.direita != null) {
            no = no.direita;
        }
        Object ret = no.direita.valor;
        no.direita = no.direita.direita;
        return ret;
    }
}

