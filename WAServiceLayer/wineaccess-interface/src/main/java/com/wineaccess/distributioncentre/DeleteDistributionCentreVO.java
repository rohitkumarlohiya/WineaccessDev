package com.wineaccess.distributioncentre;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.common.BulkDeleteVO;

/**
 * @author gaurav.agarwal1
 * VO for bulk delete of distribution centre
 */
@XmlRootElement
@XmlType(name="deleteDistributionCentre")
public class DeleteDistributionCentreVO extends BulkDeleteVO<ViewDistributionCentreVO> {

}
