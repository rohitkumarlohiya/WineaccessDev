package com.wineaccess.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.wineaccess.property.utils.PropertyholderUtils;

/**
 * @author gaurav.agarwal1
 *
 */
public class JsonDateSerializer extends JsonSerializer<Date>{
	 
    private SimpleDateFormat dateFormat = null;
    
    public JsonDateSerializer(){
    	dateFormat = new SimpleDateFormat(PropertyholderUtils.getStringProperty("response.date.format"));
    }
 
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {

    String formattedDate = dateFormat.format(date);
    gen.writeString(formattedDate);

    }
}
