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
 * @author Maycon Luz @Date 20 de nov de 2016 às 15:50:53
 * esta classe tem o objetivo de consultar e editar o cadastro das pessoas persistidas no banco
 */

@Named(value="consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	private PessoaModel pessoaModel;

	@Produces
	private List<PessoaModel> pessoas;

	@Inject transient
	private PessoaRepository pessoaRepository;

	/*
	 * Retorna uma lista de pessoas
	 * @return retorna uma lista do tipo PessoaModel
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/*
	 * é difinido uma lista de pessoas
	 * @param uma lista de pessoas
	 */
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}
	/*
	 * Retorna o modelo da Pessoa
	 * @return retorna o modelo da pessoa do Tipo pessoaModel
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/*
	 * é definido o modelo da pessoa
	 * @param usuarioEntity modelo da pessoa
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/*
	 * na inicializaçao da classe este metodo é chamado para carregar as pessoas persistendas no banco
	 */
	@PostConstruct
	public void init(){

		// retorna as pessoas cadastradas no banco de dados.
		this.pessoas = pessoaRepository.GetPessoas();
	}

	/*
	 * CARREGA INFORMAÇÕES DE UMA PESSOA PARA SER EDITADA
	 * @param pessoaModel
	 */
	public void Editar(PessoaModel pessoaModel){

		/*PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F)*/
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));

		this.pessoaModel = pessoaModel;

	}

	/*
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro(){

		this.pessoaRepository.AlterarRegistro(this.pessoaModel);


		/*RECARREGA OS REGISTROS*/
		this.init();
	}


}
