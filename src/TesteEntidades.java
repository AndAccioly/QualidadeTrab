import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TesteEntidades {
	@Test
	public void testeEstante(){
		System.out.println("Inicio de testes da entidade Estante...");
		Estante e = new Estante();
		Assert.assertNotNull(e.getLivros());
		int r;
		String s;
		
		Livro l = new Livro();
		List<Livro> livros = new ArrayList<Livro>();
		livros.add(l);
		e = new Estante(livros);
		Assert.assertEquals("Titulo generico", e.getLivros().get(0).getTitulo());
		
		l = new Livro("Titulo", "nome autor", "dtpublicacao", "cod", "genero", 0, new ArrayList<String>());
		e.adicionaLivro(l);
		Assert.assertEquals("Titulo", e.getLivros().get(1).getTitulo());
		
		l = new Livro("Titulo2", "nome autor2", "dtpublicacao2", "cod2", "genero2", 20, new ArrayList<String>());
		livros.add(l);
		e.setLivros(livros);
		Assert.assertEquals("Titulo2", e.getLivros().get(2).getTitulo());
		
		l = e.buscaLivro("Titulo generico");
		Assert.assertEquals("000000", l.getCod());
		
		l = e.buscaLivro("Titulo inexistente");
		Assert.assertNull(l);
		
		l = e.buscaLivro("Titulo generico");
		r = e.removeLivro(l);
		Assert.assertEquals(0, r);
		
		r = e.removeLivro(l);
		Assert.assertEquals(1, r);
		
		e.adicionaLivro(l);
		
		s = e.camposEmStr();
		Assert.assertEquals("cod;Titulo;nome autor;dtpublicacao;genero;0cod2;Titulo2;nome autor2;dtpublicacao2;genero2;20000000;Titulo generico;Autor generico;00/00/0000;Genero generico;0", s);
		
		s = e.codEmStr();
		Assert.assertEquals("cod;cod2;000000;", s);
		System.out.println("Fim de testes da entidade Estante...");
		
	}
	
	@Test
	public void testeLivro(){
		System.out.println("Inicio de testes da entidade Livro...");
		String s;
		Livro l = new Livro();
		Assert.assertEquals(l.getTitulo(), "Titulo generico");
		
		l = new Livro("Titulo", "nome autor", "dtpublicacao", "cod", "genero", 0, null);
		Assert.assertEquals(l.getTitulo(), "Titulo");
		Assert.assertEquals(l.getNomeAutor(), "nome autor");
		Assert.assertEquals(l.getDtPublicacao(), "dtpublicacao");
		Assert.assertEquals(l.getCod(), "cod");
		Assert.assertEquals(l.getGenero(), "genero");
		Assert.assertEquals(l.getQuantidade(), 0);
		Assert.assertEquals(l.getResenhas(), null);
		
		l.setTitulo("Titulo2");
		Assert.assertEquals(l.getTitulo(), "Titulo2");
		
		l.setNomeAutor("Nome2");
		Assert.assertEquals(l.getNomeAutor(), "Nome2");
		
		l.setDtPublicacao("dt2");
		Assert.assertEquals(l.getDtPublicacao(), "dt2");
		
		l.setCod("cod2");
		Assert.assertEquals(l.getCod(), "cod2");
		
		l.setGenero("genero2");
		Assert.assertEquals(l.getGenero(), "genero2");
		
		l.setQuantidade(1);
		Assert.assertEquals(l.getQuantidade(), 1);
		
		List<String> resenhas = new ArrayList<String>();
		resenhas.add("Resenha1");
		l.setResenhas(resenhas);
		List<String> teste = l.getResenhas();
		Assert.assertEquals(l.getResenhas().get(0), "Resenha1");
		
		s = l.camposEmStr();
		Assert.assertEquals(s, "cod2;Titulo2;Nome2;dt2;genero2;1;Resenha1");
		System.out.println("Fim de testes da entidade Livro...");
	}
	
	@Test
	public void testePessoa(){
		System.out.println("Inicio de testes da entidade Pessoa...");
		Pessoa p = new Pessoa();
		Assert.assertEquals(p.getNome(), "Nome generico");
		Estante e = new Estante();
		String s;
		
		p = new Pessoa("nome", "apelido", "senha", 1, e, "telefone");
		Assert.assertEquals(p.getNome(), "nome");
		Assert.assertEquals(p.getApelido(), "apelido");
		Assert.assertEquals(p.getSenha(), "senha");
		Assert.assertEquals(p.getTipo(), 1);
		Assert.assertNotNull(p.getEstante());
		Assert.assertEquals(p.getTelefone(), "telefone");
	
		p.setNome("nome2");
		Assert.assertEquals(p.getNome(), "nome2");
		
		p.setApelido("apelido2");
		Assert.assertEquals(p.getApelido(), "apelido2");
		
		p.setSenha("senha2");
		Assert.assertEquals(p.getSenha(), "senha2");
		
		p.setTelefone("telefone2");
		Assert.assertEquals(p.getTelefone(), "telefone2");
		
		p.setTipo(0);
		Assert.assertEquals(p.getTipo(), 0);
		
		Livro l = new Livro();
		e.adicionaLivro(l);
		p.setEstante(e);
		Assert.assertEquals(1, p.getEstante().getLivros().size());
		
		s = p.camposEmStr();
		Assert.assertEquals("nome2;apelido2;senha2;0;telefone2;000000;", s);
		System.out.println("Fim de testes da entidade Pessoa...");
		
		
	}
}
