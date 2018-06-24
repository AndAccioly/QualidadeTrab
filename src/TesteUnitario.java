import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TesteUnitario {
	
	@Test
	public void negEstanteTeste(){
		System.out.println("Inicio de testes de NegocioEstante...");
		Livro l, l2;
		PersistLivro persistLivro = new PersistLivro();
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p, p2;
		Estante e;
		int r;
		List<Pessoa> pessoas;
		String titulo, autor, dt, genero;
		
		//cria o usuario e livro para teste
		NegocioCadastro.validarCadastro("Nome teste", "Apelido teste", "abcd", "12-934567899");
		persistLivro.gravarLivro(new Livro()); 
		
		p = persistPessoa.buscarPesPorApel("Apelido teste");
		e = new Estante();
		
		//testa a criacao de livro, se o mesmo e salvo no banco
		titulo = "titulo teste";
		autor = "nome autor";
		dt = "12/12/2000";
		genero = "genero";
		l = NegocioEstante.criarLivro(titulo, autor, dt, genero);
		Assert.assertEquals(l.getTitulo(), "titulo teste");
		
		persistLivro.deletarLivro(l.getCod());
		
		//testa se o livro e colocado na estante
		e = NegocioEstante.colocarNaEstante(e, l, p);
		Assert.assertEquals(e.getLivros().get(0).getTitulo(), "titulo teste");
		
		//testa a busca de livro
		l = NegocioEstante.buscarLivro((new Livro()).getTitulo());
		Assert.assertEquals(l.getTitulo(), "Titulo generico");
		
		//testa a busca de livro com livro inexistente no banco
		l2 = NegocioEstante.buscarLivro("Titulo inexistente");
		Assert.assertNull(l2);
		
		//testa a remocao de um livro da estante
		e = NegocioEstante.removerDaEstante(e, l, p);
		Assert.assertNull(e.buscaLivro("Titulo generico"));
		
		//testa a consulta de livro
		l = NegocioEstante.consultarLivro("Titulo generico");
		Assert.assertEquals(l.camposEmStr(), "000000;Titulo generico;Autor generico;00/00/0000;Genero generico;0");
		
		//testa a consulta de livro com livro nao encontrado
		l2 = NegocioEstante.consultarLivro("Livro inexistente");
		Assert.assertNull(l2);
		
		//testa a consulta de usuario
		p = NegocioEstante.consultarUsuario("Nome teste");
		Assert.assertEquals(p.camposEmStr(), "Nome teste;Apelido teste;bcde;1;12-934567899;");
		
		//testa a consulta por usuario com usuario nao existente
		p2 = NegocioEstante.consultarUsuario("Nome inexistente");
		Assert.assertNull(p2);
		
		//testa a escrita de resenha
		l = new Livro();
		e = NegocioEstante.colocarNaEstante(e, l, p);
		r = NegocioEstante.escreverResenha(l, "Livro ruim");
		l = NegocioEstante.consultarLivro("Titulo generico");
		Assert.assertEquals(l.getResenhas().get(0), "Livro ruim");
		
		//testa a informacao de troca
		r = NegocioEstante.informarTroca("Titulo generico", p);
		Assert.assertEquals(0, r);
		
		//testa a procura por pessoas que tem o livro para trocar
		pessoas = NegocioEstante.procurarTroca("Titulo generico");
		Assert.assertEquals("Nome teste", pessoas.get(0).getNome());
		
		//deleta o que foi criado nos testes
		p = new Pessoa();
		p.setNome("Nome teste");
		persistPessoa.removerPessoa(p);
		persistLivro.deletarLivro((new Livro()).getCod());
		System.out.println("Fim de testes de NegocioEstante...");
		
	}
	
	@Test
	public void negLoginTeste(){
		System.out.println("Inicio de testes de NegocioLogin...");
		String apelido, senha, nome, telefone;
		Pessoa p;
		
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "12-934567899";
		senha = "abcd";
		NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		
		//teste que deve funcionar
		p = NegocioLogin.login(apelido, senha);
		Assert.assertEquals(p.getNome(), "Nome teste");
		
		//teste que deve falhar por senha errada
		senha = "xyze";
		p = NegocioLogin.login(apelido, senha);
		Assert.assertNull(p);
		
		PersistPessoa persistPessoa = new PersistPessoa();
		p = new Pessoa();
		p.setNome("Nome teste");
		persistPessoa.removerPessoa(p);
		
		//teste que deve falhar por login ou senha errada
		p = NegocioLogin.login(apelido, senha);
		Assert.assertNull(p);
		System.out.println("Fim de testes de NegocioLogin...");
		
	}
	
	
	@Test
	public void negCadastroTeste(){
		String nome, apelido, senha, telefone;
		int r;
		System.out.println("Inicio de testes de NegocioCadastro...");
		
		//teste que deve funcionar
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "12-934567899";
		senha = "abcd";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(0, r);
		
		//teste que deve falhar por nome ja existente
		nome = "Nome teste";
		apelido = "";
		senha = "";
		telefone = "";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(1, r);
		
		//teste que deve falhar por apelido ja existente
		nome = "Outro nome";
		apelido = "Apelido teste";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(2, r);
		
		PersistPessoa persistPessoa = new PersistPessoa();
		Pessoa p = new Pessoa();
		p.setNome("Nome teste");
		persistPessoa.removerPessoa(p);
		
		//teste que deve falhar por telefone sem hifen
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "1234567899";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(3, r);
		
		//teste que deve falhar por telefone com partes de tamanho errado
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "1-34567899";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(3, r);
		
		//teste que deve falhar por telefone com partes de tamanho errado
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "12-4567899";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(3, r);
		
		//teste que deve falhar por telefone com digito nao numerico
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "12-93456a899";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(3, r);
		
		//teste que deve falhar por telefone com digito nao numerico
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "1a-934567899";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(3, r);
		
		//teste que deve falhar por senha com numero
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "12-934567899";
		senha = "12a4";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(4, r);
		
		//teste que deve falhar por senha longa
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "12-934567899";
		senha = "abcde";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(4, r);
		
		//teste que deve falhar por senha com digito repetido
		nome = "Nome teste";
		apelido = "Apelido teste";
		telefone = "12-934567899";
		senha = "abca";
		r = NegocioCadastro.validarCadastro(nome, apelido, senha, telefone);
		Assert.assertEquals(4, r);
		
		System.out.println("Fim de testes de NegocioCadastro...");
		
	}
}
