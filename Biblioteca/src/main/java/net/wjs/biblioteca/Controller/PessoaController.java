package net.wjs.biblioteca.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wjs.biblioteca.Entity.Pessoa;
import net.wjs.biblioteca.Repository.PessoaRepository;


//A anotação @RestController permite definir um controller com características REST
@RestController
public class PessoaController {

	//A anotação @Autowired delega ao Spring Boot a inicialização do objeto
	@Autowired
	private PessoaRepository pessoaRepository;
	
	//A anotação @RequestMapping permite definir uma rota. Caso não seja informado o método HTTP da rota, ela será definida para todos os métodos
	@RequestMapping(value="/pessoa", method = RequestMethod.GET)
	public List<Pessoa> Get(){
		return pessoaRepository.findAll();
	}
	
	//A anotação @PathVariable indica que o valor da variável virá de uma informação da rota
	@RequestMapping(value="/pessoa/{id}",method = RequestMethod.GET)
	public ResponseEntity<Pessoa> GetById(@PathVariable(value="id") long id)
	{Optional<Pessoa> pessoa=pessoaRepository.findById(id);
	if (pessoa.isPresent())
		return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
	else
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	//A anotação @RequestBody indica que o valor do objeto virá do corpo da requisição
	//E a anotação @Valid indica que os dados recebidos devem ser validados
	@RequestMapping(value="/pessoa", method = RequestMethod.POST)
	public Pessoa Post(@Valid @RequestBody Pessoa pessoa)
	{
		return pessoaRepository.save(pessoa);
	}
	@RequestMapping(value="/pessoa/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Pessoa> PUT(@PathVariable(value="id") long id, @RequestBody Pessoa newPessoa)
	{
		Optional<Pessoa> oldPessoa=pessoaRepository.findById(id);
		if(oldPessoa.isPresent()) {
			Pessoa pessoa=oldPessoa.get();
			pessoa.setNome(newPessoa.getNome());
			pessoaRepository.save(pessoa);
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@RequestMapping(value="/pessoa/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value="id") long id)
	{
		Optional<Pessoa>pessoa=pessoaRepository.findById(id);
		if(pessoa.isPresent()) {
			pessoaRepository.delete(pessoa.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
