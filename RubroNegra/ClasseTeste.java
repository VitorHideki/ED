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
                    teste.remove(numero);
                    break;
                case 3:
                    System.out.println(">>>>>>RUBRO-NEGRA<<<<<<");
                    teste.impressaoPreOrdem(teste.raiz);
                    System.out.println("");
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
