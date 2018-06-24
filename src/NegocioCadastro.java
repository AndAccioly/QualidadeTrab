/**
* Classe de mediação entre a Interface de Usuário e as classes de Persist. Essa classe não interage com o banco de dados
* diretamente, necessitando dos métodos das classes de Persist para manusear os dados necessários no banco de dados.
* <p>
* A classe NegocioCadastro permite validar cadastro, apenas. Para isso, utiliza de seus métodos auxiliares e privados: 
* validarECriptografarSenha, validarNome, validarApelido e validarTelefone.
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
	* Método que recebe todos os parâmetros de um objeto Pessoa (nome, apelido, senha e telefone) e verifica se o nome 
	* já existe, se o apelido já existe e se o telefone está no formato correto. Ela utiliza dos métodos auxiliares 
	* da classe NegocioCadastro para fazer isso. São eles: validarNome, validarApelido, validarTelefone e validarSenha.
	*
	* @param nome 		uma string com o nome da pessoa a ser validada
	* @param apelido 	uma string com o apelido da pessoa a ser validada
	* @param senha 		uma string com a senha da pessoa a ser validada
	* @param telefone 	uma string com o telefone da pessoa a ser validada
	* @return 			0, caso sucesso. 1, caso erro
	* @see Pessoa
	* @see PersistPessoa
	* @see validarNome
	* @see validarApelido
	* @see validarTelefone
	* @see validarSenha
	* @since 1.0
	*/	
	public static int validarCadastro(String nome, String apelido, String senha, String telefone){

		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = new Pessoa();
		Estante e = new Estante();

		if(!validarNome(nome)){
			System.out.println("Nome ja existente.");
			return 1;
		}

		if(!validarApelido(apelido)){
			System.out.println("Apelido ja existente.");
			return 2;
		}

		if(!validarTelefone(telefone)){
			System.out.println("Telefone no formato incorreto.");
			return 3;
		}

		if(!validarSenha(senha)){
			System.out.println("Senha invalida.");
			return 4;
		}

		p.setNome(nome);
		p.setApelido(apelido);
		p.setSenha(criptografarSenha(senha));
		p.setTelefone(telefone);
		p.setTipo(1);
		p.setEstante(e);

		int result = persistPessoa.gravarPessoa(p);
		if(result == 1){
			System.out.println("Erro ao gravar pessoa no banco.");
			return 5;
		}

		return 0;
	}

	/**
	* Método auxiliar do método validarCadastro para validar uma senha de uma pessoa, ou seja, verificar se ela contém 
	* apenas caracteres e sem repetição
	*
	* @param senha 		uma string com a senha que será validada
	* @return 			um booleano true, caso sucesso, e false, caso erro
	* @see PersistPessoa
	* @see buscarPessoa
	* @since 			1.0
	*/
	private static Boolean validarSenha(String senha){
		if(!senha.matches("[a-zA-Z]{4}")){
			System.out.println("Senha deve conter apenas caracteres.");
			return false;
		}

		for(int i = 0; i < senha.length(); i++){
			for(int j = i+1; j < senha.length(); j++){
				if(senha.charAt(i) == senha.charAt(j)){
					System.out.println("Senha deve conter caracteres sem repeticao.");
					return false;
				}
			}
		}

		return true;
	}

	/**
	* Método auxiliar do método validarCadastro para validar um nome de uma pessoa, ou seja, verificar se ele já 
	* existe no banco de dados.
	*
	* @param nome 		uma string com o nome que será validado
	* @return 			um booleano true, caso sucesso e false, caso erro
	* @see PersistPessoa
	* @see buscarPessoa
	* @since 			1.0
	*/
	private static Boolean validarNome(String nome){

		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = persistPessoa.buscarPesPorNome(nome);
		if(p != null){
			return false;
		}

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
	private static Boolean validarApelido(String apelido){
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = persistPessoa.buscarPesPorApel(apelido);
		if(p != null){
			return false;
		}
		return true;
	}


	/**
	* Método auxiliar do método validarCadastro para validar um telefone de uma pessoa, ou seja, verificar se ele 
	* está no formato correto (XX-XXXXXXXXX)
	*
	* @param tel 		uma string com o telefone que será validado
	* @return 			um booleano true, caso sucesso e false, caso erro
	* @see PersistPessoa
	* @see buscarPessoa
	* @since 			1.0
	*/
	private static Boolean validarTelefone(String tel){
		if(!tel.contains("-")){
			System.out.println("Telefone sem hifem.");
			return false;
		}
		String[] partes = tel.split("-");

		if(partes[0].length() != 2 || partes[1].length() != 9){
			System.out.println("Tamanho incorreto de uma das partes do telefone.");
			return false;
		}
		if(!partes[0].matches("[0-9]*") || !partes[1].matches("[0-9]*")){
			System.out.println("Telefone deve conter apenas digitos");
			return false;
		}

		return true;
	}

	/**
	* Método auxiliar do método validarCadastro para criptografar uma senha de uma pessoa. 
	* Foi feita uma criptografia bem simples, por ser somente um trabalho acadêmico, onde o 
	* caractere é transformado no seu subsequente da tabela ASCII.
	*
	* @param senha 	uma string com a senha que será criptografada
	* @return 		a senha depois de ser criptografada.
	* @since 		1.0
	*/
	public static String criptografarSenha(String senha){
		String s = "";
		for(int i = 0; i < senha.length(); i++){
			s = s + (char)((int) senha.charAt(i) + 1);
		}

		return s;
	}

}