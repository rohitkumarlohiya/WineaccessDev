
package org.winedirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DtcOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DtcOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BatchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderType" type="{http://winedirect.com/4/0/dtc/}DtcOrderTypeEnum"/>
 *         &lt;element name="OnSitePurchase" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="WarehouseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubinventoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NotificationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackingSlipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GiftMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reference1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reference2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reference3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SoldTo" type="{http://winedirect.com/4/0/dtc/}ConsumerInfo" minOccurs="0"/>
 *         &lt;element name="ShipTo" type="{http://winedirect.com/4/0/dtc/}ConsumerInfo" minOccurs="0"/>
 *         &lt;element name="RequestedPriority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequestedShipDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShippingInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShippingCharges" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="HandlingCharges" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SalesTax" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Insurance" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalSale" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="OrderLines" type="{http://winedirect.com/4/0/dtc/}ArrayOfDtcOrderLine" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DtcOrder", propOrder = {
    "orderId",
    "batchId",
    "orderType",
    "onSitePurchase",
    "warehouseCode",
    "subinventoryCode",
    "notificationCode",
    "packingSlipCode",
    "giftMessage",
    "reference1",
    "reference2",
    "reference3",
    "soldTo",
    "shipTo",
    "requestedPriority",
    "requestedShipDate",
    "shippingInstructions",
    "shippingCharges",
    "handlingCharges",
    "salesTax",
    "insurance",
    "totalSale",
    "orderLines"
})
public class DtcOrder {

    @XmlElement(name = "OrderId")
    protected String orderId;
    @XmlElement(name = "BatchId")
    protected String batchId;
    @XmlElement(name = "OrderType", required = true)
    protected DtcOrderTypeEnum orderType;
    @XmlElement(name = "OnSitePurchase")
    protected boolean onSitePurchase;
    @XmlElement(name = "WarehouseCode")
    protected String warehouseCode;
    @XmlElement(name = "SubinventoryCode")
    protected String subinventoryCode;
    @XmlElement(name = "NotificationCode")
    protected String notificationCode;
    @XmlElement(name = "PackingSlipCode")
    protected String packingSlipCode;
    @XmlElement(name = "GiftMessage")
    protected String giftMessage;
    @XmlElement(name = "Reference1")
    protected String reference1;
    @XmlElement(name = "Reference2")
    protected String reference2;
    @XmlElement(name = "Reference3")
    protected String reference3;
    @XmlElement(name = "SoldTo")
    protected ConsumerInfo soldTo;
    @XmlElement(name = "ShipTo")
    protected ConsumerInfo shipTo;
    @XmlElement(name = "RequestedPriority")
    protected String requestedPriority;
    @XmlElement(name = "RequestedShipDate")
    protected String requestedShipDate;
    @XmlElement(name = "ShippingInstructions")
    protected String shippingInstructions;
    @XmlElement(name = "ShippingCharges")
    protected double shippingCharges;
    @XmlElement(name = "HandlingCharges")
    protected double handlingCharges;
    @XmlElement(name = "SalesTax")
    protected double salesTax;
    @XmlElement(name = "Insurance")
    protected double insurance;
    @XmlElement(name = "TotalSale")
    protected double totalSale;
    @XmlElement(name = "OrderLines")
    protected ArrayOfDtcOrderLine orderLines;

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
     * Gets the value of the orderType property.
     * 
     * @return
     *     possible object is
     *     {@link DtcOrderTypeEnum }
     *     
     */
    public DtcOrderTypeEnum getOrderType() {
        return orderType;
    }

    /**
     * Sets the value of the orderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtcOrderTypeEnum }
     *     
     */
    public void setOrderType(DtcOrderTypeEnum value) {
        this.orderType = value;
    }

    /**
     * Gets the value of the onSitePurchase property.
     * 
     */
    public boolean isOnSitePurchase() {
        return onSitePurchase;
    }

