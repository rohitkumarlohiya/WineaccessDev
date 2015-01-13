
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
 *         &lt;element name="SubmitOrdersRequest" type="{http://winedirect.com/4/0/dtc/}SubmitOrdersRequest" minOccurs="0"/>
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
    "submitOrdersRequest"
})
@XmlRootElement(name = "SubmitOrders")
public class SubmitOrders {

    @XmlElement(name = "SubmitOrdersRequest")
    protected SubmitOrdersRequest submitOrdersRequest;

    /**
     * Gets the value of the submitOrdersRequest property.
     * 
     * @return
     *     possible object is
     *     {@link SubmitOrdersRequest }
     *     
     */
    public SubmitOrdersRequest getSubmitOrdersRequest() {
        return submitOrdersRequest;
    }

    /**
     * Sets the value of the submitOrdersRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmitOrdersRequest }
     *     
     */
    public void setSubmitOrdersRequest(SubmitOrdersRequest value) {
        this.submitOrdersRequest = value;
    }

}
