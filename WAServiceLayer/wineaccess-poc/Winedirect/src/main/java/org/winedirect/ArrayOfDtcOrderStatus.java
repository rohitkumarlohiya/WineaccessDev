
package org.winedirect;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDtcOrderStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDtcOrderStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DtcOrderStatus" type="{http://winedirect.com/4/0/dtc/}DtcOrderStatus" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDtcOrderStatus", propOrder = {
    "dtcOrderStatus"
})
public class ArrayOfDtcOrderStatus {

    @XmlElement(name = "DtcOrderStatus", nillable = true)
    protected List<DtcOrderStatus> dtcOrderStatus;

    /**
     * Gets the value of the dtcOrderStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtcOrderStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtcOrderStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtcOrderStatus }
     * 
     * 
     */
    public List<DtcOrderStatus> getDtcOrderStatus() {
        if (dtcOrderStatus == null) {
            dtcOrderStatus = new ArrayList<DtcOrderStatus>();
        }
        return this.dtcOrderStatus;
    }

}
