
import java.util.Scanner;

class IuLogin{
	public static void login(){
		Scanner reader = new Scanner(System.in);
		int opcao = -1;
		PersistLivro teste = new PersistLivro();

		while(opcao != 0){
			System.out.println("Loja de livros");	
			System.out.println("0. Sair");
			System.out.println("1. Teste");
			opcao = reader.nextInt();	
			System.out.println(opcao);
			if(opcao == 1){
				teste.gravarLivro(new Livro());
			}
		}

	}

}