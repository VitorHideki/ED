import java.util.Scanner;

public class ClasseTeste {
    public static void main(String[] args) {
        ABB teste = new ABB();
        Node no = teste.getRaiz();
//        Scanner sc = new Scanner(System.in);
//        int opcao = sc.nextInt();
//        int numero = sc.nextInt();
        teste.insere(45);
        teste.insere(67);
        teste.insere(34);
        teste.insere(78);
        teste.insere(23);
        teste.impressaoPreOrdem(no);
        System.out.println("");
        teste.remove(67);
        teste.insere(50);
        teste.insere(90);
        teste.insere(65);
        teste.remove(45);
        teste.remove(78);
        teste.impressaoPreOrdem(no);
        System.out.println("");
        teste.remove(23);
        teste.remove(90);
        teste.impressaoPreOrdem(no);

//        while (opcao != 0) {
//            switch (opcao) {
//                case 1:
//                    teste.insere(numero);
//                    break;
//                case 2:
//                    teste.remove(numero);
//                    break;
//                case 3:
//                    teste.impressaoPreOrdem(no);
//                    break;
//                default:
//                    System.out.println("Opção incorreta");
//            }
//            opcao  = sc.nextInt();
//            if (opcao != 3){
//                numero = sc.nextInt();
//
//            }
//        }
    }
}
