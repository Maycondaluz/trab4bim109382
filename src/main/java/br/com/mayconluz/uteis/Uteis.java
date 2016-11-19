package br.com.mayconluz.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author Maycon Luz @Date 19 de nov de 2016 �s 14:50:56
 * nesta classe estao alguns metodos que serao utilizados no sistemas mais tarde
 */

public class Uteis {

	/*
	 * retorno de uma conexao requisitada para entity configurada com JPA
	 */
	public static EntityManager JpaEntityManager(){

		FacesContext facesContext = FacesContext.getCurrentInstance(); //instancia de regra de negocio

		ExternalContext externalContext = facesContext.getExternalContext();//efetua a valida�ao das regras de negocio

		HttpServletRequest request  = (HttpServletRequest)externalContext.getRequest(); // � instanciada para receber varias solicita�oes

		return (EntityManager)request.getAttribute("entityManager"); // um EntityMAnager � retornado
	}

	//mostra uma mensagem de alerta
	public static void Mensagem(String mensagem){

		FacesContext facesContext = FacesContext.getCurrentInstance(); // instancia para apresentar a mensagem na pagina

		facesContext.addMessage(null, new FacesMessage("Alerta",mensagem)); // apresenta a mensagem na tela
	}

	//mostra uma mensagem de atencao
	public static void MensagemAtencao(String mensagem){

		FacesContext facesContext = FacesContext.getCurrentInstance(); // instancia para aprensentar a mensagem na pagina

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten��o:", mensagem)); // apresenta a mensagem na tela
	}

	//mostra uma mensagem de informa�ao
	public static void MensagemInfo(String mensagem){

		FacesContext facesContext = FacesContext.getCurrentInstance(); // instacia para apresentar a mensagem na pagina

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem)); // apresenta a mensagem na tela
	}

}


