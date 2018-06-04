import java.util.Scanner;

class IuLogin{
	public static void teste(){
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
			}else if(opcao ==5){
				String nome = reader.nextLine();
				teste.deletarLivro(nome);
			}else if(opcao == 6){
				String nome = reader.nextLine();
				teste.diminuirQuantidadeLivro(nome, 1);
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
					//chama funcao de inicio do programa
				}
			}else if(opcao == 9){
				IuCadastro.cadastrar();
			}
		}

	}

	public static void login(){
		int opcao = -1;
		PersistPessoa persistPessoa = new PersistPessoa();
		while(opcao != 0){
			Scanner reader = new Scanner(System.in);
			System.out.println("Login");
			System.out.println("Digite seu usuário: ");
			String nome = reader.nextLine();
			System.out.println("Digite sua senha: ");
			String senha = reader.nextLine();
			persistPessoa.gravarPessoa(new Pessoa());

			
		}
	}

}