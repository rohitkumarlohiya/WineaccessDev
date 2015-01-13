
package org.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetShipmentUpdatesRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetShipmentUpdatesRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthenticationInfo" type="{http://winedirect.com/4/0/dtc/}AuthenticationInfo" minOccurs="0"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "GetShipmentUpdatesRequest", propOrder = {
    "authenticationInfo",
    "accountNumber",
    "searchStartTime",
    "searchEndTime",
    "startingContainerId"
})
public class GetShipmentUpdatesRequest {

    @XmlElement(name = "AuthenticationInfo")
    protected AuthenticationInfo authenticationInfo;
    @XmlElement(name = "AccountNumber")
    protected String accountNumber;
    @XmlElement(name = "SearchStartTime")
    protected String searchStartTime;
    @XmlElement(name = "SearchEndTime")
    protected String searchEndTime;
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
     * Gets the value of the searchStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchStartTime() {
        return searchStartTime;
    }

    /**
     * Sets the value of the searchStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchStartTime(String value) {
        this.searchStartTime = value;
    }

    /**
     * Gets the value of the searchEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchEndTime() {
        return searchEndTime;
    }

    /**
     * Sets the value of the searchEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchEndTime(String value) {
        this.searchEndTime = value;
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
