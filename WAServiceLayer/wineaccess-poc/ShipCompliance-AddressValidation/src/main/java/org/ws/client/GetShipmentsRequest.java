
package org.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetShipmentsRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetShipmentsRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthenticationInfo" type="{http://winedirect.com/4/0/dtc/}AuthenticationInfo" minOccurs="0"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchFromShipDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchToShipDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StateOrProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StatusCode" type="{http://winedirect.com/4/0/dtc/}DtcShipmentStatusEnum"/>
 *         &lt;element name="StartingContainerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetShipmentsRequest", propOrder = {
    "authenticationInfo",
    "accountNumber",
    "searchFromShipDate",
    "searchToShipDate",
    "stateOrProvince",
    "statusCode",
    "startingContainerId"
})
public class GetShipmentsRequest {

    @XmlElement(name = "AuthenticationInfo")
    protected AuthenticationInfo authenticationInfo;
    @XmlElement(name = "AccountNumber")
    protected String accountNumber;
    @XmlElement(name = "SearchFromShipDate")
    protected String searchFromShipDate;
    @XmlElement(name = "SearchToShipDate")
    protected String searchToShipDate;
    @XmlElement(name = "StateOrProvince")
    protected String stateOrProvince;
    @XmlElement(name = "StatusCode", required = true)
    protected DtcShipmentStatusEnum statusCode;
    @XmlElement(name = "StartingContainerId")
    protected String startingContainerId;

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
     * Gets the value of the searchFromShipDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchFromShipDate() {
        return searchFromShipDate;
    }

    /**
     * Sets the value of the searchFromShipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchFromShipDate(String value) {
        this.searchFromShipDate = value;
    }

    /**
     * Gets the value of the searchToShipDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchToShipDate() {
        return searchToShipDate;
    }

    /**
     * Sets the value of the searchToShipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchToShipDate(String value) {
        this.searchToShipDate = value;
    }

    /**
     * Gets the value of the stateOrProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateOrProvince() {
        return stateOrProvince;
    }

    /**
     * Sets the value of the stateOrProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateOrProvince(String value) {
        this.stateOrProvince = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link DtcShipmentStatusEnum }
     *     
     */
    public DtcShipmentStatusEnum getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtcShipmentStatusEnum }
     *     
     */
    public void setStatusCode(DtcShipmentStatusEnum value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the startingContainerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartingContainerId() {
        return startingContainerId;
    }

    /**
     * Sets the value of the startingContainerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartingContainerId(String value) {
        this.startingContainerId = value;
    }

}
