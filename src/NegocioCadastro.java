/**
* Classe de mediação entre a Interface de Usuário e as classes de Persist. Essa classe não interage com o banco de dados
* diretamente, necessitando dos métodos das classes de Persist para manusear os dados necessários no banco de dados.
* <p>
* A classe NegocioCadastro permite validar cadastro, apenas. Para isso, utiliza de seus métodos auxiliares e privados: 
* validar e criptografar senha, validar nome, validar apelido e validar telefone.
*
* @author Andre Accioly
* @author Tiago Kfouri
* @version %I%, %G%
* @since 1.0
* @see PersistPessoa
* @see Pessoa
*/
class NegocioCadastro{

	/**
	* Método q recebe todos os parâmetros de um objeto Pessoa (nome, apelido, senha e telefone) e verifica se o nome 
	* já existe, se o apelido já existe e se o telefone está no formato correto. Ela utiliza dos métodos auxiliares 
	* da classe NegocioCadastro para fazer isso. São eles: validarNome, validarApelido e validarTelefone.
	*
	* @param nome 		uma string com o nome da pessoa a ser validada
	* @param apelido 	uma string com o apelido da pessoa a ser validada
	* @param senha 		uma string com a senha da pessoa a ser validada
	* @param telefone 	uma string com o telefone da pessoa a ser validada
	* @return 			0, caso sucesso. 1, caso erro
	* @see Pessoa
	* @see PersistPessoa
	* @see ValidarNome
	* @see ValidarApelido
	* @see ValidarTelefone
	* @since 1.0
	*/	
	public static int validarCadastro(String nome, String apelido, String senha, String telefone){

		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = new Pessoa();

		if(!validarNome(nome)){
			System.out.println("Nome ja existente.");
			return 1;
		}

		if(!validarApelido(apelido)){
			System.out.println("Apelido ja existente.");
			return 1;
		}

		if(!validarTelefone(telefone)){
			System.out.println("Telefone no formato incorreto.");
			return 1;
		}

		p.setNome(nome);
		p.setApelido(apelido);
		p.setSenha(senha);
		p.setTelefone(telefone);
		p.setTipo(1);

		int result = persistPessoa.gravarPessoa(p);
		if(result == 1){
			System.out.println("Erro ao gravar pessoa no banco.");
			return 1;
		}


		return 0;
	}

//@todo
	/**
	* Método auxiliar do método validarCadastro para validar uma senha de uma pessoa. Também criptografa a senha
	* de acordo com...
	*
	* @param senha 	uma string com a senha que será validada
	* @return 		a senha depois de ser criptografada. null caso haja um erro
	* @since 		1.0
	*/
	private String validarECriptografarSenha(String senha){
		return "";
	}

	/**
	* Método auxiliar do método validarCadastro para validar um nome de uma pessoa, ou seja, verificar se ele já 
	* existe no banco de dados.
	*
	* @param nome 		uma string com o nome que será validado
	* @return 			um booleano true, caso sucesso
	* @see PersistPessoa
	* @see buscarPessoa
	* @since 			1.0
	*/
	private Boolean validarNome(String nome){
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = persistPessoa.buscarPessoa(apelido);
		return true;
	}

	/**
	* Método auxiliar do método validarCadastro para validar um apelido de uma pessoa, ou seja, verificar se ele já 
	* existe no banco de dados.
	*
	* @param apelido 	uma string com o apelido que será validado
	* @return 			um booleano true, caso sucesso
	* @see PersistPessoa
	* @see buscarPessoa
	* @since 			1.0
	*/
	private Boolean validarApelido(String apelido){
		return true;
	}

	/**
	* Método auxiliar do método validarCadastro para validar um telefone de uma pessoa, ou seja, verificar se ele já 
	* existe no banco de dados.
	*
	* @param telefone 	uma string com o telefone que será validado
	* @return 			um booleano true, caso sucesso
	* @see PersistPessoa
	* @see buscarPessoa
	* @since 			1.0
	*/
	private Boolean validarTelefone(String telefone){
		return true;
	}

}