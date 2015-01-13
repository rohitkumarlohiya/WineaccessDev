package com.wineaccess.wine;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.common.BulkDeleteVO;

/**
 * @author gaurav.agarwal1
 *
 * VO of delete wine
 */
@XmlRootElement
@XmlType(name="wineDelete")
public class WineDeleteVO extends BulkDeleteVO<WineDetails> {

	
}
