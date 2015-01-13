
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
 *         &lt;element name="GetShipmentsResult" type="{http://winedirect.com/4/0/dtc/}GetShipmentsResponse" minOccurs="0"/>
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
    "getShipmentsResult"
})
@XmlRootElement(name = "GetShipmentsResponse")
public class GetShipmentsResponse {

    @XmlElement(name = "GetShipmentsResult")
    protected GetShipmentsResponse2 getShipmentsResult;

    /**
     * Gets the value of the getShipmentsResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetShipmentsResponse2 }
     *     
     */
    public GetShipmentsResponse2 getGetShipmentsResult() {
        return getShipmentsResult;
    }

    /**
     * Sets the value of the getShipmentsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetShipmentsResponse2 }
     *     
     */
    public void setGetShipmentsResult(GetShipmentsResponse2 value) {
        this.getShipmentsResult = value;
    }

}
