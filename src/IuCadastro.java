import java.util.Scanner;

class IuCadastro{
	public static int cadastrar(){
		PersistPessoa persistPessoa = new PersistPessoa();
		Scanner reader = new Scanner(System.in);
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
			IuLogin.teste();
		}else{
			System.out.println("Erro na validacao de dados");
		}
		
		return 0;
	}
}