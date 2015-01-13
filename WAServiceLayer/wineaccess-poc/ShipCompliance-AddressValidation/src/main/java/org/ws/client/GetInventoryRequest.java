
package org.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetInventoryRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetInventoryRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthenticationInfo" type="{http://winedirect.com/4/0/dtc/}AuthenticationInfo" minOccurs="0"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WarehouseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubinventoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartingSKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AvailableOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetInventoryRequest", propOrder = {
    "authenticationInfo",
    "accountNumber",
    "warehouseCode",
    "subinventoryCode",
    "sku",
    "startingSKU",
    "availableOnly"
})
public class GetInventoryRequest {

    @XmlElement(name = "AuthenticationInfo")
    protected AuthenticationInfo authenticationInfo;
    @XmlElement(name = "AccountNumber")
    protected String accountNumber;
    @XmlElement(name = "WarehouseCode")
    protected String warehouseCode;
    @XmlElement(name = "SubinventoryCode")
    protected String subinventoryCode;
    @XmlElement(name = "SKU")
    protected String sku;
    @XmlElement(name = "StartingSKU")
    protected String startingSKU;
    @XmlElement(name = "AvailableOnly")
    protected boolean availableOnly;

    /**
     * Gets the value of the authenticationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationInfo }
     *     
     */
    public AuthenticationInfo getAuthenticationInfo() {
        return authenticationInfo;
    }

    /**
     * Sets the value of the authenticationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationInfo }
     *     
     */
    public void setAuthenticationInfo(AuthenticationInfo value) {
        this.authenticationInfo = value;
    }

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
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
     * Gets the value of the startingSKU property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartingSKU() {
        return startingSKU;
    }

    /**
     * Sets the value of the startingSKU property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartingSKU(String value) {
        this.startingSKU = value;
    }

    /**
     * Gets the value of the availableOnly property.
     * 
     */
    public boolean isAvailableOnly() {
        return availableOnly;
    }

    /**
     * Sets the value of the availableOnly property.
     * 
     */
    public void setAvailableOnly(boolean value) {
        this.availableOnly = value;
    }

}
