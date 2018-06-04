//Tipo = adm (0) ou cliente (1)
import java.util.List;

class Pessoa{
	private int tipo;
	private String nome;
	private String apelido;
	private String senha;
	private Estante estante;
	private String telefone;

	public Pessoa(){
		this.nome = "Nome generico";
		this.tipo = 1;
		this.senha = "isto nao e uma senha";
		this.apelido = "Cliente generico";
		this.estante = new Estante();
		this.telefone = "(00) - 000000000";
	}

	public Pessoa(String nome, String apelido, String senha, int tipo, Estante estante, String telefone){
		this.nome = nome;
		this.apelido = apelido;
		this.senha = senha;
		this.tipo = tipo;
		this.estante = estante;
		this.telefone = telefone;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getNome(){
		return this.nome;
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

	public void setTelefone(String telefone){
		this.telefone = telefone;
	}

	public String getTelefone(){
		return this.telefone;
	}

	public String camposEmStr(){
		String str = nome + ";" + apelido + ";" + senha + ";" +  tipo + ";" + telefone + ";" + estante.codEmStr();
		return str;
	}

}