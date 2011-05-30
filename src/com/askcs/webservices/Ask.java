
package com.askcs.webservices;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "Ask", targetNamespace = "urn:webservices.askcs.com", wsdlLocation = "http://ask-dev.customers.luna.net/~erik/trunk/webservices/index.php?wsdl")
public class Ask
    extends Service
{

    private final static URL ASK_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.askcs.webservices.Ask.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.askcs.webservices.Ask.class.getResource(".");
            url = new URL(baseUrl, "http://ask-dev.customers.luna.net/~erik/trunk/webservices/index.php?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://ask-dev.customers.luna.net/~erik/trunk/webservices/index.php?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        ASK_WSDL_LOCATION = url;
    }

    public Ask(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Ask() {
        super(ASK_WSDL_LOCATION, new QName("urn:webservices.askcs.com", "Ask"));
    }

    /**
     * 
     * @return
     *     returns AskPortType
     */
    @WebEndpoint(name = "AskPort")
    public AskPortType getAskPort() {
    	return super.getPort(new QName("urn:webservices.askcs.com", "AskPort"), AskPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AskPortType
     */
    @WebEndpoint(name = "AskPort")
    public AskPortType getAskPort(WebServiceFeature... features) {
        return super.getPort(new QName("urn:webservices.askcs.com", "AskPort"), AskPortType.class, features);
    }

}
