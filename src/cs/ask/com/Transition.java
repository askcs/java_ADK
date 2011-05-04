
package cs.ask.com;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="nextJobUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="overlap" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transition", propOrder = {

})
public class Transition {

    @XmlElement(required = true)
    protected String nextJobUUID;
    protected int length;
    protected int overlap;

    /**
     * Gets the value of the nextJobUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextJobUUID() {
        return nextJobUUID;
    }

    /**
     * Sets the value of the nextJobUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextJobUUID(String value) {
        this.nextJobUUID = value;
    }

    /**
     * Gets the value of the length property.
     * 
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     */
    public void setLength(int value) {
        this.length = value;
    }

    /**
     * Gets the value of the overlap property.
     * 
     */
    public int getOverlap() {
        return overlap;
    }

    /**
     * Sets the value of the overlap property.
     * 
     */
    public void setOverlap(int value) {
        this.overlap = value;
    }

}
