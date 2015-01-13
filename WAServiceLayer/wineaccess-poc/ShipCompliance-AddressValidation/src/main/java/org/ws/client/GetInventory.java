
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
 *         &lt;element name="GetInventoryRequest" type="{http://winedirect.com/4/0/dtc/}GetInventoryRequest" minOccurs="0"/>
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
    "getInventoryRequest"
})
@XmlRootElement(name = "GetInventory")
public class GetInventory {

    @XmlElement(name = "GetInventoryRequest")
    protected GetInventoryRequest getInventoryRequest;

    /**
     * Gets the value of the getInventoryRequest property.
     * 
     * @return
     *     possible object is
     *     {@link GetInventoryRequest }
     *     
     */
    public GetInventoryRequest getGetInventoryRequest() {
        return getInventoryRequest;
    }

    /**
     * Sets the value of the getInventoryRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInventoryRequest }
     *     
     */
    public void setGetInventoryRequest(GetInventoryRequest value) {
        this.getInventoryRequest = value;
    }

}
