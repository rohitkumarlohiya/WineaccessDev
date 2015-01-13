
package org.ws.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDtcShipmentLine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDtcShipmentLine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DtcShipmentLine" type="{http://winedirect.com/4/0/dtc/}DtcShipmentLine" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDtcShipmentLine", propOrder = {
    "dtcShipmentLine"
})
public class ArrayOfDtcShipmentLine {

    @XmlElement(name = "DtcShipmentLine", nillable = true)
    protected List<DtcShipmentLine> dtcShipmentLine;

    /**
     * Gets the value of the dtcShipmentLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtcShipmentLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtcShipmentLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtcShipmentLine }
     * 
     * 
     */
    public List<DtcShipmentLine> getDtcShipmentLine() {
        if (dtcShipmentLine == null) {
            dtcShipmentLine = new ArrayList<DtcShipmentLine>();
        }
        return this.dtcShipmentLine;
    }

}
