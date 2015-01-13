
package org.winedirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidateAddressResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidateAddressResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://winedirect.com/4/0/dtc/}IServiceResponse">
 *       &lt;sequence>
 *         &lt;element name="Outcome" type="{http://winedirect.com/4/0/dtc/}ServiceOutcome" minOccurs="0"/>
 *         &lt;element name="AddressValidated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ValidatedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValidationScore" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CorrectedAddress" type="{http://winedirect.com/4/0/dtc/}AddressInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateAddressResponse", propOrder = {
    "outcome",
    "addressValidated",
    "validatedBy",
    "validationScore",
    "correctedAddress"
})
public class ValidateAddressResponse2
    extends IServiceResponse
{

    @XmlElement(name = "Outcome")
    protected ServiceOutcome outcome;
    @XmlElement(name = "AddressValidated")
    protected boolean addressValidated;
    @XmlElement(name = "ValidatedBy")
    protected String validatedBy;
    @XmlElement(name = "ValidationScore")
    protected int validationScore;
    @XmlElement(name = "CorrectedAddress")
    protected AddressInfo correctedAddress;

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

    /**
     * Gets the value of the addressValidated property.
     * 
     */
    public boolean isAddressValidated() {
        return addressValidated;
    }

    /**
     * Sets the value of the addressValidated property.
     * 
     */
    public void setAddressValidated(boolean value) {
        this.addressValidated = value;
    }

    /**
     * Gets the value of the validatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidatedBy() {
        return validatedBy;
    }

    /**
     * Sets the value of the validatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidatedBy(String value) {
        this.validatedBy = value;
    }

    /**
     * Gets the value of the validationScore property.
     * 
     */
    public int getValidationScore() {
        return validationScore;
    }

    /**
     * Sets the value of the validationScore property.
     * 
     */
    public void setValidationScore(int value) {
        this.validationScore = value;
    }

    /**
     * Gets the value of the correctedAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressInfo }
     *     
     */
    public AddressInfo getCorrectedAddress() {
        return correctedAddress;
    }

    /**
     * Sets the value of the correctedAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressInfo }
     *     
     */
    public void setCorrectedAddress(AddressInfo value) {
        this.correctedAddress = value;
    }

}
