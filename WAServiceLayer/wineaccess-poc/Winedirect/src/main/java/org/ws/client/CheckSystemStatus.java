
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
 *         &lt;element name="CheckSystemStatusRequest" type="{http://winedirect.com/4/0/dtc/}CheckSystemStatusRequest" minOccurs="0"/>
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
    "checkSystemStatusRequest"
})
@XmlRootElement(name = "CheckSystemStatus")
public class CheckSystemStatus {

    @XmlElement(name = "CheckSystemStatusRequest")
    protected CheckSystemStatusRequest checkSystemStatusRequest;

    /**
     * Gets the value of the checkSystemStatusRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CheckSystemStatusRequest }
     *     
     */
    public CheckSystemStatusRequest getCheckSystemStatusRequest() {
        return checkSystemStatusRequest;
    }

    /**
     * Sets the value of the checkSystemStatusRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckSystemStatusRequest }
     *     
     */
    public void setCheckSystemStatusRequest(CheckSystemStatusRequest value) {
        this.checkSystemStatusRequest = value;
    }

}
