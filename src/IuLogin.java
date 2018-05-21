
import java.util.Scanner;

class IuLogin{
	public static void login(){
		Scanner reader = new Scanner(System.in);
		int opcao = -1;
		PersistLivro teste = new PersistLivro();

		while(opcao != 0){
			System.out.println("Loja de livros");	
			System.out.println("0. Sair");
			System.out.println("1. Teste gravar");
			System.out.println("2. Teste ler tudo");
			System.out.println("3. Teste ler especifico");
			System.out.println("4. Incrementar");
			opcao = reader.nextInt();
			reader.nextLine();
			System.out.println(opcao);
			
			if(opcao == 1){
				String nome = reader.nextLine();
				Livro l = new Livro();
				l.setTitulo(nome);
				teste.gravarLivro(l);
			}else if(opcao == 2){
				teste.lerTodosLivros();
			}else if(opcao == 3){
				String nome = reader.nextLine();
				System.out.println("Lido --> " + (teste.buscarLivro(nome)).camposEmStr());
			}else if(opcao == 4){
				String nome = reader.nextLine();
				teste.adicionarQuantidadeLivro(nome, 1);
			}
		}

	}

}