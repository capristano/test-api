package br.com.supero.test.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Padrão de deserialização para datas
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) throws IOException, JsonProcessingException {
    	//formatos de data aceitos
    	String str = paramJsonParser.getText().trim();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy h:mm:ss aaa", Locale.US);
        SimpleDateFormat formatterBr = new SimpleDateFormat("MMM d, yyyy h:mm:ss aaa");
        // mesmo formato de serialização, e também o mesmo usado no frontend
        SimpleDateFormat formatterSys = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return formatter.parse(str);
        } catch (ParseException e) {}
        try {
            return formatterBr.parse(str);
        } catch (ParseException e) {}
        try {
            return formatterSys.parse(str);
        } catch (ParseException e) {}
        try {
        	return paramDeserializationContext.parseDate(str);
        } catch (Exception e) {}
        
        return new Date(); 
    }

}
