package com.wineaccess.wine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.BulkDeleteVO;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ChangeWineStatusResponse extends BulkDeleteVO<WineDetails> {

	
}