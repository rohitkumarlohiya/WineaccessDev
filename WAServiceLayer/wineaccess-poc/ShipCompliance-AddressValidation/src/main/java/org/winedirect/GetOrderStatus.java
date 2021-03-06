
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
 *         &lt;element name="GetOrderStatusRequest" type="{http://winedirect.com/4/0/dtc/}GetOrderStatusRequest" minOccurs="0"/>
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
    "getOrderStatusRequest"
})
@XmlRootElement(name = "GetOrderStatus")
public class GetOrderStatus {

    @XmlElement(name = "GetOrderStatusRequest")
    protected GetOrderStatusRequest getOrderStatusRequest;

    /**
     * Gets the value of the getOrderStatusRequest property.
     * 
     * @return
     *     possible object is
     *     {@link GetOrderStatusRequest }
     *     
     */
    public GetOrderStatusRequest getGetOrderStatusRequest() {
        return getOrderStatusRequest;
    }

    /**
     * Sets the value of the getOrderStatusRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOrderStatusRequest }
     *     
     */
    public void setGetOrderStatusRequest(GetOrderStatusRequest value) {
        this.getOrderStatusRequest = value;
    }

}
