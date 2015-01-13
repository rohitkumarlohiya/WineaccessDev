package com.wineaccess.application.validation.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.property.utils.ErrorPropertyHolderUtil;
import com.wineaccess.property.utils.PropertyholderUtils;

public class ValidatingListAnnotationProcessor {

	private static DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));

    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for( int i = 0; i < fields.length; i++ ){
        	ValidatingListAnnotation annotations = (ValidatingListAnnotation)fields[i].getAnnotation(ValidatingListAnnotation.class);
            if(annotations != null ){
                try{
                	fields[i].setAccessible(true);
                	if (fields[i].get(obj) == null) continue;
                    List<Long> listValue = (List<Long>)fields[i].get(obj);
                    if(null == listValue || listValue.isEmpty()){
                    	errors.add(annotations.message()+ "\n" + ErrorPropertyHolderUtil.getProperty(annotations.message(), annotations.message()));
                    } else {
                    	for (Long l : listValue) {
                    		if (l == null) {
                    			errors.add(annotations.message()+ "\n" + ErrorPropertyHolderUtil.getProperty(annotations.message(), annotations.message()));
                    			break;
                    		}
                    	}
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return errors;
    }
}