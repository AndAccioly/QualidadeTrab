
import org.junit.Assert;
import org.junit.Test;

public class TesteUnitario {
	
	
	@Test
	public void negocioLoginTeste(){
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
		
	}
	
	
	@Test
	public void negocioCadastroTeste(){
		String nome, apelido, senha, telefone;
		int r;
		System.out.println("Inicio de testes de Negocio Cadastro...");
		
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
		
		System.out.println("Fim de testes de Negocio Cadastro...");
		
	}
}