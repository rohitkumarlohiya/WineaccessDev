
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
 *         &lt;element name="CancelOrderRequest" type="{http://winedirect.com/4/0/dtc/}CancelOrderRequest" minOccurs="0"/>
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
    "cancelOrderRequest"
})
@XmlRootElement(name = "CancelOrder")
public class CancelOrder {

    @XmlElement(name = "CancelOrderRequest")
    protected CancelOrderRequest cancelOrderRequest;

    /**
     * Gets the value of the cancelOrderRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CancelOrderRequest }
     *     
     */
    public CancelOrderRequest getCancelOrderRequest() {
        return cancelOrderRequest;
    }

    /**
     * Sets the value of the cancelOrderRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CancelOrderRequest }
     *     
     */
    public void setCancelOrderRequest(CancelOrderRequest value) {
        this.cancelOrderRequest = value;
    }

}
