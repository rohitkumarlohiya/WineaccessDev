
package org.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckSystemStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckSystemStatusResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://winedirect.com/4/0/dtc/}IServiceResponse">
 *       &lt;sequence>
 *         &lt;element name="Outcome" type="{http://winedirect.com/4/0/dtc/}ServiceOutcome" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckSystemStatusResponse", propOrder = {
    "outcome"
})
public class CheckSystemStatusResponse2
    extends IServiceResponse
{

    @XmlElement(name = "Outcome")
    protected ServiceOutcome outcome;

    /**
     * Gets the value of the outcome property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceOutcome }
     *     
     */
    public ServiceOutcome getOutcome() {
        return outcome;
    }

    /**
     * Sets the value of the outcome property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceOutcome }
     *     
     */
    public void setOutcome(ServiceOutcome value) {
        this.outcome = value;
    }

}
