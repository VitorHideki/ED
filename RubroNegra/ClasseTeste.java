package RubroNegra;

import java.util.Scanner;

public class ClasseTeste {

    public static void main(String[] args) {
        RubroNegra teste = new RubroNegra();
        Scanner sc = new Scanner(System.in);
        int opcao = sc.nextInt();
        int numero = sc.nextInt();
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    teste.insercao(numero);
                    break;
                case 2:
                    break;
                case 3:
                    teste.impressaoPreOrdem(teste.raiz);
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
