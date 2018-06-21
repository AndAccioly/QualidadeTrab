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
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = persistPessoa.buscarPesPorNome(nome);
		if(p != null){
			return false;
		}

		return true;
	}

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