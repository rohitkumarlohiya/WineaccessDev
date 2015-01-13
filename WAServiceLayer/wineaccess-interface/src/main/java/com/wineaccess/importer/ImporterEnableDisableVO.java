package com.wineaccess.importer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.common.BulkDeleteVO;

/**
 * @author gaurav.agarwal1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="importerEnableDisable")
public class ImporterEnableDisableVO extends BulkDeleteVO<ImporterDetails> {

}
