import java.util.Scanner;

/**
* Classe de Interface de usuário. As interfaces implementadas nesse projeto são simples com contato por terminal com o
* usuário. Elas se comunicam com as classes de negócio para lógica de manejamento de dados.
* <p>
* A classe IuLogin cuida de fazer o login do usuário, verificando se seu usuário e senha estão corretos
*
* @author 	Andre Accioly
* @author 	Tiago Kfouri
* @version 	%I%, %G%
* @since 	1.0
*/
class IuLogin{

	/**
	* Método que mostra a tela de login com as opções de logar, cadastrar novo usuário ou sair. Caso ele 
	* escolha a opção de logar, pergunta usuário e senha e verifica se eles estão no banco de dados. Depois, 
	* retorna para esse menu de opções, caso não encontre o usuário, mostrando uma mensagem de que não encontrou 
	* o usuário. Caso encontre o usuário, é redirecionado para a tela que mostra a estante do usuário. Se 
	* o usuário escolher cadastrar, ele é redirecionado para a tela de cadastro. Se ele quiser sair, o método 
	* termina.
	*
	* @return 			void
	* @see Pessoa
	* @see mostrarEstante
	* @see cadastrar
	* @since 			1.0
	*/	
	public static void login(){
		int opcao = -1;
		PersistPessoa persistPessoa = new PersistPessoa();
		Scanner reader = new Scanner(System.in);

		while(opcao != 0){
			System.out.println("\n\n\n ------------ LOGIN - TROCA DE LIVROS --------------");	
			System.out.println("1. Login");
			System.out.println("2. Cadastrar");
			System.out.println("0. Sair");
			opcao = reader.nextInt();
			reader.nextLine();

			if(opcao == 1){
				System.out.println("Digite seu usuario: ");
				String nome = reader.nextLine();
				System.out.println("Digite sua senha: ");
				String senha = reader.nextLine();

				Pessoa p = NegocioLogin.login(nome,senha);
				if(p != null){
					IuEstante.mostrarEstante(p);
				}else{
					System.out.println("Usuario ou senha invalido.");
				}
			}else if(opcao == 2){
				IuCadastro.cadastrar();
			}
		}
	}
}