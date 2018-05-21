//Acerca de cada livro, serão armazenados os seguintes dados: título, nome do autor, data de publicação, código e gênero literário


class Livro{
	private String titulo;
	private String nomeAutor;
	private String dtPublicacao;
	private String cod;
	private String genero;
	private int quantidade;

	public Livro(){
		this.titulo = "Titulo generico";
		this.nomeAutor = "Nome autor generico";
		this.dtPublicacao = "00/00/0000";
		this.cod = "000000";
		this.genero = "Genero generico";
		this.quantidade = 0;

	}

	public Livro(String titulo, String nomeAutor, String dtPublicacao, String cod, String genero, int quantidade){
		this.titulo = titulo;
		this.nomeAutor = nomeAutor;
		this.dtPublicacao = dtPublicacao;
		this.cod = cod;
		this.genero = genero;
		this.quantidade = quantidade;
	}

	public String getTitulo(){
		return this.titulo;
	}

	public void setTitulo(String titulo){
		this.titulo = titulo;
	}

	public String getNomeAutor(){
		return this.nomeAutor;
	}

	public void setNomeAutor(String nomeAutor){
		this.nomeAutor = nomeAutor;
	}

	public String getDtPublicacao(){
		return this.dtPublicacao;
	}

	public void setDtPublicacao(String dtPublicacao){
		this.dtPublicacao = dtPublicacao;
	}

	public String getCod(){
		return this.cod;
	}

	public void setCod(String cod){
		this.cod = cod;
	}

	public String getGenero(){
		return this.genero;
	}

	public void setGenero(String genero){
		this.genero = genero;
	}

	public int getQuantidade(){
		return this.quantidade;
	}

	public void setQuantidade(int quantidade){
		this.quantidade = quantidade;
	}

	public String camposEmStr(){
		String str = cod + ";" + titulo + ";" + nomeAutor + ";" + dtPublicacao + ";" + genero + ";" + quantidade;
		return str;
	}

}