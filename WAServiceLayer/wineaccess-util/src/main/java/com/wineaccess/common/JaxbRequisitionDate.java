package com.wineaccess.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.wineaccess.property.utils.PropertyholderUtils;

public class JaxbRequisitionDate extends XmlAdapter<String, Date>{
private SimpleDateFormat dateFormat = null;
    
    public JaxbRequisitionDate(){
    	dateFormat = new SimpleDateFormat("MM/dd/yyyy");
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
