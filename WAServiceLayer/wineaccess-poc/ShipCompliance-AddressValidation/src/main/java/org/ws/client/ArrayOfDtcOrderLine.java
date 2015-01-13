
package org.ws.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDtcOrderLine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDtcOrderLine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DtcOrderLine" type="{http://winedirect.com/4/0/dtc/}DtcOrderLine" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDtcOrderLine", propOrder = {
    "dtcOrderLine"
})
public class ArrayOfDtcOrderLine {

    @XmlElement(name = "DtcOrderLine", nillable = true)
    protected List<DtcOrderLine> dtcOrderLine;

    /**
     * Gets the value of the dtcOrderLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtcOrderLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtcOrderLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtcOrderLine }
     * 
     * 
     */
    public List<DtcOrderLine> getDtcOrderLine() {
        if (dtcOrderLine == null) {
            dtcOrderLine = new ArrayList<DtcOrderLine>();
        }
        return this.dtcOrderLine;
    }

}
