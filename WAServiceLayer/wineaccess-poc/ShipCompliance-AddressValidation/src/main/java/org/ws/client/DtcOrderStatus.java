
package org.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DtcOrderStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DtcOrderStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BatchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WarehouseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderStatusMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrentHold" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Lines" type="{http://winedirect.com/4/0/dtc/}ArrayOfDtcOrderLine" minOccurs="0"/>
 *         &lt;element name="Shipments" type="{http://winedirect.com/4/0/dtc/}ArrayOfDtcShipment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DtcOrderStatus", propOrder = {
    "orderId",
    "batchId",
    "warehouseCode",
    "orderStatusCode",
    "orderStatusMessage",
    "currentHold",
    "lines",
    "shipments"
})
public class DtcOrderStatus {

    @XmlElement(name = "OrderId")
    protected String orderId;
    @XmlElement(name = "BatchId")
    protected String batchId;
    @XmlElement(name = "WarehouseCode")
    protected String warehouseCode;
    @XmlElement(name = "OrderStatusCode")
    protected String orderStatusCode;
    @XmlElement(name = "OrderStatusMessage")
    protected String orderStatusMessage;
    @XmlElement(name = "CurrentHold")
    protected String currentHold;
    @XmlElement(name = "Lines")
    protected ArrayOfDtcOrderLine lines;
    @XmlElement(name = "Shipments")
    protected ArrayOfDtcShipment shipments;

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the batchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * Sets the value of the batchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchId(String value) {
        this.batchId = value;
    }

    /**
     * Gets the value of the warehouseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * Sets the value of the warehouseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarehouseCode(String value) {
        this.warehouseCode = value;
    }

    /**
     * Gets the value of the orderStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderStatusCode() {
        return orderStatusCode;
    }

    /**
     * Sets the value of the orderStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderStatusCode(String value) {
        this.orderStatusCode = value;
    }

    /**
     * Gets the value of the orderStatusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderStatusMessage() {
        return orderStatusMessage;
    }

    /**
     * Sets the value of the orderStatusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderStatusMessage(String value) {
        this.orderStatusMessage = value;
    }

    /**
     * Gets the value of the currentHold property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentHold() {
        return currentHold;
    }

    /**
     * Sets the value of the currentHold property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentHold(String value) {
        this.currentHold = value;
    }

    /**
     * Gets the value of the lines property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDtcOrderLine }
     *     
     */
    public ArrayOfDtcOrderLine getLines() {
        return lines;
    }

    /**
     * Sets the value of the lines property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDtcOrderLine }
     *     
     */
    public void setLines(ArrayOfDtcOrderLine value) {
        this.lines = value;
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

}
