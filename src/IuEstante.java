import java.util.Scanner;
import java.util.List;

/**
* Classe de Interface de usuário. As interfaces implementadas nesse projeto são simples com contato por terminal com o
* usuário. Elas se comunicam com as classes de negócio para lógica de manejamento de dados.
* <p>
* A classe IuEstante possui todos os métodos de interação com o usuário relativos à estante. São eles: adicionar livro 
* na estante, remover livro da estante, consultar dados de livro, consultar usuário, escrever resenha de livro e 
* procurar livro para troca.
*
* @author Andre Accioly
* @author Tiago Kfouri
* @version %I%, %G%
* @since 1.0
* @see NegocioEstante
* @see Pessoa
* @see Livro
* @see Estante
*/
class IuEstante{

	/**
	* Método que mostra a estante do usuário. Depois mostra um menu com as opções que o usuário pode 
	* fazer com a estante.
	*
	* @param p 			a pessoa cuja estante será mostrada
	* @return 			um inteiro 0, caso sucesso
	* @see Estante
	* @see Livro
	* @since 			1.0
	*/
	public static int mostrarEstante(Pessoa p){
		Scanner reader = new Scanner(System.in);
		int opcao = 0;
		while(opcao != 8){
			int i = 0;
			Estante e = p.getEstante();

			System.out.println("\n\n\n ------------ SUA ESTANTE --------------");
			if(e == null || e.getLivros().isEmpty()){
				System.out.println("Estante vazia");
			}else{
				for(Livro l : e.getLivros()){
					i++;
					System.out.println(i + ". " + l.getTitulo());
				}
			}

			System.out.println("\n\n\n ------------   OPCOES  --------------");
			System.out.println("1. Adicionar livro a estante");
			System.out.println("2. Remover livro da estante");
			System.out.println("3. Consultar dados de livro");
			System.out.println("4. Consultar usuario");
			System.out.println("5. Escrever resenha de livro");
			System.out.println("6. Colocar livro para troca");
			System.out.println("7. Procurar livro para troca");
			System.out.println("8. Encerrar sessao");

			opcao = reader.nextInt();
			reader.nextLine();

			if(opcao == 1){
				e = NegocioEstante.colocarNaEstante(e, p);
			}else if(opcao == 2){
				e = NegocioEstante.removerDaEstante(e, p);
			}else if(opcao == 3){
				NegocioEstante.consultarLivro();
			}else if(opcao == 4){
				NegocioEstante.consultarUsuario();
			}else if(opcao == 5){
				NegocioEstante.escreverResenha();
			}else if(opcao == 6){
				NegocioEstante.informarTroca(p);
			}else if(opcao == 7){
				NegocioEstante.procurarTroca();
			}
		}
		return 0;
	}
}