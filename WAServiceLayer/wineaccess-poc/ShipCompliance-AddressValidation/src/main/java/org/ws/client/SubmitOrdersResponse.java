
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
 *         &lt;element name="SubmitOrdersResult" type="{http://winedirect.com/4/0/dtc/}SubmitOrdersResponse" minOccurs="0"/>
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
    "submitOrdersResult"
})
@XmlRootElement(name = "SubmitOrdersResponse")
public class SubmitOrdersResponse {

    @XmlElement(name = "SubmitOrdersResult")
    protected SubmitOrdersResponse2 submitOrdersResult;

    /**
     * Gets the value of the submitOrdersResult property.
     * 
     * @return
     *     possible object is
     *     {@link SubmitOrdersResponse2 }
     *     
     */
    public SubmitOrdersResponse2 getSubmitOrdersResult() {
        return submitOrdersResult;
    }

    /**
     * Sets the value of the submitOrdersResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmitOrdersResponse2 }
     *     
     */
    public void setSubmitOrdersResult(SubmitOrdersResponse2 value) {
        this.submitOrdersResult = value;
    }

}
