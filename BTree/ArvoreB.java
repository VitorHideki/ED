package BTree;

public class ArvoreB<T extends Comparable<T>> {

    public Pagina<T> raiz;
    private final int ordem;
    private final int numMaximoChaves;

    public ArvoreB(int ordem){
        this.ordem = ordem;
        this.raiz = null;
        this.numMaximoChaves = (2*ordem) - 1;
    }

    public int getOrdem() {
        return ordem;
    }

    public ArvoreB(){
        this.ordem = getOrdem();
        this.raiz = null;
        this.numMaximoChaves = (2*ordem) - 1;
    }


    public T buscaArvoreB(Pagina<T> pagina, T valor){
        int i = 0;
        while (i <= pagina.numChaves && valor.compareTo(pagina.chave[i]) < 0 ){
            i++;
        }if (i <= pagina.numChaves && valor.compareTo(pagina.chave[i]) == 0 ){
            return pagina.chave[i];
        }else if (pagina.folha){
            return null;
        }else leituraDoDisco(pagina, pagina.chave[i]);
        return buscaArvoreB((Pagina<T>) pagina.chave[i], valor);
    }

    public Pagina alocaPagina(){
        Pagina novaPagina = new Pagina<>(getOrdem());
        if (this.raiz == null){
            novaPagina.folha = true;
        }
        return novaPagina;

    }

    public void insercao(Pagina<T> arvore, T valor){
        Pagina<T> novaPagina = alocaPagina();

        if (this.raiz == null){
            this.raiz = novaPagina;
            this.raiz.folha = true;
        }
        Pagina<T> r = this.raiz;
        if (r.numChaves == numMaximoChaves ){
            raiz = novaPagina;
            novaPagina.folha = false;
            novaPagina.numChaves = 0;
            novaPagina.filhos[0] = r ;
            SplitaFilhoArvoreB(novaPagina,0 );
            InsereEmArvoreBNaoCheia(novaPagina,valor);
        }else {
            InsereEmArvoreBNaoCheia(r,valor);
        }
    }

    private void InsereEmArvoreBNaoCheia(Pagina<T> arvore, T valor) {
        int i = arvore.numChaves - 1;
        if (arvore.folha) {
            while (i >= 0 && valor.compareTo(arvore.chave[i]) < 0) {
                arvore.chave[i + 1] = arvore.chave[i];
                i = i - 1;
            }
            arvore.chave[i + 1] = valor;
            arvore.numChaves = arvore.numChaves + 1;
            escritaDoDisco((Integer) valor);
        } else {
            while (i >= 0  && valor.compareTo(arvore.chave[i]) < 0 ) {
                i = i - 1;
            }
            i++;
            escritaDoDisco((Integer) valor);

            if (arvore.filhos[i].numChaves == numMaximoChaves ) {
                SplitaFilhoArvoreB(arvore, i);
                if (valor.compareTo((T) arvore.chave[i]) > 0) {
                    i = i + 1;
                }
            }
            InsereEmArvoreBNaoCheia(arvore.filhos[i], valor);
        }
    }



    private void SplitaFilhoArvoreB(Pagina novaPagina, int i) {
        Pagina z = new Pagina(getOrdem());
        Pagina y = novaPagina.filhos[i];
        z.folha = y.folha;
        z.numChaves = ordem - 1;
        for (int indice = 0; indice < ordem-1; indice++){
            z.chave[indice] = y.chave[indice+ordem];
        }if (!y.folha){
            for (int indice = 0; indice < ordem - 1; indice++){
                z.filhos[indice] = y.filhos[indice+ordem];
            }
        }
        y.numChaves = ordem - 1;
//
        for (int j = novaPagina.numChaves + 1; j > i ; j--){
            novaPagina.filhos[j+1] = novaPagina.filhos[j];
        }
        novaPagina.filhos[i+1] = z;
        for (int j = novaPagina.numChaves; j < i; j++){
            novaPagina.chave[j + 1] = novaPagina.chave[j];
        }
        novaPagina.chave[i] = y.chave[ordem - 1];
        novaPagina.numChaves = novaPagina.numChaves  + 1;

        for (int k = ordem-1; k< y.chave.length; k++){
            y.chave[k]  = null;
        }

    }



    public void imprimirPreOrdem(Pagina<T> no) {


        if (no.chave != null) {
            System.out.print("<< ");
            for (int i = 0; i < no.chave.length; i++) {
                if (no.chave[i] != null) {
                    System.out.print(" " + no.chave[i] + " ");
                }
            }
            System.out.print(" >>\n");
            for (int i = 0; i < no.filhos.length; i++) {
                imprimirPreOrdem(no.filhos[i]);

            }
        }
    }





    public void criarArvoreB(T valor){
        Pagina x = alocaPagina();
        x.folha = true;
        x.numChaves = 0;
        this.raiz = x;
    }

    private void escritaDoDisco(int x) {
        System.out.println("--- INSERINDO A CHAVE '" + x + "' ---");
    }

    private void leituraDoDisco(Pagina<T> chave, T valor) {
        System.out.print("\tLendo a Página << ");
        for (int i = 0; i < chave.numChaves; i++){
            System.out.print(chave.chave[i]);
        }
        System.out.print(" >> no Disco... ");
    }
}