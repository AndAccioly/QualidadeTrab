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
* @author 	Andre Accioly
* @author 	Tiago Kfouri
* @version 	%I%, %G%
* @since 	1.0
* @see NegocioEstante
* @see Pessoa
* @see Livro
* @see Estante
*/
class IuEstante{

	/**
	* Método que mostra todos os livros da estante do usuário. Depois mostra um menu com as opções que o usuário pode 
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
	
	/**
	* Método que pergunta para o usuário todos os dados do livro que será adicionado na estante (titulo, 
	* nome do autor, data de publicação e gênero literário), cria um novo livro com esses dados e o coloca 
	* na estante.
	*
	* @param e 			a estante da pessoa
	* @param p 			a pessoa cuja estante será mostrada
	* @return 			um inteiro 0, caso sucesso
	* @see Estante
	* @see Livro
	* @since 			1.0
	*/
	public static Estante colocarNaEstante(Estante e, Pessoa p){
		Scanner reader = new Scanner(System.in);
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
			
			livro = NegocioEstante.criarLivro(titulo, nomeAutor, dtPublicacao, genero);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(livro != null){
				e = NegocioEstante.colocarNaEstante(e, livro, p);
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
	* @param p 		o usuário que irá remover o livro da estante
	* @return 		a estante modificada agora sem o livro, caso tenha removido ou a estante intÃ¡cta, 
	*				caso não tenha encontrado o livro
	* @since 		1.0
	*/
	public static Estante removerDaEstante(Estante e, Pessoa p){
		Scanner reader = new Scanner(System.in);
		String opcao = "";
		String titulo;

		while(!opcao.equals("n") && !opcao.equals("N")){
			int contem = 0;
			Livro livro = null;
			System.out.println("\n\n\n ------------ REMOVER LIVRO --------------");
			System.out.println("Escreva o nome do livro para ser removido: ");
			titulo = reader.nextLine();
			livro =  NegocioEstante.buscarLivro(titulo);
			
			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(livro == null){
				System.out.println("Livro nao existente ou nao pertece a sua estante.");
			}else{
				e = NegocioEstante.removerDaEstante(e, livro, p);
				System.out.println("\nLivro removido.");
			}

			System.out.println("\nRemover outro? (s/n) ");
			opcao = reader.nextLine();
		}

		return e;
	}

	/**
	* MÃ©todo que permite ao usuÃ¡rio escrever uma resenha sobre um livro especÃ­fico, que nÃ£o precisa estar na 
	* estante. 
	*
	* @return 		0, caso sucesso
	* @since 		1.0
	*/
	public static int escreverResenha(){
		Scanner reader = new Scanner(System.in);
		String opcao = "";
		String titulo;
		Livro livro = null;
		String resenha = "";

		while(!opcao.equals("n") && !opcao.equals("N")){
			System.out.println("\n\n\n ------------ ESCREVER RESENHA --------------");
			System.out.println("Escreva o nome do livro para a resenha: ");
			titulo = reader.nextLine();
			livro =  NegocioEstante.buscarLivro(titulo);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(livro == null){
				System.out.println("Livro nao encontrado.");
			}else{ 
				System.out.println("Resenha: ");
				resenha = reader.nextLine();
				NegocioEstante.escreverResenha(livro, resenha);
			}

			System.out.println("\nNova resenha? (s/n) ");
			opcao = reader.nextLine();
		}
		return 0;
	}

	/**
	* Método para informar que o usuário quer trocar um livro. Ele informa o livro que quer 
	* dar e, caso o livro seja encontrado, ele é colcoado para troca. Senão ele avisa na tela 
	* que o livro não foi encontrado.
	*
	* @param p 		a pessoa que quer colocar um de seus livros para troca
	* @return 		0 sempre
	* @since 		1.0
	*/
	public static int informarTroca(Pessoa p){
		Scanner reader = new Scanner(System.in);
		String opcao = "";
		String titulo;

		while(!opcao.equals("n") && !opcao.equals("N")){
			int result = 0;
			System.out.println("\n\n\n ------------ INFORMAR TROCA DE LIVRO --------------");
			System.out.println("Escreva o nome do livro que deseja dar: ");
			titulo = reader.nextLine();
			result = NegocioEstante.informarTroca(titulo, p);

			System.out.println("\n\n\n ------------ RESULTADO --------------");
			if(result != 0){
				System.out.println("Livro nao encontrado.");
			}else{
				System.out.println("Livro colocado para troca.");
			}

			System.out.println("\nInformar outro interesse para troca? (s/n) ");
			opcao = reader.nextLine();
		}

		return 0;
	}

	/**
	* Método que pergunta para o usuário qual livro ele quer buscar para obter. Se algum outro usuário 
	* estiver oferecendo esse livro para troca, ele lista todos os usuários que estão oferecendo o livro 
	* desejado.
	*
	* @return 		0, sempre
	* @since 		1.0
	*/
	public static int procurarTroca(){
		Scanner reader = new Scanner(System.in);
		String opcao = "";
		String titulo;

		while(!opcao.equals("n") && !opcao.equals("N")){
			int i = 0;
			System.out.println("\n\n\n ------------ CONSULTAR PESSOA PARA TROCA DE LIVRO --------------");
			System.out.println("Escreva o nome do livro que deseja obter: ");
			titulo = reader.nextLine();
			List<Pessoa> pessoas = NegocioEstante.procurarTroca(titulo);

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
	* MÃ©todo que permite ao usuÃ¡rio procurar por um livro especÃ­fico. Caso encontre, todas as informaÃ§Ãµes 
	* do livro serÃ£o mostradas na tela para o usuÃ¡rio.
	*
	* @return 		0, caso sucesso
	* @since 		1.0
	*/
	public static int consultarLivro(){
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
			livro =  NegocioEstante.consultarLivro(nomeLivro);

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
	* MÃ©todo que permite ao usuÃ¡rio procurar por um usuÃ¡rio especÃ­fico. Caso encontre, todas as informaÃ§Ãµes 
	* do livro serÃ£o mostradas na tela para o usuÃ¡rio.
	*
	* @return 		0, caso sucesso
	* @since 		1.0
	*/
	public static int consultarUsuario(){
		Scanner reader = new Scanner(System.in);
		String opcao = "";
		String nomePessoa;
		Pessoa pessoa = null;

		while(!opcao.equals("n") && !opcao.equals("N")){
			System.out.println("\n\n\n ------------ CONSULTAR PESSOA --------------");
			System.out.println("Escreva o nome da pessoa desejada: ");

			nomePessoa = reader.nextLine();
			pessoa =  NegocioEstante.consultarUsuario(nomePessoa);

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