package com.ws.shipcomplianceAPI;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.ws.shipcompliance.Address;
import org.ws.shipcompliance.AddressOption;
import org.ws.shipcompliance.ArrayOfShipment;
import org.ws.shipcompliance.ArrayOfShipmentItem;
import org.ws.shipcompliance.ArrayOfTag;
import org.ws.shipcompliance.CheckComplianceOfSalesOrderRequest;
import org.ws.shipcompliance.CheckComplianceOfSalesOrderResponse2;
import org.ws.shipcompliance.OrderType;
import org.ws.shipcompliance.SalesOrder;
import org.ws.shipcompliance.SalesOrderService;
import org.ws.shipcompliance.Security;
import org.ws.shipcompliance.Shipment;
import org.ws.shipcompliance.ShipmentItem;
import org.ws.shipcompliance.ShipmentStatus;
import org.ws.shipcompliance.Tag;



public class ShipComplianceAPI {

	public static void main(String[] args) {

		CheckComplianceOfSalesOrderRequest order = populateOrder();
		SalesOrderService service = new SalesOrderService();
		CheckComplianceOfSalesOrderResponse2 response = service
				.getSalesOrderServiceSoap().checkComplianceOfSalesOrder(order);
		System.out.println(response.getResponseStatus());

	}

	private static CheckComplianceOfSalesOrderRequest populateOrder() {

		CheckComplianceOfSalesOrderRequest salesOrderRequest = new CheckComplianceOfSalesOrderRequest();

		AddressOption addressOption = new AddressOption();
		addressOption.setIgnoreStreetLevelErrors(true);
		addressOption.setRejectIfAddressSuggested(false);

		SalesOrder salesOrder = new SalesOrder();

		Address address = new Address();
		address.setCity("Highland Park");
		address.setFirstName("James");
		address.setLastName("Addams");
		address.setState("TX");
		address.setStreet1("4321 Edmondson Ave.");
		address.setZip1("75205");

		salesOrder.setBillTo(address);
		salesOrder.setCustomerKey("11948613");
		salesOrder.setFulfillmentType(null);
		salesOrder.setOrderType(OrderType.INTERNET);

		Date date = null;
		XMLGregorianCalendar gregorianCalendar = null;
		GregorianCalendar calender = new GregorianCalendar();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse("2014-06-10");
			calender.setTime(date);
			gregorianCalendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(calender);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		salesOrder.setPurchaseDate(gregorianCalendar);
		salesOrder.setSalesOrderKey("WA-11112277");
		salesOrder.setSalesTaxCollected(new BigDecimal(0));

		Shipment shipment = new Shipment();
		shipment.setShipping(0d);
		shipment.setLicenseRelationship("Default");

		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse("2014-06-17");
			calender.setTime(date);
			gregorianCalendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(calender);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shipment.setShipDate(gregorianCalendar);

		ShipmentItem shipmentItem = new ShipmentItem();
		shipmentItem.setBrandKey("NW");
		shipmentItem.setProductKey("10863397");
		shipmentItem.setProductQuantity(1.0);
		shipmentItem.setProductUnitPrice(27.66);

		ArrayOfShipmentItem arrayOfShipmentItem = new ArrayOfShipmentItem();
		arrayOfShipmentItem.getShipmentItem().add(shipmentItem);

		shipment.setShipmentItems(arrayOfShipmentItem);
		shipment.setShipmentKey("1");
		shipment.setShipmentStatus(ShipmentStatus.PAYMENT_ACCEPTED);
		shipment.setShippingService("UPS");

		Address shipmentAddress = new Address();
		shipmentAddress.setCity("Dallas");
		shipmentAddress.setFirstName("James");
		shipmentAddress.setLastName("Addams");
		shipmentAddress.setState("TX");
		shipmentAddress.setStreet1("4321 Edmondson Ave.");
		shipmentAddress.setZip1("75205");

		shipment.setShipTo(shipmentAddress);

		Tag tag = new Tag();
		tag.setName("offer_id=10079485");

		ArrayOfShipment arrayOfShipment = new ArrayOfShipment();
		arrayOfShipment.getShipment().add(shipment);
		salesOrder.setShipments(arrayOfShipment);

		ArrayOfTag arrayOfTag = new ArrayOfTag();
		arrayOfTag.getTag().add(tag);
		salesOrder.setTags(arrayOfTag);

		Security security = new Security();
		security.setUsername("shipcompliantws@wineaccess.com");
		security.setPassword("Access8");
		security.setPartnerKey("7F93C976-20C5-4682-A75E-4F9A6C85C38B");

		salesOrderRequest.setSalesOrder(salesOrder);
		salesOrderRequest.setSecurity(security);

		return salesOrderRequest;
	}

}
