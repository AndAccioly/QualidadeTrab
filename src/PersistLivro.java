
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
	private File fLivro;
	private File fPessoa;

	public PersistLivro(){
		fLivro = new File(linkBdLivro);
		fPessoa = new File(linkBdPessoa);
	}
	
	public int gravarLivro(Livro livro){
		String str = livro.camposEmStr();
		System.out.println("Salvando livro - " + str );
		
		try{	
			BufferedWriter bw = new BufferedWriter(new FileWriter(linkBdLivro));
   			bw.write(str);
   			bw.close();

		}catch(Exception e){
			System.out.println("Erro na escrita do arquivo");
		}

		return 0;
	}

	public Livro buscarLivro(){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(fLivro));
			
		}catch(Exception e){
			System.out.println("Erro na busca do livro ao abrir o arquivo");
		}

		return null;
	}

	public List<Livro> lerTodosLivros(){
		List<Livro> livros = new ArrayList<Livro>();
		System.out.println("Lendo todos os livros");

		try{
			BufferedReader br = new BufferedReader(new FileReader(linkBdLivro));
			String linha = br.readLine();
			while(linha != null){
				livros.add(Livro.transfStrEmObj(linha));
			}

		}catch (Exception e){
			System.out.println("Erro na leitura de todos os livros do banco.");
		}

		return livros;
	}

	public int gravarTodosLivros(List<Livro> livros){
		return 0;
	}

	public int deletarLivro(){
		return 0;
	}

}