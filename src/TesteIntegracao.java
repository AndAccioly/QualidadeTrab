class TesteIntegracao{
	Livro livro;

	public static int rodaTeste(){
		return 0;
	}

	private static void integrLogin(){
		System.out.println("Iniciando teste de integracao da classe LoginNegocio e persistência...");
		String apelido, senha;
		Pessoa p;
		//teste de login sucedido
		apelido = "teste";
		senha = "abcd";
		p = NegocioLogin.login(apelido, senha);
		if(p == null){
			System.out.println("");
		}

		//teste de login que deve falhar
		apelido = "apelido inexistente";


	}

	private static void integrCadastro(){
		System.out.println("Iniciando teste de integracao da classe CadastroNegocio e persistência...");


	}

	private static void integrLoginCad(){
		System.out.println("Iniciando teste de integracao da classe CadastroNegocio, LoginNegocio e persistência...");

	}


}
