package com.wineaccess.orders.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.BulkDeleteVO;


@XmlRootElement(name="requisitions")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteRequisitionVO extends BulkDeleteVO<RequisitionDetails>  {

}
