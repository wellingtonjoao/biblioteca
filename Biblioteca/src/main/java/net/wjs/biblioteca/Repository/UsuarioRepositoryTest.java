package net.wjs.biblioteca.Repository;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import net.wjs.biblioteca.Entity.Pessoa;

public class UsuarioRepositoryTest {
	
@Autowired
private PessoaRepository pessoaRepositorio;
@Before

public void setUp() throws Exception{
	Pessoa pessoa1=new Pessoa(1,"Wellington");
	assertNull(pessoa1.getId());
}
}
