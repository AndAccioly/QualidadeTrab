//Tipo = adm (0) ou cliente (1)
import java.util.List;

class Pessoa{
	private int tipo;
	private String apelido;
	private String senha;
	private Estante estante;

	public Pessoa(){
		this.tipo = 1;
		this.senha = "000000";
		this.apelido = "Cliente generico";
		this.estante = new Estante();
	}

	public Pessoa(String apelido, String senha, int tipo, Estante estante){
		this.apelido = apelido;
		this.senha = senha;
		this.tipo = tipo;
		this.estante = estante;
	}

	public String getSenha(){
		return this.senha;
	}

	public void setSenha(String senha){
		this.senha = senha;
	}

	public String getApelido(){
		return this.apelido;
	}

	public void setApelido(String apelido){
		this.apelido = apelido;
	}

	public int getTipo(){
		return this.tipo;
	}

	public void setTipo(int tipo){
		this.tipo = tipo;
	}

	public Estante getEstante(){
		return this.estante;
	}

	public void setEstante(Estante estante){
		this.estante = estante;
	}

	public String camposEmStr(){
		String str = apelido + ";" + senha + ";" +  tipo + ";" estante.codEmStr();
		return str;
	}

}