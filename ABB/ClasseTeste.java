package ABB;
import java.util.Scanner;


public class ClasseTeste {
    public static void main(String[] args) {
        ABB teste = new ABB();
        Node no = teste.getRaiz();
        Scanner sc = new Scanner(System.in);
        int opcao = sc.nextInt();
        int numero = sc.nextInt();
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    teste.insere(numero);
                    break;
                case 2:
                    teste.remove(numero);
                    break;
                case 3:
                    teste.impressaoPreOrdem(no);
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
