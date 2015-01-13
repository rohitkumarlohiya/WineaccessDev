
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
 *         &lt;element name="GetOrderStatusResult" type="{http://winedirect.com/4/0/dtc/}GetOrderStatusResponse" minOccurs="0"/>
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
    "getOrderStatusResult"
})
@XmlRootElement(name = "GetOrderStatusResponse")
public class GetOrderStatusResponse {

    @XmlElement(name = "GetOrderStatusResult")
    protected GetOrderStatusResponse2 getOrderStatusResult;

    /**
     * Gets the value of the getOrderStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetOrderStatusResponse2 }
     *     
     */
    public GetOrderStatusResponse2 getGetOrderStatusResult() {
        return getOrderStatusResult;
    }

    /**
     * Sets the value of the getOrderStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOrderStatusResponse2 }
     *     
     */
    public void setGetOrderStatusResult(GetOrderStatusResponse2 value) {
        this.getOrderStatusResult = value;
    }

}
