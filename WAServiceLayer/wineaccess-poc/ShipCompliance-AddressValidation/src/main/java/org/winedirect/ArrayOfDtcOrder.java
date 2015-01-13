
package org.winedirect;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDtcOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDtcOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DtcOrder" type="{http://winedirect.com/4/0/dtc/}DtcOrder" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDtcOrder", propOrder = {
    "dtcOrder"
})
public class ArrayOfDtcOrder {

    @XmlElement(name = "DtcOrder", nillable = true)
    protected List<DtcOrder> dtcOrder;

    /**
     * Gets the value of the dtcOrder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtcOrder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtcOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtcOrder }
     * 
     * 
     */
    public List<DtcOrder> getDtcOrder() {
        if (dtcOrder == null) {
            dtcOrder = new ArrayList<DtcOrder>();
        }
        return this.dtcOrder;
    }

}
