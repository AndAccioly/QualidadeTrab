import java.util.List;
import java.util.Scanner;

class NegocioEstante{
	//@todo
	/**
	* ...
	*
	* @param param 	descricao
	* @return 		retorno
	* @since 		1.0
	*/
	public static Livro criarLivro(String titulo, String nomeAutor, String dtPublicacao, String genero){
		PersistLivro persistLivro = new PersistLivro();
		Livro livro = new Livro();
		int result = 1;
		
		livro.setCod(persistLivro.geraCodigo());
		livro.setTitulo(titulo);
		livro.setNomeAutor(nomeAutor);
		livro.setDtPublicacao(dtPublicacao);
		livro.setGenero(genero);
		livro.setQuantidade(1);

		result =  persistLivro.gravarLivro(livro);
		if(result == 0){
			return livro;	
		}else{
			return null;
		}
		
	}
	
	public static Estante colocarNaEstante(Estante e, Livro livro, Pessoa p){
		PersistPessoa persistPessoa = new PersistPessoa();
		e.adicionaLivro(livro);
		persistPessoa.associarLivro(livro, p);
		return e;
	}


	/**
	* MÃ©todo que pergunta para o usuÃ¡rio o nome de um livro a ser removido da estante e depois 
	* remove o livro, caso encontre. Depois pergunta se quer remover outro livro.
	*
	* @param e 		a estante cujo livro serÃ¡ procurado
	* @return 		a estante modificada agora sem o livro, caso tenha removido ou a estante intÃ¡cta, 
	*				caso nÃ£o tenha encontrado o livro
	* @since 		1.0
	*/
	public static Livro buscarLivro(String titulo){
		PersistLivro persistLivro = new PersistLivro();
		Livro livro;
		livro =  persistLivro.buscarLivroNome(titulo);		
		return livro;
	
	}
	
	public static Estante removerDaEstante(Estante e, Livro l, Pessoa p){
		PersistLivro persistLivro = new PersistLivro();
		PersistPessoa persistPessoa = new PersistPessoa();

		persistPessoa.desassociarLivro(l.getTitulo(), p);
		persistLivro.subtrairQuantLiv(l.getCod(), 1);
		e.removeLivro(l);
		
		return e;
	}

	/**
	* MÃ©todo que permite ao usuÃ¡rio escrever uma resenha sobre um livro especÃ­fico, que nÃ£o precisa estar na 
	* estante. 
	*
	* @return 		0, caso sucesso
	* @since 		1.0
	*/
	public static int escreverResenha(Livro livro, String resenha){
		PersistLivro persistLivro = new PersistLivro();
		
		persistLivro.escreverResenha(livro, resenha);
			
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
	public static int informarTroca(String titulo, Pessoa p){
		PersistPessoa persistPessoa = new PersistPessoa();

		int result = persistPessoa.informarTroca(titulo, p);

		return result;
	}

//@todo
	/**
	* ...
	*
	* @param param 	descricao
	* @return 		retorno
	* @since 		1.0
	*/
	public static List<Pessoa> procurarTroca(String titulo){
		PersistPessoa persistPessoa = new PersistPessoa();
		
		List<Pessoa> pessoas = persistPessoa.procurarTroca(titulo);
		
		return pessoas;
	}

	/**
	* MÃ©todo que permite ao usuÃ¡rio procurar por um livro especÃ­fico. Caso encontre, todas as informaÃ§Ãµes 
	* do livro serÃ£o mostradas na tela para o usuÃ¡rio.
	*
	* @return 		0, caso sucesso
	* @since 		1.0
	*/
	public static Livro consultarLivro(String titulo){
		PersistLivro persistLivro = new PersistLivro();
		Livro livro =  persistLivro.buscarLivroNome(titulo);

		return livro;
	}

	/**
	* MÃ©todo que permite ao usuÃ¡rio procurar por um usuÃ¡rio especÃ­fico. Caso encontre, todas as informaÃ§Ãµes 
	* do livro serÃ£o mostradas na tela para o usuÃ¡rio.
	*
	* @return 		0, caso sucesso
	* @since 		1.0
	*/
	public static Pessoa consultarUsuario(String nome){
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa pessoa =  persistPessoa.buscarPesPorNome(nome);

		return pessoa;
	}
	
}