
package org.ws.client;

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
 *         &lt;element name="ValidateAddressRequest" type="{http://winedirect.com/4/0/dtc/}ValidateAddressRequest" minOccurs="0"/>
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
    "validateAddressRequest"
})
@XmlRootElement(name = "ValidateAddress")
public class ValidateAddress {

    @XmlElement(name = "ValidateAddressRequest")
    protected ValidateAddressRequest validateAddressRequest;

    /**
     * Gets the value of the validateAddressRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ValidateAddressRequest }
     *     
     */
    public ValidateAddressRequest getValidateAddressRequest() {
        return validateAddressRequest;
    }

    /**
     * Sets the value of the validateAddressRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidateAddressRequest }
     *     
     */
    public void setValidateAddressRequest(ValidateAddressRequest value) {
        this.validateAddressRequest = value;
    }

}
