
package org.winedirect;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDtcShipment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDtcShipment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DtcShipment" type="{http://winedirect.com/4/0/dtc/}DtcShipment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDtcShipment", propOrder = {
    "dtcShipment"
})
public class ArrayOfDtcShipment {

    @XmlElement(name = "DtcShipment", nillable = true)
    protected List<DtcShipment> dtcShipment;

    /**
     * Gets the value of the dtcShipment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtcShipment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtcShipment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtcShipment }
     * 
     * 
     */
    public List<DtcShipment> getDtcShipment() {
        if (dtcShipment == null) {
            dtcShipment = new ArrayList<DtcShipment>();
        }
        return this.dtcShipment;
    }

}
