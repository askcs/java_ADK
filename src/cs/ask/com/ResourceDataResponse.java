
package cs.ask.com;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resourceDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resourceDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="delay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="result" type="{urn:com.ask-cs}resourceData"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resourceDataResponse", propOrder = {

})
public class ResourceDataResponse {

    protected int error;
    protected int delay;
    @XmlElement(required = true)
    protected ResourceData result;

    /**
     * Gets the value of the error property.
     * 
     */
    public int getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     */
    public void setError(int value) {
        this.error = value;
    }

    /**
     * Gets the value of the delay property.
     * 
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Sets the value of the delay property.
     * 
     */
    public void setDelay(int value) {
        this.delay = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceData }
     *     
     */
    public ResourceData getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceData }
     *     
     */
    public void setResult(ResourceData value) {
        this.result = value;
    }

}
