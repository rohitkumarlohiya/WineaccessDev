
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
 *         &lt;element name="CancelOrderResult" type="{http://winedirect.com/4/0/dtc/}CancelOrderResponse" minOccurs="0"/>
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
    "cancelOrderResult"
})
@XmlRootElement(name = "CancelOrderResponse")
public class CancelOrderResponse {

    @XmlElement(name = "CancelOrderResult")
    protected CancelOrderResponse2 cancelOrderResult;

    /**
     * Gets the value of the cancelOrderResult property.
     * 
     * @return
     *     possible object is
     *     {@link CancelOrderResponse2 }
     *     
     */
    public CancelOrderResponse2 getCancelOrderResult() {
        return cancelOrderResult;
    }

    /**
     * Sets the value of the cancelOrderResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CancelOrderResponse2 }
     *     
     */
    public void setCancelOrderResult(CancelOrderResponse2 value) {
        this.cancelOrderResult = value;
    }

}
