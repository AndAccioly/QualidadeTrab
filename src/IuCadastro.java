import java.util.Scanner;

/**
* Classe de Interface de usuário. As interfaces implementadas nesse projeto são simples com contato por terminal com o
* usuário. Elas se comunicam com as classes de negócio para lógica de manejamento de dados.
* <p>
* A classe IuCadastro pega as informações do usuário necessárias para cadastrá-lo e avisa o usuário se foi possível 
* cadastrar ou não.
*
* @author Andre Accioly
* @author Tiago Kfouri
* @version %I%, %G%
* @since 1.0
* @see NegocioCadastro
*/
public class IuCadastro{

	/**
	* Método que recebe as informações do usuário por meio do terminal para validar seu cadastro. São elas: nome, 
	* apelido, senha e telefone (no formato XX-XXXXXXXXX). Imprime na tela se foi possível cadastrar ou não. Caso 
	* o cadastro tenha sucesso, ela é direcionada para a interface de login
	*
	* @return 			um inteiro 0, caso sucesso
	* @see NegocioCadastro
	* @see IuLogin
	* @since 			1.0
	*/
	public static int cadastrar(){
		Scanner reader = new Scanner(System.in);
		System.out.println("\n\n\n ------------ CADASTRO --------------");
		System.out.println("Insira seu nome: ");
		String nome = reader.nextLine();

		System.out.println("Insira um apelido: ");
		String apelido = reader.nextLine();

		System.out.println("Insira uma senha: ");
		String senha = reader.nextLine();

		System.out.println("Insira seu telefone no formato XX-XXXXXXXXX: ");
		String telefone = reader.nextLine();

		if(NegocioCadastro.validarCadastro(nome, apelido, senha, telefone) == 0){
			System.out.println("Pessoa cadastrada com sucesso.");
			IuLogin.login();
		}else{
			System.out.println("Erro na validacao de dados");
		}
		
		reader.close();
		return 0;
	}
}