
package org.winedirect;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * WineDirect Web Services for the Direct-to-Consumer (DTC) Fulfillment System
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "dtc", targetNamespace = "http://winedirect.com/4/0/dtc/", wsdlLocation = "http://webservices-test.winedirect.com/4/0/dtc.asmx?WSDL")
public class Dtc
    extends Service
{

    private final static URL DTC_WSDL_LOCATION;
    private final static WebServiceException DTC_EXCEPTION;
    private final static QName DTC_QNAME = new QName("http://winedirect.com/4/0/dtc/", "dtc");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://webservices-test.winedirect.com/4/0/dtc.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DTC_WSDL_LOCATION = url;
        DTC_EXCEPTION = e;
    }

    public Dtc() {
        super(__getWsdlLocation(), DTC_QNAME);
    }

    public Dtc(WebServiceFeature... features) {
        super(__getWsdlLocation(), DTC_QNAME, features);
    }

    public Dtc(URL wsdlLocation) {
        super(wsdlLocation, DTC_QNAME);
    }

    public Dtc(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DTC_QNAME, features);
    }

    public Dtc(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Dtc(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DtcSoap
     */
    @WebEndpoint(name = "dtcSoap")
    public DtcSoap getDtcSoap() {
        return super.getPort(new QName("http://winedirect.com/4/0/dtc/", "dtcSoap"), DtcSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DtcSoap
     */
    @WebEndpoint(name = "dtcSoap")
    public DtcSoap getDtcSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://winedirect.com/4/0/dtc/", "dtcSoap"), DtcSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DTC_EXCEPTION!= null) {
            throw DTC_EXCEPTION;
        }
        return DTC_WSDL_LOCATION;
    }

}
