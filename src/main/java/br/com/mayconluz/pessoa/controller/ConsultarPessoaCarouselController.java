package br.com.mayconluz.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mayconluz.model.PessoaModel;
import br.com.mayconluz.repository.PessoaRepository;

/**
 *
 * @author Maycon Luz @Date 22 de nov de 2016 às 19:34:05
 * Classe implementa serializable
 * Classe de consulta com carusel primefaces
 */

@Named(value="consultarPessoaCarouselController")
@ViewScoped
public class ConsultarPessoaCarouselController implements Serializable {


	private static final long serialVersionUID = 1L;// versao da classe

	@Inject transient
	private PessoaRepository pessoaRepository;

	@Produces
	private List<PessoaModel> pessoas;


	public List<PessoaModel> getPessoas() { // retorna uma lista de pessoas
		return pessoas;
	}

	@PostConstruct
	private void init(){

		this.pessoas = pessoaRepository.GetPessoas(); // realiza a solicitação ao metodo pessoaRepository para recuperar o valor do objeto armazenados em GePessoas.
	}




}
