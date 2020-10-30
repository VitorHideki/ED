package RubroNegra;

import static RubroNegra.Cor.RUBRO;
import static RubroNegra.Cor.NEGRA;

public class RubroNegra<T extends Comparable<T>> {

    protected No<T> raiz;

    public RubroNegra() {
        this.raiz = new No<T>();

    }

    public boolean insercao(T valor) {
        No<T> no = raiz;
        No<T> anterior = null;
        while (no.info != null) {
            anterior = no;
            if (no.info.compareTo(valor) < 0) {
                no = no.dir;
            } else if (no.info.compareTo(valor) > 0) {
                no = no.esq;
            } else {
                return false;
            }
        }
        no.pai = anterior;

        no.info = valor;
        no.esq = new No<T>();
        no.dir = new No<T>();
        no.cor = RUBRO;
        if (anterior != null) {
            verificaBalanceamento(no);
        }
        return true;
    }

    public void rotacionaEsquerda(No<T> no) {
        No filho = no.dir;
        No neto = filho.esq;
        no.dir = neto;
        filho.esq = no;
        if (no.pai != null) {
            if (no.pai.dir == no) {
                no.pai.dir = filho;
            } else {
                no.pai.esq = filho;
            }
        } else {
            raiz = filho;
        }
        filho.pai = no.pai;
        no.pai = filho;
        neto.pai = no;
    }


    public void rotacionaDireita(No<T> no) {
        No filho = no.esq;
        No neto = filho.dir;
        no.esq = neto;
        filho.dir = no;
        if (no.pai != null) {
            if (no.pai.esq == no) {
                no.pai.esq = filho;
            } else {
                no.pai.dir = filho;
            }
        } else {
            raiz = filho;
        }
        filho.pai = no.pai;
        no.pai = filho;
        neto.pai = no;
    }


    public void verificaBalanceamento(No<T> novoNo) {
        while (novoNo.cor == RUBRO && novoNo.pai != null && novoNo.pai.cor == RUBRO) {
            if (novoNo.pai.pai == null) {
                break;
            }
            if (novoNo.pai.equals(novoNo.pai.pai.esq)) {
                No<T> tio = novoNo.pai.pai.dir;
                if (tio.cor == RUBRO) {
                    novoNo.pai.cor = NEGRA;
                    tio.cor = NEGRA;
                    novoNo.pai.pai.cor = RUBRO;
                    novoNo = novoNo.pai.pai;
                } else {
                    if (novoNo.equals(novoNo.pai.dir)) {
                        rotacionaEsquerda(novoNo.pai);
                        novoNo = novoNo.esq;
                    }
                    rotacionaDireita(novoNo.pai.pai);
                    if(novoNo.pai.pai !=null){
                        novoNo.pai.pai.cor = RUBRO;
                    }

                    novoNo.pai.cor = NEGRA;
                    novoNo = novoNo.pai;
                }
            } else {
                if (novoNo.pai.equals(novoNo.pai.pai.dir)) {
                    No<T> tio = novoNo.pai.pai.esq;
                    if (tio.cor == RUBRO) {
                        novoNo.pai.cor = NEGRA;
                        tio.cor = NEGRA;
                        novoNo.pai.pai.cor = RUBRO;
                        novoNo = novoNo.pai.pai;
                    } else {
                        if (novoNo.equals(novoNo.pai.esq)) {
                            rotacionaDireita(novoNo.pai);
                            novoNo = novoNo.dir;
                        }
                        rotacionaEsquerda(novoNo.pai.pai);
                        if(novoNo.pai.pai !=null){
                            novoNo.pai.pai.cor = RUBRO;
                        }
                        novoNo.pai.cor = NEGRA;
                        novoNo = novoNo.pai;
                    }
                }
            }
        }
        raiz.cor = NEGRA;
    }

    public void impressaoPreOrdem(No<? extends Object> no) {
        if (no.info != null) {
            if (no.pai == null) {
                System.out.println("[" + no.info + "]" + " -> " + "COR: " + no.cor + " ->  PAI: null");
            } else {
                System.out.println("[" + no.info + "]" + " -> " + "COR: " + no.cor + " ->  PAI: " + no.pai.info);
            }
            if (no.esq.info != null) {
                impressaoPreOrdem(no.esq);
            }
            if (no.dir != null) {
                impressaoPreOrdem(no.dir);
            }
        }
    }
}
