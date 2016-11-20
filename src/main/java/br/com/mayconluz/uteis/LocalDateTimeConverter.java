package br.com.mayconluz.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author Maycon Luz @Date 20 de nov de 2016 às 16:16:43
 * A classe tem o objetivo converter as datas apresentadas na view de pessoas cadastradas para aparecer a data formatada
 * a classe tambem extende de DataTimeConverter.
 */

@FacesConverter(value= LocalDateTimeConverter.ID)
public class LocalDateTimeConverter extends DateTimeConverter {

	public static final String ID="br.com.mayconluz.uteis.LocalDateTimeConverter";

	/**
	 * @param facesContext - parâmetro de contexto do CDI que irá utilizae
	 * @param uiComponent  - o componente do obejto que irá utilizar o arquivo
	 * @param value		   - a data que sera convertida
	 * @return localDateTime
	 */

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

		Date date = null; // é instanciado uma variavel do tipo Date e atribuido null a ela.
		LocalDateTime localDateTime = null; // é instanciada uma varivel do tipo LocalDateTime e atribuido null a ela.

		Object object = super.getAsObject(facesContext, uiComponent, value); // é instanciado uma variavel do tipo Obeject e atribuido os valores dos paramentos facesContext uiComponent e value.

		/*
		 * Compara se o obejeto é do tipo Date
		 */
		if(object instanceof Date){

			date = (Date) object;// convert object em Date e atribui o valor para variavel date.

			Instant instant = Instant.ofEpochMilli(date.getTime());// converte um valor e atribui a variavel  instant do tipo Instant
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); // converte um objeto data e hora e atribui o valor para a variavel LocalDateTime
	  		return localDateTime; // retorna a data formatada

		}
		else{
			// caso ocorra algum erro na execuçao do codigo acima sera tratado e apresentado a mensagem abaixo na pagina para o usuario.
			throw new IllegalArgumentException(String.format("value=%s Não foi possível converter LocalDateTime, resultado super.getAsObject=%s",value,object));
		}


	}

	/*
	 * @param facesContext - parâmetro de contexto do CDI que irá utilizae
	 * @param uiComponent  - qual o componente do obejto que irá utilizar o arquivo
	 * @param value		   - a data que será passada para ser formatado
	 * @return localDateTime
	 */
	  @Override
	  public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		  /*
		   * Compara se o valor do parametro value é null
		   */
		  if(value == null)
			  return super.getAsString(facesContext, uiComponent, value);

		  // compara de se o valor é um LocalDateTime
		  if(value instanceof LocalDateTime){

			  LocalDateTime localDateTime = (LocalDateTime) value; // realiza um cast de value para LocalDateTime e atribui o valor á varivel LocalDateTime

			  Instant instant = localDateTime.toInstant(ZoneOffset.UTC); // atribui o valor para a variavel instant

			  Date  date =  Date.from(instant);

			  return super.getAsString(facesContext, uiComponent, date);
		  }
		  else{
			  // caso ocorra algum erro na execuçao do codigo acima é tratado e apresentado a mensagem na pagina para o usuario.
			  throw new IllegalArgumentException(String.format("value=%s não é um LocalDateTime",value));
		  }

	  }
}
