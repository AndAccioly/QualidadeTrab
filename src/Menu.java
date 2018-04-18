import java.util.Scanner;

public class Menu{
	public static void login(){
		Scanner reader = new Scanner(System.in);
		int opcao = -1;

		while(opcao != 0){
			System.out.println("Loja de livros");	
			System.out.println("0. Sair");
			System.out.println("1. Teste");
			opcao = reader.nextInt();	
		}

	}

	public static void cadastro(){

	}

	public static void loja(){

	}
}