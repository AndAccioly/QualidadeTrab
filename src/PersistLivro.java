
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

class PersistLivro{

//o usuário deverá informar apelido e senha
// Cada usuário terá uma estante virtual onde poderá armazenar até 10 (dez) exemplares de livros
	private String linkBdLivro = "bd_livro.xml";
	private String linkBdPessoa = "bd_pessoa.xml";
	String endline = "\n";
	private File fLivro;
	private File fPessoa;

	public PersistLivro(){
		fLivro = new File(linkBdLivro);
		fPessoa = new File(linkBdPessoa);
	}
	
	public int gravarLivro(Livro livro){
		String str = livro.camposEmStr();
		System.out.println("Salvando livro - " + str );

		for(Livro l : this.lerTodosLivros()){
			// if(livro.getCod().equals(l.getCod())){
			// 	System.out.println("Livro ja estava adicionado");
			// 	break;
			// }
			str = str + endline + l.camposEmStr();
		}
		
		gravarString(str);

		return 0;
	}

	public Livro buscarLivro(String cod){
		for(Livro l : this.lerTodosLivros()){
			if(l.getCod().equals(cod)){
				return l;
			}
		}
		
		System.out.println("Livro não encontrado.");
		return null;
	}

	public int adicionarQuantidadeLivro(String cod, int quant){
		String str = "";
		int encontrado = 0;
		System.out.println("Adicionando quantidade livro");

		for(Livro l : this.lerTodosLivros()){
			System.out.println(l.getCod());
			if(cod.equals(l.getCod())){
				l.setQuantidade(l.getQuantidade() + quant);
				str = str + l.camposEmStr() + endline;
				encontrado = 1;	
			}else{
				str = str + l.camposEmStr() + endline;
			}
		}

		gravarString(str);

		return encontrado;
	}

	public List<Livro> lerTodosLivros(){
		List<Livro> livros = new ArrayList<Livro>();
		System.out.println("\nLendo todos os livros");

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
			System.out.println("Erro na leitura de todos os livros do banco.");
		}

		for(Livro l : livros){
			System.out.println(l.camposEmStr());
		}

		return livros;
	}

	public int gravarString(String str){
		try{	
			BufferedWriter bw = new BufferedWriter(new FileWriter(linkBdLivro));
   			bw.write(str);
   			bw.close();

		}catch(Exception e){
			System.out.println("Erro na escrita do arquivo");
			return 0;
		}

		return 1;
	}

	public int deletarLivro(){
		return 0;
	}

	public static Livro transfStrEmObj(String str){
		String[] vetor = str.split(";");
		Livro l = new Livro();
		
		l.setCod(vetor[0]);
		l.setTitulo(vetor[1]);
		l.setNomeAutor(vetor[2]);
		l.setDtPublicacao(vetor[3]);
		l.setGenero(vetor[4]);
		l.setQuantidade(Integer.parseInt(vetor[5]));
		
		return l;
	}

}