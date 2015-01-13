
package org.winedirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetShipmentsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetShipmentsResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://winedirect.com/4/0/dtc/}IServiceResponse">
 *       &lt;sequence>
 *         &lt;element name="Outcome" type="{http://winedirect.com/4/0/dtc/}ServiceOutcome" minOccurs="0"/>
 *         &lt;element name="Shipments" type="{http://winedirect.com/4/0/dtc/}ArrayOfDtcShipment" minOccurs="0"/>
 *         &lt;element name="MatchingRecords" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RecordsRemaining" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetShipmentsResponse", propOrder = {
    "outcome",
    "shipments",
    "matchingRecords",
    "recordsRemaining"
})
public class GetShipmentsResponse2
    extends IServiceResponse
{

    @XmlElement(name = "Outcome")
    protected ServiceOutcome outcome;
    @XmlElement(name = "Shipments")
    protected ArrayOfDtcShipment shipments;
    @XmlElement(name = "MatchingRecords")
    protected int matchingRecords;
    @XmlElement(name = "RecordsRemaining")
    protected int recordsRemaining;

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
     * Gets the value of the shipments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDtcShipment }
     *     
     */
    public ArrayOfDtcShipment getShipments() {
        return shipments;
    }

    /**
     * Sets the value of the shipments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDtcShipment }
     *     
     */
    public void setShipments(ArrayOfDtcShipment value) {
        this.shipments = value;
    }

    /**
     * Gets the value of the matchingRecords property.
     * 
     */
    public int getMatchingRecords() {
        return matchingRecords;
    }

    /**
     * Sets the value of the matchingRecords property.
     * 
     */
    public void setMatchingRecords(int value) {
        this.matchingRecords = value;
    }

    /**
     * Gets the value of the recordsRemaining property.
     * 
     */
    public int getRecordsRemaining() {
        return recordsRemaining;
    }

    /**
     * Sets the value of the recordsRemaining property.
     * 
     */
    public void setRecordsRemaining(int value) {
        this.recordsRemaining = value;
    }

}
