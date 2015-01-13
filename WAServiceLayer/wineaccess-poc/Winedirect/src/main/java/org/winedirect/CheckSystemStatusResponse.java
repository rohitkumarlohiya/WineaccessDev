
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
 *         &lt;element name="CheckSystemStatusResult" type="{http://winedirect.com/4/0/dtc/}CheckSystemStatusResponse" minOccurs="0"/>
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
    "checkSystemStatusResult"
})
@XmlRootElement(name = "CheckSystemStatusResponse")
public class CheckSystemStatusResponse {

    @XmlElement(name = "CheckSystemStatusResult")
    protected CheckSystemStatusResponse2 checkSystemStatusResult;

    /**
     * Gets the value of the checkSystemStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link CheckSystemStatusResponse2 }
     *     
     */
    public CheckSystemStatusResponse2 getCheckSystemStatusResult() {
        return checkSystemStatusResult;
    }

    /**
     * Sets the value of the checkSystemStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckSystemStatusResponse2 }
     *     
     */
    public void setCheckSystemStatusResult(CheckSystemStatusResponse2 value) {
        this.checkSystemStatusResult = value;
    }

}
