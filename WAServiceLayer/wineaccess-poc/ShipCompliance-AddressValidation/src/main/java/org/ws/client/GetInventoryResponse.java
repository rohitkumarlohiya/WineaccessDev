
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
 *         &lt;element name="GetInventoryResult" type="{http://winedirect.com/4/0/dtc/}GetInventoryResponse" minOccurs="0"/>
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
    "getInventoryResult"
})
@XmlRootElement(name = "GetInventoryResponse")
public class GetInventoryResponse {

    @XmlElement(name = "GetInventoryResult")
    protected GetInventoryResponse2 getInventoryResult;

    /**
     * Gets the value of the getInventoryResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetInventoryResponse2 }
     *     
     */
    public GetInventoryResponse2 getGetInventoryResult() {
        return getInventoryResult;
    }

    /**
     * Sets the value of the getInventoryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInventoryResponse2 }
     *     
     */
    public void setGetInventoryResult(GetInventoryResponse2 value) {
        this.getInventoryResult = value;
    }

}
