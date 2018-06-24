
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

/**
* Classe com todos métodos para trocas de informações com o banco de dados referentes à classe Pessoa.
*
* @author Andre Accioly
* @author Tiago Kfouri
* @version %I%, %G%
* @since 1.0
* @see Pessoa
*/
class PersistPessoa{

	private String linkBdPessoa = "bd_pessoa.xml";
	String endline = "\n";
	private File fPessoa;

	/**
	* Construtor da classe PeersistPessoa. Inicia o ponteiro para o arquivo xml referente à pessoa, que será
	* onde serão armazenados os dados.
	* 
	* @see Pessoa
	* @see PersistPessoa
	* @since 			1.0
	*/
	public PersistPessoa(){
		fPessoa = new File(linkBdPessoa);
	}

	/**
	* Método para salvar um objeto da classe Pessoa no banco de dados
	*
	* @param pessoa 	um objeto Pessoa, que será salvo no banco de dados
	* @return 			0, caso sucesso
	* @see Pessoa
	* @since 			1.0
	*/	
	public int gravarPessoa(Pessoa pessoa){
		String str = pessoa.camposEmStr() + endline;

		for(Pessoa p : this.lerTodasPessoas()){
			str = str + p.camposEmStr() + endline ;
		}
		
		gravarString(str);

		return 0;
	}

	/**
	* Método para ler todas as pessoa do banco de dados. Elas não são retirados do banco.
	* Pode retornar uma exceção caso ocorra algum erro durante a leitura das pessoas no banco de dados.
	*
	* @return 		uma lista com todas as pessoas (em formato do objeto Pessoa) do banco de dados
	* @see Pessoa
	* @since 		1.0
	*/
	public List<Pessoa> lerTodasPessoas(){
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		try{
			BufferedReader br = new BufferedReader(new FileReader(linkBdPessoa));
			String linha = br.readLine();
			while(linha != null){
				pessoas.add(transfStrEmObj(linha));
				linha = br.readLine();
			}
			br.close();

		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Erro na leitura de pessoas do banco.");
			return null;
		}

		return pessoas;
	}

	public int associarLivro(Livro l, Pessoa pessoa){
		String str = "";
		for(Pessoa p : this.lerTodasPessoas()){
			if(p.getNome().equals(pessoa.getNome())){
				str = str + p.camposEmStr() + l.getCod() + endline;
			}else{
				str = str + p.camposEmStr() + endline;
			}
		}

		gravarString(str);

		return 0;
	}

	public int desassociarLivro(String titulo, Pessoa pessoa){
		String str = "";
		PersistLivro persistLivro = new PersistLivro();
		Livro livro = persistLivro.buscarLivroNome(titulo);

		for(Pessoa p : this.lerTodasPessoas()){
			if(p.getNome().equals(pessoa.getNome())){
				p.getEstante().removeLivro(livro);
			}
			str = str + p.camposEmStr() + endline;
			
		}

		gravarString(str);

		return 0;
	}

	public int informarTroca(String titulo, Pessoa pessoa){
		String str = "";
		PersistLivro persistLivro = new PersistLivro();
		Livro livro = persistLivro.buscarLivroNome(titulo);
		for(Pessoa p : this.lerTodasPessoas()){
			if(p.getNome().equals(pessoa.getNome())){
				Estante e = p.getEstante();
				int result = e.removeLivro(livro);
				if(result == 0){
					livro.setCod("T" + livro.getCod());
					e.adicionaLivro(livro);
			}

			str = str + p.camposEmStr() + endline;
			}
		}
		gravarString(str);

		return 0;
	}

	public List<Pessoa> procurarTroca(String titulo){
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for(Pessoa p : this.lerTodasPessoas()){
			Estante e = p.getEstante();
			Livro l = e.buscaLivro(titulo);
			if(l != null){
				if(l.getCod().contains("T")){
					pessoas.add(p);
				}
			}
		}

		return pessoas;
	}


	public Pessoa buscarPesPorNome(String nome){
		for(Pessoa p : this.lerTodasPessoas()){
			if(p.getNome().equals(nome)){
				return p;
			}
		}
		
		return null;
	}

	public Pessoa buscarPesPorApel(String apelido){
		for(Pessoa p : this.lerTodasPessoas()){
			if(p.getApelido().equals(apelido)){
				return p;
			}
		}
		
		return null;
	}

	/**
	* Método para deletar uma pessoa do banco de dados. Precisa do apelido da pessoa para conseguir deletar.
	*
	* @param apelido 		o apelido da pessoa a ser deletado
	* @return 				0, caso sucesso
	* @see Pessoa
	* @see lerTodasPessoas
	* @since 				1.0
	*/
	public int deletarPessoa(String apelido){
		String str = "";

		for(Pessoa p : this.lerTodasPessoas()){
			if(!p.getApelido().equals(apelido)){
				str = str + p.camposEmStr() + endline;
			}			
		}

		gravarString(str);

		return 0;
	}

	/**
	* Método auxiliar para gravar uma string no arquivo.
	* Pode retornar uma exceção caso ocorra algum erro durante a escrita.
	*
	* @param str 	a string a ser gravada no arquivo
	* @return 		0, caso sucesso. 1, caso falhe
	* @since 		1.0
	*/
	public int gravarString(String str){
		try{	
			BufferedWriter bw = new BufferedWriter(new FileWriter(linkBdPessoa));
	 		bw.write(str);
	 		bw.close();

		}catch(Exception e){
			System.out.println("Erro na escrita no arquivo de pessoas.");
			return 1;
		}
		return 0;
	}

	/**
	* Método auxiliar para transformar uma pessoa no formato de string para um objeto Pessoa
	*
	* @param str 	a pessoa em formato de string
	* @return 		a pessoa em formato de objeto
	* @see Pessoa
	* @since 		1.0
	*/
	public static Pessoa transfStrEmObj(String str){
		String[] vetor = str.split(";");
		Pessoa p = new Pessoa();
		Estante e = new Estante();
		PersistLivro persistLivro = new PersistLivro();
		int i;
		
		p.setNome(vetor[0]);
		p.setApelido(vetor[1]);
		p.setSenha(vetor[2]);
		p.setTipo(Integer.parseInt(vetor[3]));
		p.setTelefone(vetor[4]);
		
		for(i = 5; i < vetor.length; i++){
			Livro l;
			String codigo = vetor[i];
			if(codigo.contains("T")){
				codigo = codigo.substring(1, 7);
			}

			l = persistLivro.buscarLivro(codigo);
			if(vetor[i].contains("T")){
				l.setCod("T" + l.getCod());
			}
			if(l != null){
				e.adicionaLivro(l);
			}
		}

		p.setEstante(e);
		
		return p;
	}

}