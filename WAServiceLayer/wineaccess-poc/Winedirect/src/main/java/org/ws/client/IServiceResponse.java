
package org.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IServiceResponse")
@XmlSeeAlso({
    CheckSystemStatusResponse2 .class,
    CancelOrderResponse2 .class,
    GetShipmentUpdatesResponse2 .class,
    GetInventoryResponse2 .class,
    GetShipmentsResponse2 .class,
    ValidateAddressResponse2 .class,
    GetProductsResponse2 .class,
    SubmitOrdersResponse2 .class,
    GetOrderStatusResponse2 .class
})
public class IServiceResponse {


}
