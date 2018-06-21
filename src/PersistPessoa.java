
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

class PersistPessoa{
	private String linkBdPessoa = "bd_pessoa.xml";
	String endline = "\n";
	private File fPessoa;

	public PersistPessoa(){
		fPessoa = new File(linkBdPessoa);
	}

	public int gravarPessoa(Pessoa pessoa){
		String str = pessoa.camposEmStr() + endline;
		System.out.println("Salvando pessoa - " + str );

		for(Pessoa p : this.lerTodasPessoas()){
			str = str + p.camposEmStr() + endline ;
		}
		
		gravarString(str);

		return 0;
	}

	public List<Pessoa> lerTodasPessoas(){
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		System.out.println("\nLendo todas as pessoas");

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
			System.out.println("Erro na leitura de todas as pessoas do banco.");
			return null;
		}

		for(Pessoa p : pessoas){
			System.out.println(p.camposEmStr());
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

	public int deletarPessoa(String apelido){
		String str = "";

		for(Pessoa p : this.lerTodasPessoas()){
			if(!p.getApelido().equals(apelido)){
				str = str + p.camposEmStr() + endline;
			}else{
				System.out.println("Pessoa deletada" + p.camposEmStr());
			}
			
		}

		gravarString(str);

		return 0;
	}

	public int gravarString(String str){
		try{	
			BufferedWriter bw = new BufferedWriter(new FileWriter(linkBdPessoa));
	 		bw.write(str);
	 		bw.close();

		}catch(Exception e){
			System.out.println("Erro na escrita do arquivo");
			return 1;
		}
		return 0;
	}

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
			l = persistLivro.buscarLivro(vetor[i]);
			if(l != null){
				e.adicionaLivro(l);
			}
		}

		p.setEstante(e);
		
		return p;
	}

}