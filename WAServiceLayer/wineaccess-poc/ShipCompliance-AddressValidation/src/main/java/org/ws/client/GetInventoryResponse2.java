
package org.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetInventoryResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetInventoryResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://winedirect.com/4/0/dtc/}IServiceResponse">
 *       &lt;sequence>
 *         &lt;element name="Outcome" type="{http://winedirect.com/4/0/dtc/}ServiceOutcome" minOccurs="0"/>
 *         &lt;element name="Items" type="{http://winedirect.com/4/0/dtc/}ArrayOfInventoryItem" minOccurs="0"/>
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
@XmlType(name = "GetInventoryResponse", propOrder = {
    "outcome",
    "items",
    "matchingRecords",
    "recordsRemaining"
})
public class GetInventoryResponse2
    extends IServiceResponse
{

    @XmlElement(name = "Outcome")
    protected ServiceOutcome outcome;
    @XmlElement(name = "Items")
    protected ArrayOfInventoryItem items;
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
     * Gets the value of the items property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInventoryItem }
     *     
     */
    public ArrayOfInventoryItem getItems() {
        return items;
    }

    /**
     * Sets the value of the items property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInventoryItem }
     *     
     */
    public void setItems(ArrayOfInventoryItem value) {
        this.items = value;
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
