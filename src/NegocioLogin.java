class NegocioLogin{
	public static Pessoa login(String apelido, String senha){
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = persistPessoa.buscarPesPorApel(apelido);
		String senhaCripto = criptografarSenha(senha);

		if(p.getSenha().equals(senhaCripto) && p != null){
			return p;
		}

		return null;
	}

	public static String criptografarSenha(String senha){
		String s = "";
		for(int i = 0; i < senha.length(); i++){
			s = s + (char)((int) senha.charAt(i) + 1);
		}

		return s;
	}

}