package com.wineaccess.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.wineaccess.property.utils.PropertyholderUtils;

public class JaxbDateHourSerializer extends XmlAdapter<String, Date>{
	 
    private SimpleDateFormat dateFormat = null;
    
    public JaxbDateHourSerializer(){
    	dateFormat = new SimpleDateFormat(PropertyholderUtils.getStringProperty("response.date.format.hour"));
    }
    
    
    @Override
    public String marshal(Date date) throws Exception {
        return dateFormat.format(date);
    }
 
    @Override
    public Date unmarshal(String date) throws Exception {
    	if(("").equals(date))
    		date = dateFormat.format(new Date());
    	
        return dateFormat.parse(date);
    }
}
