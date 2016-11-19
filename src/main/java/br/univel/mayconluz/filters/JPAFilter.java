package br.univel.mayconluz.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Maycon Luz @Date 8 de nov de 2016 �s 20:40:37
 *
 * quando for solicitado um requisi��o para o faces servelet esse filter vai ser chamado.
 *
 */
@WebFilter(servletNames ={ "Faces Servlet" })
public class JPAFilter implements Filter {


	private EntityManagerFactory entityManagerFactory;

	private String persistence_unit_name = "unit_app";

	/*
	 *  Declara��o do Construtor vazio.
	 */
    public JPAFilter() {

    }

    /*
     * Fecha a conexao, liberando todos os recursos que ele contem.
     *
     */
	public void destroy() {

		this.entityManagerFactory.close();
	}

	/* Informa��es do protocolo http com os parametros de requisica�ao e resposta e tambem para iniciar
	 * os servlets a exe�ao � o tratamento caso aconte�a algo inexperado durante a execu�ao.
	 *
	 * */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		/*Cria uma nova aplica��o de gest�o EntityManager. Este m�todo retorna uma nova
		 * EntityManagerinst�ncia de cada vez que � chamado*/
		EntityManager entityManager =  this.entityManagerFactory.createEntityManager();

		/*adiciona o entityManager na requisi��o atraves do request*/
		request.setAttribute("entityManager", entityManager);

		/*inicia a transa�ao requisitada*/
		entityManager.getTransaction().begin();

		/*inicia os servlets*/
		chain.doFilter(request, response);

		try {

			/*essa parte � executada caso nao ocorra nenhuma erro e faz o comit*/
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			/*caso tiver erro durante a execu��o � deito o rollback*/
			entityManager.getTransaction().rollback();
		}
		finally{

			/*apos executar o commit ou rollback o entityManager � finalizado*/
			entityManager.close();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

		/* cria a entityManagerFactory com os parametros definidos no persistence.xml*/
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}

}