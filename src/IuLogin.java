import java.util.Scanner;

class IuLogin{
	public static void teste(){
		login();
		Scanner reader = new Scanner(System.in);
		int opcao = -1;
		PersistLivro teste = new PersistLivro();
		PersistPessoa persistPessoa = new PersistPessoa();

		while(opcao != 0){
			System.out.println("Loja de livros");	
			System.out.println("0. Sair");
			System.out.println("1. Teste gravar");
			System.out.println("2. Teste ler tudo");
			System.out.println("3. Teste ler especifico");
			System.out.println("4. Incrementar");
			System.out.println("5. Deletar");
			System.out.println("6. Decrementar");
			System.out.println("7. Gravar Pessoa");
			System.out.println("8. Teste Login");
			System.out.println("9. Cadastrar");
			opcao = reader.nextInt();
			reader.nextLine();
			
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
				teste.somarQuantLiv(nome, 1);
			}else if(opcao ==5){
				String nome = reader.nextLine();
				teste.deletarLivro(nome);
			}else if(opcao == 6){
				String nome = reader.nextLine();
				teste.subtrairQuantLiv(nome, 1);
			}else if(opcao == 7){
				//login();
				Pessoa p = new Pessoa();
				Livro l = new Livro();
				Estante e = p.getEstante();
				//e.adicionaLivro(l);
				p.setEstante(e);
				p.setTelefone("(12) - 123456789");
				persistPessoa.gravarPessoa(p);
			}else if(opcao == 8){
				System.out.println("Insira o apelido: ");
				String nome = reader.nextLine();
				System.out.println("Insira a senha: ");
				String senha = reader.nextLine();
				Pessoa p = NegocioLogin.login(nome, senha);
				if(p == null){
					System.out.println("Usuario ou senha invalidos");
				}else{
					IuEstante.mostrarEstante(p);
				}
			}else if(opcao == 9){
				IuCadastro.cadastrar();
			}
		}

	}

	public static void login(){
		int opcao = -1;
		PersistPessoa persistPessoa = new PersistPessoa();
		Scanner reader = new Scanner(System.in);

		while(opcao != 0){
			System.out.println("1. Login");
			System.out.println("0. Sair");
			opcao = reader.nextInt();
			reader.nextLine();

			if(opcao == 1){
				System.out.println("Digite seu usuario: ");
				String nome = reader.nextLine();
				System.out.println("Digite sua senha: ");
				String senha = reader.nextLine();

				Pessoa p = NegocioLogin.login(nome,senha);
				if(p != null){
					IuEstante.mostrarEstante(p);
				}else{
					System.out.println("Usuario ou senha invalidos");
				}
			}
		}
	}
}