package BTree;


import java.util.Scanner;

public class ClasseTeste {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int ordem = sc.nextInt();
        ArvoreB teste = new ArvoreB(ordem);
        int opcao = sc.nextInt();
        int numero = sc.nextInt();
        Pagina nova = new Pagina(ordem);

        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    teste.insercao(teste.raiz, numero);
                    break;
                case 2:
//                    teste.remove(numero);
                    break;
                case 3:
                    teste.imprimirPreOrdem(teste.raiz);
                    break;
                default:
                    System.out.println("Opção incorreta");
            }
            opcao  = sc.nextInt();
            if (opcao != 3){
                numero = sc.nextInt();

            }
        }
    }
}
