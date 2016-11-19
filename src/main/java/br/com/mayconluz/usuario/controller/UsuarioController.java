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



@Named(value="usuarioController") // annotation que diz que é um bean gerenciavel
@SessionScoped // annotation que informa que o escopo da sessao é um bean gerenciavel

/**
 *
 * @author Maycon Luz @Date 19 de nov de 2016 às 15:31:39
 *  Classe serializada que controla a injecao de dependencias dos objetos da classe
 */

public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L; // versao da classe

	@Inject // annotation para injeçao de beans
	private UsuarioModel usuarioModel; // instancia usuarioModel do tipo UsuarioModel

	@Inject // annotation para injeçao de beans
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
	 * é passado por parametro o valor do objeto da classe
	 */

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	/*
	 *retorna o usuario na autenticaçao
	 */
	public UsuarioModel GetUsuarioSession(){

		FacesContext facesContext = FacesContext.getCurrentInstance(); // instancia do FacesContext

		return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado"); // neste momento é feita a autenticação
	}

	/*
	 * finaliza a sessão do usuário e redireciona para a página de login
	 */
	public String Logout(){

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();// efetua a validação da sessao

		return "/index.xhtml?faces-redirect=true"; // apos a validaçao estiver correta é retornado a pagina index.xhtml
	}
	/*
	 *  metodo que realiza a autenticaçao caso o usuario seja válido, tambem retorna a pagina do sistema
	 *  caso os requisitos sejam atendidos, caso contrario sera retornado null.
	 */
	public String EfetuarLogin(){

		if(StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())){

			Uteis.Mensagem("Favor informar o login!"); // faz a solicitaçao para o metodo que mostra a mensagem informada
			return null;// retorna nulo
		}
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){

			Uteis.Mensagem("Favor informara senha!");// faz a solicitaçao para o metodo que mostra a mensagem informada
			return null; // retorna nulo
		}
		else{

			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel); // apos fazder a verificação do usuario e certificar que o mesmo é valido é retornado os dados do  usuario

			if(usuarioEntity!= null){ // compara se usuarioEntity é diferente de null

				usuarioModel.setSenha(null); // atribui o valor null para senha
				usuarioModel.setCodigo(usuarioEntity.getCodigo()); // é armazenado o valor do codigo


				FacesContext facesContext = FacesContext.getCurrentInstance(); // instancia atual do sistema que o usuario esta utilizando

				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);// realiza o mapeamento do contexto do cliente


				return "sistema/home?faces-redirect=true"; // retorna a pagina home do sistema
			}
			else{

				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");// caso ocorra algum erro é apresnetado a mensagem .
				return null;
			}
		}


	}

}