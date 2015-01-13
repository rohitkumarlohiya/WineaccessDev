
package org.winedirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidateAddressRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidateAddressRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthenticationInfo" type="{http://winedirect.com/4/0/dtc/}AuthenticationInfo" minOccurs="0"/>
 *         &lt;element name="Address" type="{http://winedirect.com/4/0/dtc/}AddressInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateAddressRequest", propOrder = {
    "authenticationInfo",
    "address"
})
public class ValidateAddressRequest {

    @XmlElement(name = "AuthenticationInfo")
    protected AuthenticationInfo authenticationInfo;
    @XmlElement(name = "Address")
    protected AddressInfo address;

    /**
     * Gets the value of the authenticationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationInfo }
     *     
     */
    public AuthenticationInfo getAuthenticationInfo() {
        return authenticationInfo;
    }

    /**
     * Sets the value of the authenticationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationInfo }
     *     
     */
    public void setAuthenticationInfo(AuthenticationInfo value) {
        this.authenticationInfo = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressInfo }
     *     
     */
    public AddressInfo getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressInfo }
     *     
     */
    public void setAddress(AddressInfo value) {
        this.address = value;
    }

}
