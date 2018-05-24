import java.util.List;
import java.util.ArrayList;

class Estante{
	private List<Livro> livros;

	public Estante(){
		this.livros = new ArrayList<Livro>();
	}

	public Estante(List<Livro> livros){
		this.livros = livros;
	}

	public List<Livro> getLivros(){
		return this.livros;
	}

	public void setLivros(List<Livro> livros){
		this.livros = livros;
	}

	public int adicionaLivro(Livro livro){
		this.livros.add(livro);
		return 0;
	}

	public int removeLivro(Livro livro){
		return 0;
	}

	public String camposEmStr(){
		String str = "";
		for(Livro l : livros){
			str += l.camposEmStr();
		}

		return str;
	}

	public String codEmStr(){
		String str = "";
		for(Livro l : livros){
			str += l.getCod() + ";";
		}

		return str;
	}

}