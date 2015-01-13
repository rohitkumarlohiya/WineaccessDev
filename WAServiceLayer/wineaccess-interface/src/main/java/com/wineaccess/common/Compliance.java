package com.wineaccess.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * compliance details
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class Compliance implements Serializable {

    private static final long serialVersionUID = 1395999106971691825L;

    private String licenseType;
    private String status;

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
