import java.util.Scanner;

class IuEstante{

	public static int mostrarEstante(Pessoa p){
		Scanner reader = new Scanner(System.in);
		int opcao = 0;
		while(opcao != 7){
			int i = 0;
			Estante e = p.getEstante();

			System.out.println("\n\n\n ------------ SUA ESTANTE --------------");
			if(e == null || e.getLivros().isEmpty()){
				System.out.println("Estante vazia");
			}else{
				for(Livro l : e.getLivros()){
					i++;
					System.out.println(i + ". " + l.getTitulo());
				}
			}

			System.out.println("\n\n\n ------------   OPCOES  --------------");
			System.out.println("1. Adicionar livro a estante");
			System.out.println("2. Remover livro da estante");
			System.out.println("3. Consultar dados de livro");
			System.out.println("4. Consultar usuario");
			System.out.println("5. Escrever resenha de livro");
			System.out.println("6. Procurar livro para troca");
			System.out.println("7. Sair");

			opcao = reader.nextInt();
			reader.nextLine();

			if(opcao == 1){
				e = colocarNaEstante(e);
			}else if(opcao == 2){
				e = removerDaEstante(e);
			}else if(opcao == 3){
				consultarLivro();
			}else if(opcao == 4){
				consultarUsuario();
			}else if(opcao == 5){
				escreverResenha();
			}
		}
		return 0;
	}

//@todo
	/**
	* ...
	*
	* @param param 	descricao
	* @return 		retorno
	* @since 		1.0
	*/
	private static Estante colocarNaEstante(Estante e){
		return e;
	}

//@todo
	/**
	* ...
	*
	* @param param 	descricao
	* @return 		retorno
	* @since 		1.0
	*/
	private static Estante removerDaEstante(Estante e){
		Scanner reader = new Scanner(System.in);
		PersistLivro persistLivro = new PersistLivro();
		String opcao = "";
		String nomeLivro;

		while(!opcao.equals("n") && !opcao.equals("N")){
			int contem = 0;
			Livro livro = null;
			System.out.println("\n\n\n ------------ REMOVER LIVRO --------------");
			System.out.println("Escreva o nome do livro para ser removido: ");
			nomeLivro = reader.nextLine();
			livro =  persistLivro.buscarLivroNome(nomeLivro);
			contem = e.removeLivro(livro);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(livro == null){
				System.out.println("Livro nao encontrado.");
			}else if(contem == 0){
				System.out.println("Livro nao pertence a sua estante.");
			}else{ 
				persistLivro.subtrairQuantLiv(livro.getCod(), 1);
				System.out.println("\nLivro removido.");
			}

			System.out.println("\nRemover outro? (s/n) ");
			opcao = reader.nextLine();
		}

		return e;
	}

	private static int escreverResenha(){
		// falta salvar no banco a resenha
		Scanner reader = new Scanner(System.in);
		PersistLivro persistLivro = new PersistLivro();
		String opcao = "";
		String nomeLivro;
		Livro livro = null;
		String resenha = "";

		while(!opcao.equals("n") && !opcao.equals("N")){
			System.out.println("\n\n\n ------------ ESCREVER RESENHA --------------");
			System.out.println("Escreva o nome do livro para a resenha: ");
			nomeLivro = reader.nextLine();
			livro =  persistLivro.buscarLivroNome(nomeLivro);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(livro == null){
				System.out.println("Livro nao encontrado.");
			}else{ 
				System.out.println("Resenha: ");
				resenha = reader.nextLine();
				livro.adicionarResenha(resenha);
			}

			System.out.println("\nNova resenha? (s/n) ");
			opcao = reader.nextLine();
		}
		return 0;
	}

	private static int informarTroca(){
		return 0;
	}

	private static int procurarTroca(){
		return 0;
	}

//@todo
	/**
	* ...
	*
	* @param param 	descricao
	* @return 		retorno
	* @since 		1.0
	*/
	private static int consultarLivro(){
		Scanner reader = new Scanner(System.in);
		PersistLivro persistLivro = new PersistLivro();
		String opcao = "";
		String nomeLivro;
		Livro livro = null;

		while(!opcao.equals("n") && !opcao.equals("N")){
			System.out.println("\n\n\n ------------ CONSULTAR LIVRO --------------");
			System.out.println("Escreva o nome do livro desejado: ");
			nomeLivro = reader.nextLine();
			livro =  persistLivro.buscarLivroNome(nomeLivro);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(livro == null){
				System.out.println("Livro nao encontrado.");
			}else{ 
				System.out.println(livro.getTitulo() + " - " + livro.getNomeAutor() + " - " + livro.getDtPublicacao() + " - " + livro.getGenero());
			}

			System.out.println("\nNova consulta? (s/n) ");
			opcao = reader.nextLine();
		}
		return 0;
	}

//@todo
	/**
	* ...
	*
	* @param param 	descricao
	* @return 		retorno
	* @since 		1.0
	*/
	private static int consultarUsuario(){
		Scanner reader = new Scanner(System.in);
		PersistPessoa persistPessoa = new PersistPessoa();
		String opcao = "";
		String nomePessoa;
		Pessoa pessoa = null;

		while(!opcao.equals("n") && !opcao.equals("N")){
			System.out.println("\n\n\n ------------ CONSULTAR PESSOA --------------");
			System.out.println("Escreva o nome da pessoa desejada: ");

			nomePessoa = reader.nextLine();
			pessoa =  persistPessoa.buscarPesPorNome(nomePessoa);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(pessoa == null){
				System.out.println("Pessoa nao encontrada.");
			}else{
				System.out.println(pessoa.getNome() + " - " + pessoa.getApelido() + " - " + pessoa.getTelefone());
			}

			System.out.println("\nNova consulta? (s/n) ");
			opcao = reader.nextLine();
		}

		return 0;
	}

}