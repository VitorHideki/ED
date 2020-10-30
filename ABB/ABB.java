public class ABB<T extends Comparable<T>> {
    public Node<T> getRaiz() {
        return raiz;
    }

    private Node<T> raiz = new Node<>(null);
    private Node info;

    public ABB() {
        this.info = null;
        this.raiz = new Node<>(null);
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
        no.esq = new Node<>(null);
        no.dir = new Node<>(null);
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

//    public T remove(T valor) {
//        T valorEncontrado = busca(valor);
//        if(valorEncontrado == null){
//            return null;
//        }
//        Node<T> pai = null;
//        Node<T> ponteiro = this.raiz;
//        Node<T> teste = null;
//        while (ponteiro != null) {
//            if (ponteiro.info.compareTo(valor) >= 0 || this.raiz.info.equals(valor)) {
//                if (ponteiro.info.compareTo(valor) == 0 || ponteiro.esq.info.compareTo(valor) == 0) {
//                    if (ponteiro.esq.info == null && ponteiro.dir.info == null) {
//                        pai.esq = null;
//                        ponteiro = pai;
//                    } else if (ponteiro.esq.info != null && ponteiro.dir.info == null && ponteiro.esq.info == valor) {
//                        pai.esq = ponteiro.esq;
//                        ponteiro.esq = ponteiro.esq.esq;
//                        ponteiro.info = pai.info;
//                        return ponteiro.info;
//                    } else if (ponteiro.dir.info != null && ponteiro.esq.info == null && ponteiro.dir.info == valor) {
//                        pai = ponteiro.dir;
//                        ponteiro.dir = ponteiro.dir.dir;
//                        ponteiro.info = pai.info;
//                        return ponteiro.info;
//                    } else if (ponteiro.esq.info == valor && ponteiro.dir.info != null && ponteiro.esq.info != null) {
//                        pai = ponteiro;
//                        ponteiro = ponteiro.esq;
//                        if (ponteiro.esq.info != null){
//                              ponteiro.esq = ponteiro.esq.esq;
//                            return ponteiro.info;
//                        }else if (ponteiro.dir.info != null){
//                             ponteiro.dir  = ponteiro.dir.dir;
//
//                            return ponteiro.info;
//                        }else {
//                            return ponteiro.info = null;
//                        }
//                    } else if (ponteiro.dir.info != null && ponteiro.esq.info != null && ponteiro.info == valor) {
//                        teste = ponteiro;
//                        if (ponteiro.info == this.raiz.info) {
//                            ponteiro = ponteiro.esq;
//                        }
//
//                        while (ponteiro.dir != null) {
//                            pai = ponteiro;
//                            ponteiro = ponteiro.dir;
//                        }
//
//                        ponteiro = pai.esq;
//                        pai.dir = null;
//                        teste.esq = ponteiro;
//                        return teste.info = pai.info;
//
//                    }
//                    pai = ponteiro;
//                    ponteiro = ponteiro.esq;
//                }
//            }
//            if (ponteiro.info.compareTo(valor) <= 0) {
//
//                if (ponteiro.esq.info != null && ponteiro.esq.info.compareTo(valor) <= 0) {
//                    ponteiro = ponteiro.dir;
//                }
//                if (ponteiro.info.compareTo(valor) == 0) {
//                    if (ponteiro.esq.info == null && ponteiro.dir.info == null) {
//                        pai.dir = null;
//                        ponteiro = pai;
//                    } else if (ponteiro.esq.info != null && ponteiro.dir.info == null && ponteiro.esq.info == valor) {
//                        pai.esq = ponteiro.esq;
//                        ponteiro.esq = ponteiro.esq.esq;
//                        ponteiro.info = pai.info;
//                        return ponteiro.info;
//                    } else if (ponteiro.dir.info != null && ponteiro.esq.info == null) {
//                        pai = ponteiro.dir;
//                        ponteiro.dir = ponteiro.dir.dir;
//                        ponteiro.info = pai.info;
//                        return ponteiro.info;
//                    } else if (ponteiro.dir.info != null && ponteiro.esq.info != null) {
//                        teste = ponteiro;
//                        ponteiro = ponteiro.esq;
//
//                        while (ponteiro.dir.info != null) {
//                            pai = ponteiro;
//                            ponteiro = ponteiro.dir;
//                        }
//                        pai.dir = null;
//                        return teste.info = ponteiro.info;
//                    }
//                }
//                ponteiro = ponteiro.dir;
//                pai = ponteiro;
//            }
//        }
//        return valor;
//
//    }

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



