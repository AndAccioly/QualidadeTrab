import java.util.List;
import java.util.ArrayList;
/**
* Classe que representa o objeto Livro. Acerca de cada livro, serão armazenados os seguintes dados: 
* título, nome do autor, data de publicação, código, gênero literário e número de livros
*
* @author 	Andre Accioly
* @author 	Tiago Kfouri
* @version 	%I%, %G%
* @since 	1.0
*/
public class Livro{
	private String titulo;
	private String nomeAutor;
	private String dtPublicacao;
	private String cod;
	private String genero;
	private int quantidade;
	private List<String> resenhas;

	/**
	* Construtor da classe Livro. Caso nao seja passado parametro, os campos serao iniciados com campos genéricos
	* 
	* @see Livro
	* @since 1.0
	*/
	public Livro(){
		this.titulo = "Titulo generico";
		this.nomeAutor = "Autor generico";
		this.dtPublicacao = "00/00/0000";
		this.cod = "000000";
		this.genero = "Genero generico";
		this.resenhas = new ArrayList<String>();
		this.quantidade = 0;

	}

	/**
	* Construtor da classe Livro. Sao passados todos os campos para a criacao do livro.
	* 
	* @param titulo 		o titulo do livro.
	* @param nomeAutor 		o nome do autor do livro.
	* @param dtPublicacao 	a data de publicacao do livro.
	* @param cod 			o codigo do livro.
	* @param genero 		o genero do livro.
	* @param quantidade 	a quantidade de livros.
	* @param resenhas 		uma lista de strings, onde cada elemento é uma resenha escrita por um usuário
	* @see Livro
	* @since 				1.0
	*/
	public Livro(String titulo, String nomeAutor, String dtPublicacao, String cod, String genero, int quantidade, List<String> resenhas){
		this.titulo = titulo;
		this.nomeAutor = nomeAutor;
		this.dtPublicacao = dtPublicacao;
		this.cod = cod;
		this.genero = genero;
		this.quantidade = quantidade;
		this.resenhas = resenhas;
	}

	/**
	* Metodo para pegar as resenhas do livro.
	*
	* @return 	uma lista de strings, onde cada elemento é uma resenha escrita por um usuário
	* @since 	1.0
	*/
	public List<String> getResenhas(){
		return this.resenhas;
	}

	/**
	* Seta as resenhas do livro.
	*
	* @param resenhas 	as resenhas escritas sobre oo livro
	* @since 			1.0
	*/
	public void setResenhas(List<String> resenhas){
		this.resenhas = resenhas;
	}

	/**
	* Adiciona uma resenha à lista de resenhas do livro.
	*
	* @param resenha 	a resenha escrita sobre oo livro
	* @since 			1.0
	*/
	public void adicionarResenha(String resenha){
		this.resenhas.add(resenha);
	}

	/**
	* Metodo para pegar o titulo do livro.
	*
	* @return 	o titulo do livro
	* @since 	1.0
	*/
	public String getTitulo(){
		return this.titulo;
	}

	/**
	* Seta o titulo do livro.
	*
	* @param titulo 	o titulo do livro
	* @since 			1.0
	*/
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}

	/**
	* Metodo para pegar o nome do autor do livro.
	*
	* @return 	o nome do autor do livro
	* @since 	1.0
	*/
	public String getNomeAutor(){
		return this.nomeAutor;
	}

	/**
	* Seta o nome do autor do livro.
	*
	* @param nomeAutor 	o nome do autor do livro
	* @since 			1.0
	*/
	public void setNomeAutor(String nomeAutor){
		this.nomeAutor = nomeAutor;
	}

	/**
	* Metodo para pegar a data de publicacao do livro.
	*
	* @return 	a data de publicacao do livro
	* @since 	1.0
	*/
	public String getDtPublicacao(){
		return this.dtPublicacao;
	}

	/**
	* Seta a data de publicacao do livro.
	*
	* @param dtPublicacao 	a data de publicacao do livro
	* @since 				1.0
	*/
	public void setDtPublicacao(String dtPublicacao){
		this.dtPublicacao = dtPublicacao;
	}

	/**
	* Metodo para pegar o codigo do livro.
	*
	* @return 	o codigo do livro
	* @since 	1.0
	*/
	public String getCod(){
		return this.cod;
	}

	/**
	* Seta o codigo do livro.
	*
	* @param cod 	o codigo do livro
	* @since 		1.0
	*/
	public void setCod(String cod){
		this.cod = cod;
	}

	/**
	* Metodo para pegar o genero do livro.
	*
	* @return 	o genero do livro
	* @since 	1.0
	*/
	public String getGenero(){
		return this.genero;
	}

	/**
	* Seta o genero do livro.
	*
	* @param genero		o genero do livro
	* @since 			1.0
	*/
	public void setGenero(String genero){
		this.genero = genero;
	}

	/**
	* Metodo para pegar a quantidade de livros.
	*
	* @return 	a quantidade de livros
	* @since 	1.0
	*/
	public int getQuantidade(){
		return this.quantidade;
	}

	/**
	* Seta a quantidade de livros.
	*
	* @param quantidade 	a quantidade de livros
	* @since 				1.0
	*/
	public void setQuantidade(int quantidade){
		this.quantidade = quantidade;
	}

	/**
	* Retorna uma string com todos os campos do livro ordenados em "codigo;titulo;nome do autor;data de publicacao;genero;quantidade"
	*
	* @return 	uma string com todos os campos do livro
	* @since 	1.0
	*/
	public String camposEmStr(){
		String str = cod + ";" + titulo + ";" + nomeAutor + ";" + dtPublicacao + ";" + genero + ";" + quantidade;

		for(String s : resenhas){
			str += ";" + s;
		}

		return str;
	}

}