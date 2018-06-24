import java.util.List;
import java.util.Scanner;

/**
* Classe de mediação entre a Interface de Usuário e as classes de Persist. Essa classe não interage com o banco de dados
* diretamente, necessitando dos métodos das classes de Persist para manusear os dados necessários no banco de dados.
* <p>
* A classe NegocioEstante possui todos os métodos para tranferência de dados com o banco de dados relacionados com a estante. 
* As operações possíveis são: criar livro, colocar na estante, buscar livro, remover da estante, escrever resenha, informar 
* troca, procurar troca, consultar livro e consultar usuário.
*
* @author Andre Accioly
* @author Tiago Kfouri
* @version %I%, %G%
* @since 1.0
* @see PersistPessoa
* @see Pessoa
*/
class NegocioEstante{


	/**
	* Método para ccriar um novo objeto Livro e gravá-lo no banco de dados
	*
	* @param titulo 		o titulo do livro
	* @param nomeAutor 		o nome do autor do livro
	* @param dtPublicacao 	a data de publicação do livro
	* @param genero 		O gênero literário do livro
	* @return 				um objeto Livro com os dados passados por parâmetro
	* @see PersistLivro
	* @since 				1.0
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
	
	/**
	* Método para colocar um livro na estante.
	*
	* @param e 		a estante do usuário
	* @param livro 	o livro a ser colocado na estante
	* @param p 		o usuário que quer adicionar o livro na estante
	* @return 		a estante do usuário, agora com o livro adicionado, caso sucesso, ou sem modificação, 
	*				caso erro
	* @see PersistPessoa
	* @since 		1.0
	*/
	public static Estante colocarNaEstante(Estante e, Livro livro, Pessoa p){
		PersistPessoa persistPessoa = new PersistPessoa();
		e.adicionaLivro(livro);
		persistPessoa.associarLivro(livro, p);
		return e;
	}

	/**
	* Método para buscar um livro no banco de dados a partir de seu título
	*
	* @param titulo o titulo do livro
	* @return 		um objeto Livro com as informações do livro procurado pelo título
	* @see PersistLivro
	* @see Livro
	* @since 		1.0
	*/
	public static Livro buscarLivro(String titulo){
		PersistLivro persistLivro = new PersistLivro();
		Livro livro;
		livro =  persistLivro.buscarLivroNome(titulo);		
		return livro;
	
	}

	/**
	* Método que remove um livro especificado da estante, caso encontre.
	*
	* @param e 		a estante cujo livro será procurado
	* @param l 		o livro que será removido
	* @param p 		a pessoa dona da estante
	* @return 		a estante modificada agora sem o livro, caso tenha removido ou a estante intácta, 
	*				caso não tenha encontrado o livro
	* @since 		1.0
	*/	
	public static Estante removerDaEstante(Estante e, Livro l, Pessoa p){
		PersistLivro persistLivro = new PersistLivro();
		PersistPessoa persistPessoa = new PersistPessoa();

		persistPessoa.desassociarLivro(l.getTitulo(), p);
		persistLivro.subtrairQuantLiv(l.getCod(), 1);
		e.removeLivro(l);
		
		return e;
	}

	/**
	* Método que coloca uma resenha em um livro.
	*
	* @param livro 		o livro o qual será adicionado a resenha
	* @param resenha 	a resenha a ser salva no livro
	* @return 			0, caso sucesso
	* @since 			1.0
	*/
	public static int escreverResenha(Livro livro, String resenha){
		PersistLivro persistLivro = new PersistLivro();
		
		persistLivro.escreverResenha(livro, resenha);
			
		return 0;
	}

	/**
	* Método para o usuário informar que deseja realizar uma troca de livro com outro usuário. Ele 
	* informa o nome do livro que deseja colocar pra trocar.
	*
	* @param titulo o titulo do livro que será oferecido para troca
	* @param p 		o usuário que irá colocar o livro para troca
	* @return 		0, caso sucesso
	* @see persistPessoa
	* @since 		1.0
	*/
	public static int informarTroca(String titulo, Pessoa p){
		PersistPessoa persistPessoa = new PersistPessoa();

		int result = persistPessoa.informarTroca(titulo, p);

		return result;
	}

	/**
	* Método para listar todas as pessoas que estão procurando um livro específico
	*
	* @param titulo	o titulo do livro cujas pessoas estão à procura
	* @return 		uma lista com todas as pessoas que estão à procura do livro especificado
	* @since 		1.0
	*/
	public static List<Pessoa> procurarTroca(String titulo){
		PersistPessoa persistPessoa = new PersistPessoa();
		
		List<Pessoa> pessoas = persistPessoa.procurarTroca(titulo);
		
		return pessoas;
	}

	/**
	* Método para buscar um livro no banco de dados pelo título indicado.
	*
	* @param titulo o titulo do livro que será consultado
	* @return 		um objeto Livro com o livro buscado
	* @since 		1.0
	*/
	public static Livro consultarLivro(String titulo){
		PersistLivro persistLivro = new PersistLivro();
		Livro livro =  persistLivro.buscarLivroNome(titulo);

		return livro;
	}

	/**
	* Método que permite ao usuário procurar por um usuário específico.
	*
	* @param nome 	o nome do usuário
	* @return 		um objeto Pessoa com o usuário buscado
	* @since 		1.0
	*/
	public static Pessoa consultarUsuario(String nome){
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa pessoa =  persistPessoa.buscarPesPorNome(nome);

		return pessoa;
	}
	
}