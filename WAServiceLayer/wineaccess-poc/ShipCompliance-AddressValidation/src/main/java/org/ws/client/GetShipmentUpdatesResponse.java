
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
 *         &lt;element name="GetShipmentUpdatesResult" type="{http://winedirect.com/4/0/dtc/}GetShipmentUpdatesResponse" minOccurs="0"/>
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
    "getShipmentUpdatesResult"
})
@XmlRootElement(name = "GetShipmentUpdatesResponse")
public class GetShipmentUpdatesResponse {

    @XmlElement(name = "GetShipmentUpdatesResult")
    protected GetShipmentUpdatesResponse2 getShipmentUpdatesResult;

    /**
     * Gets the value of the getShipmentUpdatesResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetShipmentUpdatesResponse2 }
     *     
     */
    public GetShipmentUpdatesResponse2 getGetShipmentUpdatesResult() {
        return getShipmentUpdatesResult;
    }

    /**
     * Sets the value of the getShipmentUpdatesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetShipmentUpdatesResponse2 }
     *     
     */
    public void setGetShipmentUpdatesResult(GetShipmentUpdatesResponse2 value) {
        this.getShipmentUpdatesResult = value;
    }

}
