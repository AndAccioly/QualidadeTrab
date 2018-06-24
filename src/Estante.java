import java.util.List;
import java.util.ArrayList;

/**
* Classe que representa o objeto Estante, onde serao armazenados objetos
* da classe Livro.
*
* @author Andre Accioly
* @author Tiago Kfouri
* @version %I%, %G%
* @since 1.0
*/
class Estante{

	private List<Livro> livros;

	/**
	* Construtor da classe Estante. Caso nao seja passado parametro, e criada
	* uma nova lista de Livro.
	* 
	* @see Estante
	* @see Livro
	* @since 1.0
	*/
	public Estante(){
		this.livros = new ArrayList<Livro>();
	}

	/**
	* Construtor da classe Estante. A lista de Livro e passada como parametro.
	* 
	* @param livros		lista com os livros a serem colocados na estante
	* @see Estante
	* @see Livro
	* @since 			1.0
	*/
	public Estante(List<Livro> livros){
		this.livros = livros;
	}

	/**
	* Metodo que retorna todos os livros da estante. Eles nao sao retirados
	* da estante.
	*
	* @return 	uma lista com os livros da estante
	* @since 	1.0
	*/
	public List<Livro> getLivros(){
		return this.livros;
	}

	/**
	* Metodo para setar os livros da estante. Importante notar que os livros
	* atuais na estante serao retirados.
	*
	* @param livros 	uma lista com os livros a serem colocados na estante
	* @since 1.0
	*/
	public void setLivros(List<Livro> livros){
		this.livros = livros;
	}

	/**
	* Metodo que adiciona um livro a estante.
	*
	* @param livro 	o livro a ser adicionado na estante
	* @return 		0 caso sucesso
	* @since 		1.0
	*/
	public int adicionaLivro(Livro livro){
		this.livros.add(livro);
		return 0;
	}

	/**
	* Metodo que busca um livro na estante pelo seu título.
	*
	* @param titulo 	o titulo do livro a ser procurado na estante
	* @return 			um objeto Livro com o livro procurado. null, caso não encontre
	* @see 	Livro
	* @since 			1.0
	*/
	public Livro buscaLivro(String titulo){
		for(Livro l : livros){
			if(l.getTitulo().equals(titulo)){
				return l;
			}
		}
		return null;
	}

	/**
	* Metodo que remove o livro especificado da estante.
	*
	* @param livro 	o livro a ser removido da estante
	* @return 		0 caso sucesso
	* @since 		1.0
	*/
	public int removeLivro(Livro livro){
		int i = 0;
		for(Livro l : livros){
			if(l.getTitulo().equals(livro.getTitulo())){
				livros.remove(i);
				return 0;
			}

			i++;
		}

		return 1;
	}

	/**
	* Metodo que retorna todos os campos dos livros presentes na estante em formato
	* de string.
	*
	* @return 	uma string com todos campos dos livros da estante
	* @see 		Livro
	* @see 		camposEmStr
	* @since 	1.0
	*/
	public String camposEmStr(){
		String str = "";
		for(Livro l : livros){
			str += l.camposEmStr();
		}

		return str;
	}

	/**
	* Metodo que retorna todos os codigos dos livros presentes na estante em formato
	* de string.
	*
	* @return 	uma string com todos codigos dos livros da estante
	* @see 		Livro
	* @see 		codEmStr
	* @since 	1.0
	*/
	public String codEmStr(){
		String str = "";
		for(Livro l : livros){
			str += l.getCod() + ";";
		}

		return str;
	}

}