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
        No<T> avo = novoNo.pai.pai;
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
                    novoNo.pai.dir.cor = RUBRO;
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
                        novoNo.pai.dir.cor = RUBRO;
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

    public void removeUmUnicoFilho(ABB.Node ponteiro) {
        ponteiro.info = ponteiro.esq.info;
        ponteiro.dir = ponteiro.esq.dir;
        ponteiro.esq = ponteiro.esq.esq;
    }

    public void removeNoFolha(ABB.Node<T> ponteiro) {
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

    public No remove(T valor) {
        No ret = null;
        No<T> ponteiro = this.raiz;
        Object temp = null;
        while (ponteiro.info != null) {
            if (ponteiro.info.compareTo(valor) < 0) {
                ponteiro = ponteiro.dir;
            } else if (ponteiro.info.compareTo(valor) > 0) {
                ponteiro = ponteiro.esq;
            } else {
                ret = ponteiro;
                if (ponteiro.esq.info != null && ponteiro.dir.info != null) {
                    ret = ponteiro.esq;
                    ponteiro.info = (T) ret.info;
                    ponteiro =  remMaiorEsq(ponteiro);
                    verificaBalanceamentoRemocao(ponteiro);
                    return ret;
                } else if (ponteiro.dir.info == null) {
                    ponteiro.info = ponteiro.esq.info;
                    ponteiro.dir = ponteiro.esq.dir;
                    ponteiro.esq = ponteiro.esq.esq;
                    verificaBalanceamentoRemocao(ponteiro);
                    return ret;
                } else {
                    ponteiro.info = ponteiro.dir.info;
                    ponteiro.esq = ponteiro.dir.esq;
                    ponteiro.dir = ponteiro.dir.dir;
                    return ret;
                }
            }
        }
        return ret;

        }


    private No remMaiorEsq(No<T> ponteiro) {
        ponteiro = ponteiro.esq;
        while (ponteiro.dir.info != null) {
            ponteiro = ponteiro.dir;
        }

        ponteiro.info = ponteiro.esq.info;
        ponteiro.dir = ponteiro.esq.dir;
        ponteiro.esq = ponteiro.esq.esq;
        return ponteiro;
    }







    private No verificaBalanceamentoRemocao(No noRemovido) {
        while (noRemovido != raiz && noRemovido.cor == NEGRA ) {
            if (noRemovido.equals(noRemovido.pai.esq)) { // caso 1
                No irmao = noRemovido.pai.dir;
                if (irmao.cor == RUBRO) {
                    irmao.cor = NEGRA;
                    noRemovido.pai.cor = RUBRO;
                    rotacionaEsquerda(noRemovido.pai);
                    noRemovido = irmao;
                }
                if (irmao.esq.cor == NEGRA && irmao.dir.cor == NEGRA) { // caso 2
                    irmao.cor = RUBRO;
                    noRemovido = noRemovido.pai;
                } else {
                    if (irmao.dir.cor == NEGRA) {
                        irmao.esq.cor = NEGRA;
                        irmao.cor = RUBRO;
                        rotacionaDireita(irmao);
                        noRemovido = irmao;
                    }
                    irmao.cor = irmao.pai.cor;
                    noRemovido.pai.cor = NEGRA;
                    irmao.dir.cor = NEGRA;
                    rotacionaEsquerda(irmao.pai);
                    noRemovido = this.raiz;
                    if (raiz.cor == RUBRO) {
                        raiz.cor = NEGRA;
                    }
                }
            }else{
                if (noRemovido.equals(noRemovido.pai.dir)) {
                    No irmao = noRemovido.pai.esq;
                    if (irmao.cor == RUBRO){
                        irmao.cor = NEGRA;
                        noRemovido.pai.cor = RUBRO;
                        rotacionaDireita(noRemovido.pai);
                        noRemovido = irmao;
                    }
                    if (irmao.dir.cor == NEGRA && irmao.esq.cor == NEGRA){
                        irmao.cor = RUBRO;
                        noRemovido = irmao.pai;
                    }else{
                        if (irmao.esq.cor == NEGRA){
                            irmao.dir.cor = NEGRA;
                            irmao.cor = RUBRO;
                            rotacionaEsquerda(irmao);
                            noRemovido = irmao;
                        }
                        irmao.cor = irmao.pai.cor;
                        noRemovido.pai.cor = NEGRA;
                        irmao.esq.cor = NEGRA;
                        rotacionaDireita(irmao.pai);
                        noRemovido = this.raiz;
                        if (raiz.cor == RUBRO) {
                            raiz.cor = NEGRA;
                        }
                    }
                }
                }
            noRemovido.cor = NEGRA;
            return noRemovido;
        }
        return noRemovido;
        }
    }

