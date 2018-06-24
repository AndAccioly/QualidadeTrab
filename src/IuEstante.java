import java.util.Scanner;
import java.util.List;

/**
* Classe de Interface de usuário. As interfaces implementadas nesse projeto são simples com contato por terminal com o
* usuário. Elas se comunicam com as classes de negócio para lógica de manejamento de dados.
* <p>
* A classe IuEstante possui todos os métodos de interação com o usuário relativos à estante. São eles: adicionar livro 
* na estante, remover livro da estante, consultar dados de livro, consultar usuário, escrever resenha de livro e 
* procurar livro para troca.
*
* @author Andre Accioly
* @author Tiago Kfouri
* @version %I%, %G%
* @since 1.0
* @see NegocioEstante
* @see Pessoa
* @see Livro
* @see Estante
*/
class IuEstante{

	/**
	* Método que mostra a estante do usuário. Depois mostra um menu com as opções que o usuário pode 
	* fazer com a estante.
	*
	* @param p 			a pessoa cuja estante será mostrada
	* @return 			um inteiro 0, caso sucesso
	* @see Estante
	* @see Livro
	* @since 			1.0
	*/
	public static int mostrarEstante(Pessoa p){
		Scanner reader = new Scanner(System.in);
		int opcao = 0;
		while(opcao != 8){
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
			System.out.println("6. Colocar livro para troca");
			System.out.println("7. Procurar livro para troca");
			System.out.println("8. Encerrar sessao");

			opcao = reader.nextInt();
			reader.nextLine();

			if(opcao == 1){
				e = colocarNaEstante(e, p);
			}else if(opcao == 2){
				e = removerDaEstante(e, p);
			}else if(opcao == 3){
				consultarLivro();
			}else if(opcao == 4){
				consultarUsuario();
			}else if(opcao == 5){
				escreverResenha();
			}else if(opcao == 6){
				informarTroca(p);
			}else if(opcao == 7){
				procurarTroca();
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
	private static Estante colocarNaEstante(Estante e, Pessoa p){
		PersistPessoa persistPessoa = new PersistPessoa();
		Scanner reader = new Scanner(System.in);
		PersistLivro persistLivro = new PersistLivro();
		String opcao = "";

		while(!opcao.equals("n") && !opcao.equals("N")){
			int result = 1;
			Livro livro = new Livro();
			System.out.println("\n\n\n ------------ ADICIONAR LIVRO --------------");
			System.out.println("Escreva os dados do livro: ");
			System.out.println("Titulo: ");
			String titulo = reader.nextLine();
			System.out.println("Nome do autor: ");
			String nomeAutor = reader.nextLine();
			System.out.println("Data de publicacao: ");
			String dtPublicacao = reader.nextLine();
			System.out.println("Genero: ");
			String genero = reader.nextLine();
			
			livro.setCod(persistLivro.geraCodigo());
			livro.setTitulo(titulo);
			livro.setNomeAutor(nomeAutor);
			livro.setDtPublicacao(dtPublicacao);
			livro.setGenero(genero);
			livro.setQuantidade(1);

			result =  persistLivro.gravarLivro(livro);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(result == 0){
				e.adicionaLivro(livro);
				persistPessoa.associarLivro(livro, p);
				System.out.println("Livro adicionado com sucesso.");
			}else{ 
				System.out.println("Erro ao adicionar livro.");
			}

			System.out.println("\n\nAdicionar outro? (s/n) ");
			opcao = reader.nextLine();
		}

		return e;
	}


	/**
	* Método que pergunta para o usuário o nome de um livro a ser removido da estante e depois 
	* remove o livro, caso encontre. Depois pergunta se quer remover outro livro.
	*
	* @param e 		a estante cujo livro será procurado
	* @return 		a estante modificada agora sem o livro, caso tenha removido ou a estante intácta, 
	*				caso não tenha encontrado o livro
	* @since 		1.0
	*/
	private static Estante removerDaEstante(Estante e, Pessoa p){
		Scanner reader = new Scanner(System.in);
		PersistLivro persistLivro = new PersistLivro();
		PersistPessoa persistPessoa = new PersistPessoa();
		String opcao = "";
		String titulo;

		while(!opcao.equals("n") && !opcao.equals("N")){
			int contem = 0;
			Livro livro = null;
			System.out.println("\n\n\n ------------ REMOVER LIVRO --------------");
			System.out.println("Escreva o nome do livro para ser removido: ");
			titulo = reader.nextLine();
			livro =  persistLivro.buscarLivroNome(titulo);
			if(livro!= null){
				contem = e.removeLivro(livro);
			}

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(livro == null){
				System.out.println("Livro nao encontrado.");
			}else if(contem == 1){
				System.out.println("Livro nao pertence a sua estante.");
			}else{
				persistPessoa.desassociarLivro(titulo, p);
				persistLivro.subtrairQuantLiv(livro.getCod(), 1);
				System.out.println("\nLivro removido.");
			}

			System.out.println("\nRemover outro? (s/n) ");
			opcao = reader.nextLine();
		}

		return e;
	}

	/**
	* Método que permite ao usuário escrever uma resenha sobre um livro específico, que não precisa estar na 
	* estante. 
	*
	* @return 		0, caso sucesso
	* @since 		1.0
	*/
	private static int escreverResenha(){
		// falta salvar no banco a resenha
		Scanner reader = new Scanner(System.in);
		PersistLivro persistLivro = new PersistLivro();
		String opcao = "";
		String titulo;
		Livro livro = null;
		String resenha = "";

		while(!opcao.equals("n") && !opcao.equals("N")){
			System.out.println("\n\n\n ------------ ESCREVER RESENHA --------------");
			System.out.println("Escreva o nome do livro para a resenha: ");
			titulo = reader.nextLine();
			livro =  persistLivro.buscarLivroNome(titulo);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(livro == null){
				System.out.println("Livro nao encontrado.");
			}else{ 
				System.out.println("Resenha: ");
				resenha = reader.nextLine();
				persistLivro.escreverResenha(livro, resenha);
			}

			System.out.println("\nNova resenha? (s/n) ");
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
	private static int informarTroca(Pessoa p){
		Scanner reader = new Scanner(System.in);
		PersistPessoa persistPessoa = new PersistPessoa();
		String opcao = "";
		String titulo;

		while(!opcao.equals("n") && !opcao.equals("N")){
			int result = 0;
			System.out.println("\n\n\n ------------ INFORMAR TROCA DE LIVRO --------------");
			System.out.println("Escreva o nome do livro que deseja dar: ");
			titulo = reader.nextLine();
			result = persistPessoa.informarTroca(titulo, p);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(result == 0){
				System.out.println("Livro nao encontrado.");
			}

			System.out.println("\nInformar outro interesse para troca? (s/n) ");
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
	private static int procurarTroca(){
		Scanner reader = new Scanner(System.in);
		PersistPessoa persistPessoa = new PersistPessoa();
		String opcao = "";
		String titulo;

		while(!opcao.equals("n") && !opcao.equals("N")){
			int i = 0;
			System.out.println("\n\n\n ------------ CONSULTAR PESSOA PARA TROCA DE LIVRO --------------");
			System.out.println("Escreva o nome do livro que deseja obter: ");
			titulo = reader.nextLine();
			List<Pessoa> pessoas = persistPessoa.procurarTroca(titulo);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(pessoas != null){
				if(pessoas.size() == 0){
					System.out.println("Livro nao encontrado.");
				}else{
					System.out.println("Pessoas que tem o livro:\n");
					for(Pessoa p : pessoas){
						i++;
						System.out.println(i + ". " + p.getNome() + "-" + p.getApelido() + "-" + p.getTelefone());
					}
				}
			}else{
					System.out.println("Livro nao encontrado.");
			}

			System.out.println("\nInformar outro interesse para troca? (s/n) ");
			opcao = reader.nextLine();
		}
		return 0;
	}

	/**
	* Método que permite ao usuário procurar por um livro específico. Caso encontre, todas as informações 
	* do livro serão mostradas na tela para o usuário.
	*
	* @return 		0, caso sucesso
	* @since 		1.0
	*/
	private static int consultarLivro(){
		Scanner reader = new Scanner(System.in);
		PersistLivro persistLivro = new PersistLivro();
		String opcao = "";
		int i = 0;
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
				for(String s : livro.getResenhas()){
					i++;
					System.out.println("\nResenha " + i + ": " + s + "\n");
				}
			}

			System.out.println("\nNova consulta? (s/n) ");
			opcao = reader.nextLine();
		}
		return 0;
	}

	/**
	* Método que permite ao usuário procurar por um usuário específico. Caso encontre, todas as informações 
	* do livro serão mostradas na tela para o usuário.
	*
	* @return 		0, caso sucesso
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