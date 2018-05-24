class LivroTeste{
	Livro livro;

	public static int rodaTeste(){
		return 0;
	}

	private int setGetTeste(){
		Livro livro = new Livro();
		if(testaStr(livro.getTitulo, "000000")){
			//ok
		}else{
			//erro
		}

	}


	public bool testaStr(String esperado, String testado){
		if(esperado.equals(testado)){
			return true;
		}

		return false;
	}
}