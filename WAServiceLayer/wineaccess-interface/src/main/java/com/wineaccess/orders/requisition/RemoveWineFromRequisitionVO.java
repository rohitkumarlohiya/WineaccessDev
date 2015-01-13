package com.wineaccess.orders.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.common.BulkDeleteVO;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="removeWineFromRequisition")
public class RemoveWineFromRequisitionVO extends BulkDeleteVO<WineInRequistionResultModel>{
	
}