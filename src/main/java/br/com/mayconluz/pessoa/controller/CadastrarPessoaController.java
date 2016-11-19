package br.com.mayconluz.pessoa.controller;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mayconluz.model.PessoaModel;
import br.com.mayconluz.repository.PessoaRepository;
import br.com.mayconluz.usuario.controller.UsuarioController;
import br.com.mayconluz.uteis.Uteis;

@Named(value="cadastrarPessoaController") // annotation que muda a classe para um bean gerenciado pelo CDI
@RequestScoped // especifica que o bean é um escopo de solicitaçao.

/**
 *
 * @author Maycon Luz @Date 19 de nov de 2016 às 17:50:09
 * Classe que controla o cadastro de Pessoa
 */
public class CadastrarPessoaController {

	@Inject // realiza a injecao de dependencia no bean
	PessoaModel pessoaModel;

	@Inject// realiza a injecao de dependencia no bean
	UsuarioController usuarioController;

	@Inject// realiza a injecao de dependencia no bean
	PessoaRepository pessoaRepository;


	/*
	 * retorna um objeto do tipo PessoaModel
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/*
	 * é passado por parametro o valor do objeto da classe
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 *Realiza o salvamento de um novo registro via input
	 */
	public void SalvarNovaPessoa(){

		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		//informa que o cadastro foi via input
		pessoaModel.setOrigemCadastro("I");

		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);

		this.pessoaModel = null;

		Uteis.MensagemInfo("Registro cadastrado com sucesso"); // apresenta a mensagem na tela caso a operaçao realizada com sucesso.

	}

}