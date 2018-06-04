class NegocioLogin{
	public static Pessoa login(String apelido, String senha){
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = persistPessoa.buscarPessoa(apelido, senha);
		return p;
	}

}