class NegocioCadastro{
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

<<<<<<< HEAD
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

	private static Boolean validarNome(String nome){
=======
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
>>>>>>> 1919a3c9d841cec3931000982746314b3f68dddf
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = persistPessoa.buscarPesPorNome(nome);
		if(p != null){
			return false;
		}

		return true;
	}

<<<<<<< HEAD
	private static Boolean validarApelido(String apelido){
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = persistPessoa.buscarPesPorApel(apelido);
		return true;
	}

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
=======
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
>>>>>>> 1919a3c9d841cec3931000982746314b3f68dddf
		return true;
	}

	public static String criptografarSenha(String senha){
		String s = "";
		for(int i = 0; i < senha.length(); i++){
			s = s + (char)((int) senha.charAt(i) + 1);
		}

		return s;
	}

}