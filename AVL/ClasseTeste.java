package AVL;

import java.util.Scanner;

public class ClasseTeste {

    public static void main(String[] args) {
        AVL teste = new AVL();
        Scanner sc = new Scanner(System.in);
        int opcao = sc.nextInt();
        int numero = sc.nextInt();
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    teste.inserir(numero);
                    break;
                case 2:
                      teste.remove(numero, teste.raiz,null);
                    break;
                case 3:
                    teste.impressaoPreOrdem();
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
