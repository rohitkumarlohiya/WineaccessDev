
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
 *         &lt;element name="GetShipmentUpdatesRequest" type="{http://winedirect.com/4/0/dtc/}GetShipmentUpdatesRequest" minOccurs="0"/>
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
    "getShipmentUpdatesRequest"
})
@XmlRootElement(name = "GetShipmentUpdates")
public class GetShipmentUpdates {

    @XmlElement(name = "GetShipmentUpdatesRequest")
    protected GetShipmentUpdatesRequest getShipmentUpdatesRequest;

    /**
     * Gets the value of the getShipmentUpdatesRequest property.
     * 
     * @return
     *     possible object is
     *     {@link GetShipmentUpdatesRequest }
     *     
     */
    public GetShipmentUpdatesRequest getGetShipmentUpdatesRequest() {
        return getShipmentUpdatesRequest;
    }

    /**
     * Sets the value of the getShipmentUpdatesRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetShipmentUpdatesRequest }
     *     
     */
    public void setGetShipmentUpdatesRequest(GetShipmentUpdatesRequest value) {
        this.getShipmentUpdatesRequest = value;
    }

}
