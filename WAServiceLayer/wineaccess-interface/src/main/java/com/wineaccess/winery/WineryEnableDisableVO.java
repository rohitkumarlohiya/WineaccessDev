package com.wineaccess.winery;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.common.BulkDeleteVO;

/**
 * @author gaurav.agarwal1
 * VO to show the enable/disable response list
 */
@XmlRootElement
@XmlType(name="wineryEnableDisable")
public class WineryEnableDisableVO extends BulkDeleteVO<WineryDetails> {

}
