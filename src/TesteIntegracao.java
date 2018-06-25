import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TesteIntegracao{

	@Test
	public void integrCadLogin(){
		System.out.println("Iniciando teste de integracao entre Cadastro e Login...");
		String nome, apelido, senha, telefone;
		Pessoa p;
		int r;
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "12-93456789";
		senha = "abcd";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(r,3);
		
		telefone = "12-934567899";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(r,0);
		
		p = NegocioLogin.login(apelido, senha);
		
		Assert.assertNotNull(p);
		
		
		PersistPessoa persistPessoa = new PersistPessoa();
		p = new Pessoa();
		p.setNome("Nome teste");
		persistPessoa.removerPessoa(p);
		System.out.println("Finalizando teste de integracao entre Cadastro e Login...");

	}
	
	@Test
	public void AdicResConsLivro(){
		System.out.println("Iniciando teste de integracao de adição de resenha...");
		PersistLivro persistLivro = new PersistLivro();
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = new Pessoa();
		Livro l = new Livro();
		Estante e = new Estante();
		int r;
		
		persistLivro.gravarLivro(new Livro()); 
		r = NegocioCadastro.validarCadastro(p.getNome(), p.getApelido(), p.getSenha(), p.getTelefone());
		Assert.assertEquals(r,0);
		e = NegocioEstante.colocarNaEstante(e, l, p);
		Assert.assertEquals(l.getTitulo(), "Titulo generico");
		
		r = NegocioEstante.escreverResenha(l, "Livro ruim");
		Assert.assertEquals(r,  0);
		l = NegocioEstante.consultarLivro("Titulo generico");
		Assert.assertEquals(l.getTitulo(), "Titulo generico");
		
		Assert.assertEquals(l.getResenhas().get(0), "Livro ruim");
		
		
		persistPessoa.removerPessoa((new Pessoa()));
		persistLivro.deletarLivro((new Livro()).getCod());
		System.out.println("Finalizando teste de integracao de adição de resenha...");
		
	}
	
	@Test
	public void AdicRemovLivro(){
		System.out.println("Iniciando teste de integracao de adicionar e remover livro...");
		PersistLivro persistLivro = new PersistLivro();
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = new Pessoa();
		Livro l = new Livro();
		Estante e = new Estante();
		int r;
		
		persistLivro.gravarLivro(new Livro()); 
		r = NegocioCadastro.validarCadastro(p.getNome(), p.getApelido(), p.getSenha(), p.getTelefone());
		Assert.assertEquals(r,0);
		e = NegocioEstante.colocarNaEstante(e, l, p);
		Assert.assertEquals(l.getTitulo(), "Titulo generico");
		
		e = NegocioEstante.removerDaEstante(e, l, p);
		Assert.assertNull(e.buscaLivro("Titulo generico"));
		
		persistPessoa.removerPessoa((new Pessoa()));
		persistLivro.deletarLivro((new Livro()).getCod());
		System.out.println("Finalizando teste de integracao de adicionar e remover livro...");
	}
	
	@Test
	public void trocaLivro(){
		System.out.println("Iniciando teste de integracao de trocar livro...");
		PersistLivro persistLivro = new PersistLivro();
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = new Pessoa();
		Livro l = new Livro();
		Estante e = new Estante();
		int r;
		List<Pessoa> pessoas;
		
		persistLivro.gravarLivro(new Livro()); 
		r = NegocioCadastro.validarCadastro(p.getNome(), p.getApelido(), p.getSenha(), p.getTelefone());
		Assert.assertEquals(r,0);
		e = NegocioEstante.colocarNaEstante(e, l, p);
		Assert.assertEquals(l.getTitulo(), "Titulo generico");
		
		r = NegocioEstante.informarTroca("Titulo generico", p);
		Assert.assertEquals(0, r);
		
		pessoas = NegocioEstante.procurarTroca("Titulo generico");
		Assert.assertEquals("Nome generico", pessoas.get(0).getNome());
		
		persistPessoa.removerPessoa((new Pessoa()));
		persistLivro.deletarLivro((new Livro()).getCod());
		System.out.println("Finalizando teste de integracao de trocar livro...");
	}
	
	@Test
	public void verCadastro(){
		System.out.println("Iniciando teste de integracao de visualizacao de cadastro...");
		PersistLivro persistLivro = new PersistLivro();
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = new Pessoa();
		int r;
		
		r = NegocioCadastro.validarCadastro(p.getNome(), p.getApelido(), p.getSenha(), p.getTelefone());
		Assert.assertEquals(r,0);
		r = NegocioCadastro.validarCadastro("Nome teste", "Apelido teste", "abcd", "12-934567899");
		Assert.assertEquals(r,0);
		
		p = NegocioEstante.consultarUsuario("Nome teste");
		Assert.assertEquals(p.camposEmStr(), "Nome teste;Apelido teste;bcde;1;12-934567899;");
		
		persistPessoa.removerPessoa((new Pessoa()));
		p = new Pessoa("Nome teste", "Apelido teste", "abcd", 1, new Estante(),  "12-934567899");
		persistPessoa.removerPessoa(p);
		System.out.println("Finalizando teste de integracao de visualizacao de cadastro...");
	}
	
	@Test
	public void adicConsultLivro(){
		System.out.println("Iniciando teste de integracao de adicionar e consultar livro...");
		PersistLivro persistLivro = new PersistLivro();
		PersistPessoa persistPessoa = new PersistPessoa();
		Livro l = new Livro();
		Pessoa p = new Pessoa();
		Estante e = new Estante();
		int r;
		
		persistLivro.gravarLivro(new Livro()); 
		r = NegocioCadastro.validarCadastro(p.getNome(), p.getApelido(), p.getSenha(), p.getTelefone());
		Assert.assertEquals(r,0);
		e = NegocioEstante.colocarNaEstante(e, l, p);
		Assert.assertEquals(l.getTitulo(), "Titulo generico");
		l = NegocioEstante.consultarLivro("Titulo generico");
		Assert.assertEquals(l.getTitulo(), "Titulo generico");
		
		persistPessoa.removerPessoa((new Pessoa()));
		persistLivro.deletarLivro((new Livro()).getCod());
		
		System.out.println("Finalizando teste de integracao de adicionar e consultar livro...");
	}
}
