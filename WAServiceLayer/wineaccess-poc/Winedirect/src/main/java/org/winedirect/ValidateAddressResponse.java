
package org.winedirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ValidateAddressResult" type="{http://winedirect.com/4/0/dtc/}ValidateAddressResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "validateAddressResult"
})
@XmlRootElement(name = "ValidateAddressResponse")
public class ValidateAddressResponse {

    @XmlElement(name = "ValidateAddressResult")
    protected ValidateAddressResponse2 validateAddressResult;

    /**
     * Gets the value of the validateAddressResult property.
     * 
     * @return
     *     possible object is
     *     {@link ValidateAddressResponse2 }
     *     
     */
    public ValidateAddressResponse2 getValidateAddressResult() {
        return validateAddressResult;
    }

    /**
     * Sets the value of the validateAddressResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidateAddressResponse2 }
     *     
     */
    public void setValidateAddressResult(ValidateAddressResponse2 value) {
        this.validateAddressResult = value;
    }

}
