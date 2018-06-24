/**
* Classe de mediação entre a Interface de Usuário e as classes de Persist. Essa classe não interage com o banco de dados
* diretamente, necessitando dos métodos das classes de Persist para manusear os dados necessários no banco de dados.
* <p>
* A classe NegocioLogin permite fazer o login da pessoa verificando se ela está no banco de dados
*
* @author Andre Accioly
* @author Tiago Kfouri
* @version %I%, %G%
* @since 1.0
* @see PersistPessoa
* @see Pessoa
*/
class NegocioLogin{

	/**
	* Método que busca uma pessoa com o apelido e a senha especificados no banco de dados
	*
	* @param apelido	uma string com o apelido da pessoa a ser procurada no banco de dados
	* @param senha 	 	uma string com a senha da pessoa a ser procurada no banco de dados
	* @return 			um objeto Pessoa com os dados da pessoa procurada, caso sucesso. null, caso erro
	* @see Pessoa
	* @see PersistPessoa
	* @see buscarPessoa
	* @since 1.0
	*/	
	public static Pessoa login(String apelido, String senha){
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = persistPessoa.buscarPesPorApel(apelido);
		String senhaCripto = criptografarSenha(senha);

		if(p != null){
			if(p.getSenha().equals(senhaCripto)){
				return p;
			}
		}

		return null;
	}

	/**
	* Método que criptografa uma senha. Foi feita uma criptografia bem simples, por ser somente um trabalho acadêmico, onde o 
	* caractere é transformado no seu subsequente da tabela ASCII.
	*
	* @param senha 	 	uma string com a senha a ser criptografada
	* @return 			uma string com a senha após ser criptografada
	* @see Pessoa
	* @since 1.0
	*/
	public static String criptografarSenha(String senha){
		String s = "";
		for(int i = 0; i < senha.length(); i++){
			s = s + (char)((int) senha.charAt(i) + 1);
		}

		return s;
	}

}