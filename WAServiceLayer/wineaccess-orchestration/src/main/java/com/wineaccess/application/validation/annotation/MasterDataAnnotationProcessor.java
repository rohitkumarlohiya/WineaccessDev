package com.wineaccess.application.validation.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.property.utils.PropertyholderUtils;

public class MasterDataAnnotationProcessor {


    private static DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Collection<Field> getDeclaredFields(Collection<Field> fields, Class cla) {

	if (cla == null) {
	    return fields;
	}  else {
	    fields.addAll(new ArrayList(Arrays.asList(cla.getDeclaredFields())));
	    return getDeclaredFields(fields, cla.getSuperclass());
	}
    }

    public static List<String> validate(Object obj) throws Exception {
	List<String> errors = new ArrayList<String>();
	if (obj == null) {
	    return errors;
	}
	Collection<Field> fields = getDeclaredFields(new CopyOnWriteArrayList<Field>(), obj.getClass());

	for (Field field : fields) {

	    MasterDataAnnotation annotations = (MasterDataAnnotation) field.getAnnotation(MasterDataAnnotation.class);
	    if (annotations != null) {
		field.setAccessible(true);

		if (field.get(obj) == null)
		    continue;

		String masterDataId = String.valueOf(field.get(obj));
		try{
		Long.parseLong(masterDataId);
		}
		catch (NumberFormatException ne) {
		   continue;
		}
		MasterDataAnnotation masterDataAnnotation = ((MasterDataAnnotation) annotations);
		String masterDataTypeName = masterDataAnnotation.masterDataTypeName();
		String retrivedMasterDataType = dataRepositoryManager.getMasterDataType(masterDataId);

		if (!(retrivedMasterDataType != null && retrivedMasterDataType.equals(masterDataTypeName))) {
		    errors.add(masterDataAnnotation.message() + "\n" + "invalid master data id (" + masterDataId + ") for type ("
			    + masterDataTypeName + ")");
		}
	    } else {
		WineAccessEmbedded embeddedObject = (WineAccessEmbedded) field.getAnnotation(WineAccessEmbedded.class);
		if (embeddedObject != null) {
		    field.setAccessible(true);
		    errors.addAll(validate(field.get(obj)));
		}
	    }
	}
	return errors;
    }
}