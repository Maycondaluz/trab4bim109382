package br.com.mayconluz.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.mayconluz.model.UsuarioModel;
import br.com.mayconluz.repository.UsuarioRepository;
import br.com.mayconluz.repository.entity.UsuarioEntity;
import br.com.mayconluz.uteis.Uteis;



@Named(value="usuarioController") // annotation que diz que � um bean gerenciavel
@SessionScoped // annotation que informa que o escopo da sessao � um bean gerenciavel

/**
 *
 * @author Maycon Luz @Date 19 de nov de 2016 �s 15:31:39
 *  Classe serializada que controla a injecao de dependencias dos objetos da classe
 */

public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L; // versao da classe

	@Inject // annotation para inje�ao de beans
	private UsuarioModel usuarioModel; // instancia usuarioModel do tipo UsuarioModel

	@Inject // annotation para inje�ao de beans
	private UsuarioRepository usuarioRepository; // instancia usuarioRepository do tipo UsuarioRepository

	@Inject // annotation para injecao de beans
	private UsuarioEntity usuarioEntity; // instancia usuarioEntity do tipo UsuarioEntity

	/*
	 * Retorna um objeto do tipo usuarioModel
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	/*
	 * � passado por parametro o valor do objeto da classe
	 */

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	/*
	 *retorna o usuario na autentica�ao
	 */
	public UsuarioModel GetUsuarioSession(){

		FacesContext facesContext = FacesContext.getCurrentInstance(); // instancia do FacesContext

		return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado"); // neste momento � feita a autentica��o
	}

	/*
	 * finaliza a sess�o do usu�rio e redireciona para a p�gina de login
	 */
	public String Logout(){

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();// efetua a valida��o da sessao

		return "/index.xhtml?faces-redirect=true"; // apos a valida�ao estiver correta � retornado a pagina index.xhtml
	}
	/*
	 *  metodo que realiza a autentica�ao caso o usuario seja v�lido, tambem retorna a pagina do sistema
	 *  caso os requisitos sejam atendidos, caso contrario sera retornado null.
	 */
	public String EfetuarLogin(){

		if(StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())){

			Uteis.Mensagem("Favor informar o login!"); // faz a solicita�ao para o metodo que mostra a mensagem informada
			return null;// retorna nulo
		}
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){

			Uteis.Mensagem("Favor informara senha!");// faz a solicita�ao para o metodo que mostra a mensagem informada
			return null; // retorna nulo
		}
		else{

			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel); // apos fazder a verifica��o do usuario e certificar que o mesmo � valido � retornado os dados do  usuario

			if(usuarioEntity!= null){ // compara se usuarioEntity � diferente de null

				usuarioModel.setSenha(null); // atribui o valor null para senha
				usuarioModel.setCodigo(usuarioEntity.getCodigo()); // � armazenado o valor do codigo


				FacesContext facesContext = FacesContext.getCurrentInstance(); // instancia atual do sistema que o usuario esta utilizando

				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);// realiza o mapeamento do contexto do cliente


				return "sistema/home?faces-redirect=true"; // retorna a pagina home do sistema
			}
			else{

				Uteis.Mensagem("N�o foi poss�vel efetuar o login com esse usu�rio e senha!");// caso ocorra algum erro � apresnetado a mensagem .
				return null;
			}
		}


	}

}