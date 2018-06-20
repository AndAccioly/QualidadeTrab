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
	* Método q busca uma pessoa com o apelido e a senha especificados no banco de dados
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
		Pessoa p = persistPessoa.buscarPessoa(apelido, senha);
		return p;
	}

}