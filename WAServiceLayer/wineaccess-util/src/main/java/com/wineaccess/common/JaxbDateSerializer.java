package com.wineaccess.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.wineaccess.property.utils.PropertyholderUtils;

public class JaxbDateSerializer extends XmlAdapter<String, Date>{
	 
    private SimpleDateFormat dateFormat = null;
    
    public JaxbDateSerializer(){
    	dateFormat = new SimpleDateFormat(PropertyholderUtils.getStringProperty("response.date.format"));
    }
 
    @Override
    public String marshal(Date date) throws Exception {
        return dateFormat.format(date);
    }
 
    @Override
    public Date unmarshal(String date) throws Exception {
        return dateFormat.parse(date);
    }
}
