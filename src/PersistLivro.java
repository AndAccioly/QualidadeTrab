
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

/**
* Classe com todos métodos para trocas de informações com o banco de dados referentes à classe Livro.
* <p>
* Cada usuário terá uma estante virtual onde poderá armazenar até 10 (dez) exemplares de livros.
* <p>
* O usuário deverá informar apelido e senha.
*
* @author Andre Accioly
* @author Tiago Kfouri
* @version %I%, %G%
* @since 1.0
* @see Livro
*/
class PersistLivro{

	private String linkBdLivro = "bd_livro.xml";
	String endline = "\n";
	private File fLivro;

	/**
	* Construtor da classe PeersistLivro. Inicia o ponteiro para o arquivo xml referente ao livro, que será
	* onde serão armazenados os dados.
	* 
	* @see Livro
	* @see PersistLivro
	* @since 1.0
	*/
	public PersistLivro(){
		fLivro = new File(linkBdLivro);
	}

	/**
	* Método para salvar um objeto da classe Livro no banco de dados
	*
	* @param livro 	o livro que será salvo no banco de dados
	* @return 		0, caso sucesso
	* @see Livro
	* @since 1.0
	*/	
	public int gravarLivro(Livro livro){
		String str = livro.camposEmStr() + endline;

		for(Livro l : this.lerTodosLivros()){
			if(livro.getCod().equals(l.getCod())){
				l.setQuantidade(l.getQuantidade() + 1);
			}
			str = str + l.camposEmStr() + endline ;
		}
		
		gravarString(str);

		return 0;
	}

	public String geraCodigo(){
		String codComp = "";
		String codFinal = "000000";
		for(Livro l : this.lerTodosLivros()){
			codComp = l.getCod();
			if(Integer.parseInt(codComp) > Integer.parseInt(codFinal)){
				codFinal = codComp;
			}
		}

		codFinal = Integer.toString(Integer.parseInt(codFinal) + 1);
		if(codFinal.length() == 1){
			codFinal = "00000" + codFinal;
		}else if (codFinal.length() == 2){
			codFinal = "0000" + codFinal;
		}else if (codFinal.length() == 3){
			codFinal = "000" + codFinal;
		}else if (codFinal.length() == 4){
			codFinal = "00" + codFinal;
		}else if (codFinal.length() == 5){
			codFinal = "0" + codFinal;
		}
		
		return codFinal;
	}

	/**
	* Método para buscar um objeto da classe Livro do banco de dados através de seu código.
	* Caso não encontre, é printado uma mensagem de alerta na tela e retorna-se null.
	* O livro não é deletado do banco de dados.
	*
	* @param cod 	o código do livro que será buscado no banco de dados
	* @return 		O livro cujo código foi passado por parâmetro. Caso não encontre, retorna null
	* @see Livro
	* @since 1.0
	*/
	public Livro buscarLivro(String cod){
		for(Livro l : this.lerTodosLivros()){
			if(l.getCod().equals(cod)){
				return l;
			}
		}
		
		return null;
	}

/**
	* Método para buscar um objeto da classe Livro do banco de dados através de seu titulo.
	* Caso não encontre, é printado uma mensagem de alerta na tela e retorna-se null.
	* O livro não é deletado do banco de dados.
	*
	* @param titulo 	O titulo do livro que será buscado no banco de dados
	* @return 			O livro cujo titulo foi passado por parâmetro. Caso não encontre, retorna null
	* @see Livro
	* @since 1.0
	*/
	public Livro buscarLivroNome(String titulo){
		for(Livro l : this.lerTodosLivros()){
			if(l.getTitulo().equals(titulo)){
				return l;
			}
		}
		
		return null;
	}

	/**
	* Método para adicionar uma quantidade de livros à quantidade atual de um livro.
	*
	* @param cod 	o codigo do livro
	* @param quant 	a quantidade de livros a serem adicionados
	* @return 		0, caso sucesso. 1, caso falhe
	* @see Livro
	* @since 1.0
	*/
	public int somarQuantLiv(String cod, int quant){
		String str = "";
		int encontrado = 1;

		for(Livro l : this.lerTodosLivros()){
			if(cod.equals(l.getCod())){
				l.setQuantidade(l.getQuantidade() + quant);
				str = str + l.camposEmStr() + endline;
				encontrado = 0;	
			}else{
				str = str + l.camposEmStr() + endline;
			}
		}

		gravarString(str);

		return encontrado;
	}

