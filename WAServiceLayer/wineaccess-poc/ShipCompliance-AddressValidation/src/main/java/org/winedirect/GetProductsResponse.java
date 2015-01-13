
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
 *         &lt;element name="GetProductsResult" type="{http://winedirect.com/4/0/dtc/}GetProductsResponse" minOccurs="0"/>
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
    "getProductsResult"
})
@XmlRootElement(name = "GetProductsResponse")
public class GetProductsResponse {

    @XmlElement(name = "GetProductsResult")
    protected GetProductsResponse2 getProductsResult;

    /**
     * Gets the value of the getProductsResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetProductsResponse2 }
     *     
     */
    public GetProductsResponse2 getGetProductsResult() {
        return getProductsResult;
    }

    /**
     * Sets the value of the getProductsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetProductsResponse2 }
     *     
     */
    public void setGetProductsResult(GetProductsResponse2 value) {
        this.getProductsResult = value;
    }

}
