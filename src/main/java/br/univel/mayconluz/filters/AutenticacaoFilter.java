package br.univel.mayconluz.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.mayconluz.model.UsuarioModel;



@WebFilter("/sistema/*") // annotation que ira filtrar as requisi��es de todos os itens que tiverem em /sistema/*

/**
 *
 * @author Maycon Luz @Date 19 de nov de 2016 �s 16:11:39
 * Esta classe efetua a implementa�ao da interface filter e realiza a autentica��o via HTTP
 */

public class AutenticacaoFilter implements Filter {

	/*
	 * Construtor vazio
	 */
    public AutenticacaoFilter() {

    }
    /*
	 * Metodo implmentado da interface Filter
	 */
	public void destroy() {

	}

	/*
	 * Metodo que realiza o filtro pelo protocolo HTTP
	 * nos parametros contem  requisica�ao e resposta e tambem para iniciar,
	 * throws � o tratamento caso aconte�a algo inexperado durante a execu�ao
	 */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession httpSession 				= ((HttpServletRequest) request).getSession(); // a sessao � iniciada

		HttpServletRequest httpServletRequest   = (HttpServletRequest) request; // � atribuido o valor do request

		HttpServletResponse httpServletResponse = (HttpServletResponse) response; // � atribuido o valor do response

		if(httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1){

			UsuarioModel usuarioModel =(UsuarioModel) httpSession.getAttribute("usuarioAutenticado"); // atributo presente na sessao

			if(usuarioModel == null){

				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/index.xhtml");

			}
			else{

				chain.doFilter(request, response);
			}
		}
		else{

			chain.doFilter(request, response);
		}
	}

	/*
	 * Metodo vazio da interface
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}