	/**
	* Método para diminuir uma quantidade de livros à quantidade atual de um livro.
	*
	* @param cod 	o codigo do livro
	* @param quant 	a quantidade de livros a serem diminuidos
	* @return 		0, caso sucesso. 1, caso não há quantidade suficiente em estoque
	* @see Livro
	* @since 1.0
	*/
	public int subtrairQuantLiv(String cod, int quant){
		String str = "";
		for(Livro l : this.lerTodosLivros()){
			if(cod.equals(l.getCod())){
				l.setQuantidade(l.getQuantidade() - quant);
				if(l.getQuantidade() < 0){						//quantidade insuficiente em estoque
					return 1;
				}else{
					str = str + l.camposEmStr() + endline;
				}
			}else{
				str = str + l.camposEmStr() + endline;
			}
		}

		gravarString(str);

		return 0;
	}

	/**
	* Método para ler todos os livros do banco de dados. Eles não são retirados do banco.
	* Pode retornar uma exceção caso ocorra algum erro durante a leitura dos livros no banco de dados.
	*
	* @return 		uma lista com todos os livros (em formato do objeto Livro) do banco de dados
	* @see Livro
	* @since 1.0
	*/
	public List<Livro> lerTodosLivros(){
		List<Livro> livros = new ArrayList<Livro>();

		try{
			BufferedReader br = new BufferedReader(new FileReader(linkBdLivro));
			String linha = br.readLine();
			while(linha != null){
				livros.add(transfStrEmObj(linha));
				linha = br.readLine();
			}
			br.close();

		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Erro na leitura de livros no banco.");
		}

		return livros;
	}

	/**
	* Método auxiliar para gravar uma string no arquivo.
	* Pode retornar uma exceção caso ocorra algum erro durante a escrita.
	*
	* @param str 	a string a ser gravada no arquivo
	* @return 		0, caso sucesso. 1, caso falhe
	* @since 1.0
	*/
	public int gravarString(String str){
		try{	
			BufferedWriter bw = new BufferedWriter(new FileWriter(linkBdLivro));
   			bw.write(str);
   			bw.close();

		}catch(Exception e){
			System.out.println("Erro na escrita do arquivo de livros.");
			return 1;
		}

		return 0;
	}



	public int escreverResenha(Livro livro, String resenha){
		String str = "";
		for(Livro l : this.lerTodosLivros()){
			if(l.getTitulo().equals(livro.getTitulo())){
				str = str + l.camposEmStr() + ";" + resenha + endline ;
			}else{
				str = str + l.camposEmStr() + endline ;
			}
		}
		gravarString(str);
		return 0;
	}

	/**
	* Método para deletar um livro do banco de dados. Precisa do código do livro para conseguir deletar.
	*
	* @param cod 	o codigo do livro a ser deletado
	* @return 		0, caso sucesso
	* @see Livro
	* @see lerTodosLivros
	* @since 1.0
	*/
	public int deletarLivro(String cod){
		String str = "";

		for(Livro l : this.lerTodosLivros()){
			if(!l.getCod().equals(cod)){
				str = str + l.camposEmStr() + endline;
			}			
		}

		gravarString(str);

		return 0;
	}

	/**
	* Método auxiliar para transformar um livro no formato de string para um objeto Livro
	*
	* @param str 	o livro em formato de string
	* @return 		o livro em formato de objeto
	* @see Livro
	* @since 1.0
	*/
	public static Livro transfStrEmObj(String str){
		String[] vetor = str.split(";");
		Livro l = new Livro();
		int i;
		
		l.setCod(vetor[0]);
		l.setTitulo(vetor[1]);
		l.setNomeAutor(vetor[2]);
		l.setDtPublicacao(vetor[3]);
		l.setGenero(vetor[4]);
		l.setQuantidade(Integer.parseInt(vetor[5]));

		for(i = 6; i < vetor.length; i++){
			l.adicionarResenha(vetor[i]);
		}
		
		return l;
	}

}