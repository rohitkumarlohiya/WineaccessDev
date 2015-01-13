
package org.winedirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetOrderStatusRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetOrderStatusRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthenticationInfo" type="{http://winedirect.com/4/0/dtc/}AuthenticationInfo" minOccurs="0"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchByBatchIdSpecified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="BatchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchByOrderIdsSpecified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="OrderIds" type="{http://winedirect.com/4/0/dtc/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="SearchByStatusSpecified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchByReferenceSpecified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Reference1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reference2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reference3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartingOrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetOrderStatusRequest", propOrder = {
    "authenticationInfo",
    "accountNumber",
    "searchByBatchIdSpecified",
    "batchId",
    "searchByOrderIdsSpecified",
    "orderIds",
    "searchByStatusSpecified",
    "status",
    "searchByReferenceSpecified",
    "reference1",
    "reference2",
    "reference3",
    "startingOrderId"
})
public class GetOrderStatusRequest {

    @XmlElement(name = "AuthenticationInfo")
    protected AuthenticationInfo authenticationInfo;
    @XmlElement(name = "AccountNumber")
    protected String accountNumber;
    @XmlElement(name = "SearchByBatchIdSpecified")
    protected boolean searchByBatchIdSpecified;
    @XmlElement(name = "BatchId")
    protected String batchId;
    @XmlElement(name = "SearchByOrderIdsSpecified")
    protected boolean searchByOrderIdsSpecified;
    @XmlElement(name = "OrderIds")
    protected ArrayOfString orderIds;
    @XmlElement(name = "SearchByStatusSpecified")
    protected boolean searchByStatusSpecified;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "SearchByReferenceSpecified")
    protected boolean searchByReferenceSpecified;
    @XmlElement(name = "Reference1")
    protected String reference1;
    @XmlElement(name = "Reference2")
    protected String reference2;
    @XmlElement(name = "Reference3")
    protected String reference3;
    @XmlElement(name = "StartingOrderId")
    protected String startingOrderId;

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
     * Gets the value of the searchByBatchIdSpecified property.
     * 
     */
    public boolean isSearchByBatchIdSpecified() {
        return searchByBatchIdSpecified;
    }

    /**
     * Sets the value of the searchByBatchIdSpecified property.
     * 
     */
    public void setSearchByBatchIdSpecified(boolean value) {
        this.searchByBatchIdSpecified = value;
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
     * Gets the value of the searchByOrderIdsSpecified property.
     * 
     */
    public boolean isSearchByOrderIdsSpecified() {
        return searchByOrderIdsSpecified;
    }

    /**
     * Sets the value of the searchByOrderIdsSpecified property.
     * 
     */
    public void setSearchByOrderIdsSpecified(boolean value) {
        this.searchByOrderIdsSpecified = value;
    }

    /**
     * Gets the value of the orderIds property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getOrderIds() {
        return orderIds;
    }

    /**
     * Sets the value of the orderIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setOrderIds(ArrayOfString value) {
        this.orderIds = value;
    }

    /**
     * Gets the value of the searchByStatusSpecified property.
     * 
     */
    public boolean isSearchByStatusSpecified() {
        return searchByStatusSpecified;
    }

    /**
     * Sets the value of the searchByStatusSpecified property.
     * 
     */
    public void setSearchByStatusSpecified(boolean value) {
        this.searchByStatusSpecified = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the searchByReferenceSpecified property.
     * 
     */
    public boolean isSearchByReferenceSpecified() {
        return searchByReferenceSpecified;
    }

    /**
     * Sets the value of the searchByReferenceSpecified property.
     * 
     */
    public void setSearchByReferenceSpecified(boolean value) {
        this.searchByReferenceSpecified = value;
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
     * Gets the value of the startingOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartingOrderId() {
        return startingOrderId;
    }

    /**
     * Sets the value of the startingOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartingOrderId(String value) {
        this.startingOrderId = value;
    }

}
