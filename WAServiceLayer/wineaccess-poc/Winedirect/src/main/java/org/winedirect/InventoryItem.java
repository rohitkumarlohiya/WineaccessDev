
package org.winedirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InventoryItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InventoryItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WarehouseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubinventoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="QuantityOnHand" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="QuantityOnOrders" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="QuantityAvailable" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InventoryItem", propOrder = {
    "warehouseCode",
    "subinventoryCode",
    "sku",
    "quantityOnHand",
    "quantityOnOrders",
    "quantityAvailable"
})
public class InventoryItem {

    @XmlElement(name = "WarehouseCode")
    protected String warehouseCode;
    @XmlElement(name = "SubinventoryCode")
    protected String subinventoryCode;
    @XmlElement(name = "SKU")
    protected String sku;
    @XmlElement(name = "QuantityOnHand")
    protected int quantityOnHand;
    @XmlElement(name = "QuantityOnOrders")
    protected int quantityOnOrders;
    @XmlElement(name = "QuantityAvailable")
    protected int quantityAvailable;

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
     * Gets the value of the sku property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSKU() {
        return sku;
    }

    /**
     * Sets the value of the sku property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSKU(String value) {
        this.sku = value;
    }

    /**
     * Gets the value of the quantityOnHand property.
     * 
     */
    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    /**
     * Sets the value of the quantityOnHand property.
     * 
     */
    public void setQuantityOnHand(int value) {
        this.quantityOnHand = value;
    }

    /**
     * Gets the value of the quantityOnOrders property.
     * 
     */
    public int getQuantityOnOrders() {
        return quantityOnOrders;
    }

    /**
     * Sets the value of the quantityOnOrders property.
     * 
     */
    public void setQuantityOnOrders(int value) {
        this.quantityOnOrders = value;
    }

    /**
     * Gets the value of the quantityAvailable property.
     * 
     */
    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    /**
     * Sets the value of the quantityAvailable property.
     * 
     */
    public void setQuantityAvailable(int value) {
        this.quantityAvailable = value;
    }

}
