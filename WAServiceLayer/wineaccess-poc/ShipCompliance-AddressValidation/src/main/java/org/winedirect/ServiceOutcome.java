
package org.winedirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceOutcome complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceOutcome">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://winedirect.com/4/0/dtc/}ResultEnum"/>
 *         &lt;element name="Warnings" type="{http://winedirect.com/4/0/dtc/}ArrayOfServiceMessage" minOccurs="0"/>
 *         &lt;element name="Errors" type="{http://winedirect.com/4/0/dtc/}ArrayOfServiceMessage" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceOutcome", propOrder = {
    "result",
    "warnings",
    "errors"
})
public class ServiceOutcome {

    @XmlElement(name = "Result", required = true)
    protected ResultEnum result;
    @XmlElement(name = "Warnings")
    protected ArrayOfServiceMessage warnings;
    @XmlElement(name = "Errors")
    protected ArrayOfServiceMessage errors;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link ResultEnum }
     *     
     */
    public ResultEnum getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultEnum }
     *     
     */
    public void setResult(ResultEnum value) {
        this.result = value;
    }

    /**
     * Gets the value of the warnings property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfServiceMessage }
     *     
     */
    public ArrayOfServiceMessage getWarnings() {
        return warnings;
    }

    /**
     * Sets the value of the warnings property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfServiceMessage }
     *     
     */
    public void setWarnings(ArrayOfServiceMessage value) {
        this.warnings = value;
    }

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfServiceMessage }
     *     
     */
    public ArrayOfServiceMessage getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfServiceMessage }
     *     
     */
    public void setErrors(ArrayOfServiceMessage value) {
        this.errors = value;
    }

}
