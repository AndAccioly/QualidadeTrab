class NegocioCadastro{
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

	private String validarECriptografarSenha(String senha){
		return "";
	}

	private Boolean validarNome(String nome){
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = persistPessoa.buscarPessoa(apelido);
		return true;
	}

	private Boolean validarApelido(String apelido){
		return true;
	}

	private Boolean validarTelefone(String telefone){
		return true;
	}

}