
package cs.ask.com;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for jobData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="jobData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="interval" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="params" type="{urn:com.ask-cs}tupleArray"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "jobData", propOrder = {

})
public class JobData {

    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String desc;
    protected int interval;
    @XmlElement(required = true)
    protected TupleArray params;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the interval property.
     * 
     */
    public int getInterval() {
        return interval;
    }

    /**
     * Sets the value of the interval property.
     * 
     */
    public void setInterval(int value) {
        this.interval = value;
    }

    /**
     * Gets the value of the params property.
     * 
     * @return
     *     possible object is
     *     {@link TupleArray }
     *     
     */
    public TupleArray getParams() {
        return params;
    }

    /**
     * Sets the value of the params property.
     * 
     * @param value
     *     allowed object is
     *     {@link TupleArray }
     *     
     */
    public void setParams(TupleArray value) {
        this.params = value;
    }

}