    /**
     * Sets the value of the onSitePurchase property.
     * 
     */
    public void setOnSitePurchase(boolean value) {
        this.onSitePurchase = value;
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
     * Gets the value of the subinventoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubinventoryCode() {
        return subinventoryCode;
    }

    /**
     * Sets the value of the subinventoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubinventoryCode(String value) {
        this.subinventoryCode = value;
    }

    /**
     * Gets the value of the notificationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationCode() {
        return notificationCode;
    }

    /**
     * Sets the value of the notificationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationCode(String value) {
        this.notificationCode = value;
    }

    /**
     * Gets the value of the packingSlipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackingSlipCode() {
        return packingSlipCode;
    }

    /**
     * Sets the value of the packingSlipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackingSlipCode(String value) {
        this.packingSlipCode = value;
    }

    /**
     * Gets the value of the giftMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGiftMessage() {
        return giftMessage;
    }

    /**
     * Sets the value of the giftMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGiftMessage(String value) {
        this.giftMessage = value;
    }

    /**
     * Gets the value of the reference1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference1() {
        return reference1;
    }

    /**
     * Sets the value of the reference1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference1(String value) {
        this.reference1 = value;
    }

    /**
     * Gets the value of the reference2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference2() {
        return reference2;
    }

    /**
     * Sets the value of the reference2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference2(String value) {
        this.reference2 = value;
    }

    /**
     * Gets the value of the reference3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference3() {
        return reference3;
    }

    /**
     * Sets the value of the reference3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference3(String value) {
        this.reference3 = value;
    }

    /**
     * Gets the value of the soldTo property.
     * 
     * @return
     *     possible object is
     *     {@link ConsumerInfo }
     *     
     */
    public ConsumerInfo getSoldTo() {
        return soldTo;
    }

    /**
     * Sets the value of the soldTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsumerInfo }
     *     
     */
    public void setSoldTo(ConsumerInfo value) {
        this.soldTo = value;
    }

    /**
     * Gets the value of the shipTo property.
     * 
     * @return
     *     possible object is
     *     {@link ConsumerInfo }
     *     
     */
    public ConsumerInfo getShipTo() {
        return shipTo;
    }

    /**
     * Sets the value of the shipTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsumerInfo }
     *     
     */
    public void setShipTo(ConsumerInfo value) {
        this.shipTo = value;
    }

    /**
     * Gets the value of the requestedPriority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestedPriority() {
        return requestedPriority;
    }

    /**
     * Sets the value of the requestedPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestedPriority(String value) {
        this.requestedPriority = value;
    }

    /**
     * Gets the value of the requestedShipDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestedShipDate() {
        return requestedShipDate;
    }

    /**
     * Sets the value of the requestedShipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestedShipDate(String value) {
        this.requestedShipDate = value;
    }

    /**
     * Gets the value of the shippingInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShippingInstructions() {
        return shippingInstructions;
    }

    /**
     * Sets the value of the shippingInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingInstructions(String value) {
        this.shippingInstructions = value;
    }

    /**
     * Gets the value of the shippingCharges property.
     * 
     */
    public double getShippingCharges() {
        return shippingCharges;
    }

    /**
     * Sets the value of the shippingCharges property.
     * 
     */
    public void setShippingCharges(double value) {
        this.shippingCharges = value;
    }

    /**
     * Gets the value of the handlingCharges property.
     * 
     */
    public double getHandlingCharges() {
        return handlingCharges;
    }

    /**
     * Sets the value of the handlingCharges property.
     * 
     */
    public void setHandlingCharges(double value) {
        this.handlingCharges = value;
    }

    /**
     * Gets the value of the salesTax property.
     * 
     */
    public double getSalesTax() {
        return salesTax;
    }

    /**
     * Sets the value of the salesTax property.
     * 
     */
    public void setSalesTax(double value) {
        this.salesTax = value;
    }

    /**
     * Gets the value of the insurance property.
     * 
     */
    public double getInsurance() {
        return insurance;
    }

    /**
     * Sets the value of the insurance property.
     * 
     */
    public void setInsurance(double value) {
        this.insurance = value;
    }

    /**
     * Gets the value of the totalSale property.
     * 
     */
    public double getTotalSale() {
        return totalSale;
    }

    /**
     * Sets the value of the totalSale property.
     * 
     */
    public void setTotalSale(double value) {
        this.totalSale = value;
    }

    /**
     * Gets the value of the orderLines property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDtcOrderLine }
     *     
     */
    public ArrayOfDtcOrderLine getOrderLines() {
        return orderLines;
    }

    /**
     * Sets the value of the orderLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDtcOrderLine }
     *     
     */
    public void setOrderLines(ArrayOfDtcOrderLine value) {
        this.orderLines = value;
    }

}
