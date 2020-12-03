import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Integer valor,menu;	
		Scanner entrada = new Scanner(System.in);
		try{
            RubroNegra arvore = new RubroNegra();
		System.out.println("Menu:");

		while(true) {
			System.out.println("\nInsira 1 para inserir No.\nInsira 2 para excluir No");
			menu = entrada.nextInt();
			
			switch(menu){
				case 1:						
					System.out.println("insira o No.");
					valor = entrada.nextInt();
					arvore.inserirNo(valor);
					System.out.println("caminho prefixado");
					arvore.prefixado(arvore.getRoot());
					System.out.println();
					break;
				case 2:
					System.out.println("insira o No a ser excluida.");
					valor = entrada.nextInt();
					arvore.excluirNo(valor);
					System.out.println("caminho prefixado");
					arvore.prefixado(arvore.getRoot());
					System.out.println();
					break;
				default:
					System.out.println("digite uma opção válida!");
					break;

			}//fim do switch
			
        }
        } finally {
            entrada.close();
        }
		
    }
}
