package br.com.mayconluz.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.mayconluz.model.UsuarioModel;
import br.com.mayconluz.repository.entity.UsuarioEntity;
import br.com.mayconluz.uteis.Uteis;

/**
 *
 * @author Maycon Luz @Date 19 de nov de 2016 às 16:58:19
 *  classe que implementa serializable para retornar um objeto do tipo usuario
 */


public class UsuarioRepository implements Serializable {


	private static final long serialVersionUID = 1L; // versao da classe

	/*
	 * apos verificaçao na base de dados, caso exista o metodo retorna um objeto do tipo UsuarioUnity
	 */
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){

		try {

			//QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");

			//PARÂMETROS DA QUERY
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());

			//RETORNA O USUÁRIO SE FOR LOCALIZADO  CASO CONTRARIO RETORNA NULL
			return (UsuarioEntity)query.getSingleResult();

		} catch (Exception e) {

			return null;
		}



	}
}