
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
 *         &lt;element name="GetShipmentsRequest" type="{http://winedirect.com/4/0/dtc/}GetShipmentsRequest" minOccurs="0"/>
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
    "getShipmentsRequest"
})
@XmlRootElement(name = "GetShipments")
public class GetShipments {

    @XmlElement(name = "GetShipmentsRequest")
    protected GetShipmentsRequest getShipmentsRequest;

    /**
     * Gets the value of the getShipmentsRequest property.
     * 
     * @return
     *     possible object is
     *     {@link GetShipmentsRequest }
     *     
     */
    public GetShipmentsRequest getGetShipmentsRequest() {
        return getShipmentsRequest;
    }

    /**
     * Sets the value of the getShipmentsRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetShipmentsRequest }
     *     
     */
    public void setGetShipmentsRequest(GetShipmentsRequest value) {
        this.getShipmentsRequest = value;
    }

}
