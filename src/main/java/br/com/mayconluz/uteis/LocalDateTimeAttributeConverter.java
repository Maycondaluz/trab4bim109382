package br.com.mayconluz.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
/**
 *
 * @author Maycon Luz @Date 19 de nov de 2016 às 18:18:09
 * Classe feita para converter data para poder persistir em JPA.
 */
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {


    //TRANSFORMA EM Timestamp NA HORA DE PERSISTIR NO BANCO DE DADOS
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {

    	if(localDateTime != null)
    		return Timestamp.valueOf(localDateTime);

    	return null;

    }

    //TRANSFORMA UM Timestamp EM LocalDateTime QUANDO RETORNAR DO BANCO PARA ENTIDADE
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {

    	if(timestamp != null)
    		return timestamp.toLocalDateTime();

    	return null;
    }
}
