import java.util.List;

/**
* Classe que representa o objeto Pessoa. Acerca de cada pessoa, serão armazenados os seguintes dados: 
* tipo (adm=0 ou cliente=1), nome, apelido, senha, estante e telefone.
*
* @author Andre Accioly
* @author Tiago Kfouri
* @version %I%, %G%
* @since 1.0
*/
public class Pessoa{
	private int tipo;
	private String nome;
	private String apelido;
	private String senha;
	private Estante estante;
	private String telefone;

	/**
	* Construtor da classe Pessoa. Caso nao seja passado parametro, os campos serao iniciados com campos genericos
	* 
	* @see Pessoa
	* @since 1.0
	*/
	public Pessoa(){
		this.nome = "Nome generico";
		this.tipo = 1;
		this.senha = "isto nao e uma senha";
		this.apelido = "Cliente generico";
		this.estante = new Estante();
		this.telefone = "(00) - 000000000";
	}

	/**
	* Construtor da classe Pessoa. Sao passados todos os campos para a criacao da pessoa.
	* 
	* @param nome 			o nome da pessoa.
	* @param apelido 		o apelido da pessoa.
	* @param senha 		 	a senha da pessoa.
	* @param tipo 			o tipo da pessoa (adm=0 ou cliente=1).
	* @param estante 		a estante da pessoa.
	* @param telefone 		o telefone da pessoa.
	* @see Pessoa
	* @since 1.0
	*/
	public Pessoa(String nome, String apelido, String senha, int tipo, Estante estante, String telefone){
		this.nome = nome;
		this.apelido = apelido;
		this.senha = senha;
		this.tipo = tipo;
		this.estante = estante;
		this.telefone = telefone;
	}

	/**
	* Metodo para pegar o nome da pessoa.
	*
	* @return 	o nome da pessoa
	* @since 	1.0
	*/
	public String getNome(){
		return this.nome;
	}

	/**
	* Seta o nome da pessoa.
	*
	* @param nome 	o nome da pessoa
	* @since 		1.0
	*/
	public void setNome(String nome){
		this.nome = nome;
	}

	/**
	* Metodo para pegar a senha da pessoa.
	*
	* @return 	a senha da pessoa
	* @since 	1.0
	*/
	public String getSenha(){
		return this.senha;
	}

	/**
	* Seta a senha da pessoa.
	*
	* @param senha 	a senha da pessoa
	* @since 		1.0
	*/
	public void setSenha(String senha){
		this.senha = senha;
	}

	/**
	* Metodo para pegar o apelido da pessoa.
	*
	* @return 	o apelido da pessoa
	* @since 	1.0
	*/
	public String getApelido(){
		return this.apelido;
	}

	/**
	* Seta o apelido da pessoa.
	*
	* @param apelido 	o apelido da pessoa
	* @since 			1.0
	*/
	public void setApelido(String apelido){
		this.apelido = apelido;
	}

	/**
	* Metodo para pegar o tipo da pessoa.
	*
	* @return 	o tipo da pessoa
	* @since 	1.0
	*/
	public int getTipo(){
		return this.tipo;
	}

	/**
	* Seta o tipo da pessoa.
	*
	* @param tipo 	o tipo da pessoa
	* @since 		1.0
	*/
	public void setTipo(int tipo){
		this.tipo = tipo;
	}

	/**
	* Metodo para pegar a estante da pessoa.
	*
	* @return 	a estante da pessoa
	* @since 	1.0
	*/
	public Estante getEstante(){
		return this.estante;
	}

	/**
	* Seta a estante da pessoa.
	*
	* @param estante 	a estante da pessoa
	* @since 			1.0
	*/
	public void setEstante(Estante estante){
		this.estante = estante;
	}

	/**
	* Metodo para pegar o telefone da pessoa.
	*
	* @return 	o telefone da pessoa
	* @since 	1.0
	*/
	public String getTelefone(){
		return this.telefone;
	}

	/**
	* Seta o telefone da pessoa.
	*
	* @param telefone 	o telefone da pessoa
	* @since 			1.0
	*/
	public void setTelefone(String telefone){
		this.telefone = telefone;
	}

	/**
	* Metodo que retorna todos os campos da pessoa, em formato de string, ordenados em 
	* "nome;apelido;senha;tipo;telefone;codigos dos livros da estante"
	*
	* @return 	uma string com todos os campos da pessoa
	* @see 		Estante
	* @since 	1.0
	*/
	public String camposEmStr(){
		String str = nome + ";" + apelido + ";" + senha + ";" +  tipo + ";" + telefone + ";" + estante.codEmStr();
		return str;
	}

}