import java.util.Scanner;

public class Main{
	public static void main(String[] args)}{
		Scanner reader = new Scanner(System.in);
		int opcao = -1;

		while(opcao != 0){
			System.out.println("Loja de livros");	
			while(opcao != 0 && opcao != 1){
				System.out.println("0. Sair");
				System.out.println("1. Teste");
				opcao = reader.nextInt();
			}
			
			
		}
		
		
	}
}