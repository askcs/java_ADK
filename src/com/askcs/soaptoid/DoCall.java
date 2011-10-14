
package com.askcs.soaptoid;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iSessionID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="question" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "iSessionID",
    "question"
})
@XmlRootElement(name = "doCall", namespace = "urn:soaptoid")
public class DoCall {

    @XmlElementRef(name = "iSessionID", namespace = "urn:soaptoid", type = JAXBElement.class)
    protected JAXBElement<java.lang.Integer> iSessionID;
    @XmlElementRef(name = "question", namespace = "urn:soaptoid", type = JAXBElement.class)
    protected JAXBElement<java.lang.String> question;

    /**
     * Gets the value of the iSessionID property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link java.lang.Integer }{@code >}
     *     
     */
    public JAXBElement<java.lang.Integer> getISessionID() {
        return iSessionID;
    }

    /**
     * Sets the value of the iSessionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link java.lang.Integer }{@code >}
     *     
     */
    public void setISessionID(JAXBElement<java.lang.Integer> value) {
        this.iSessionID = ((JAXBElement<java.lang.Integer> ) value);
    }

    /**
     * Gets the value of the question property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link java.lang.String }{@code >}
     *     
     */
    public JAXBElement<java.lang.String> getQuestion() {
        return question;
    }

    /**
     * Sets the value of the question property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link java.lang.String }{@code >}
     *     
     */
    public void setQuestion(JAXBElement<java.lang.String> value) {
        this.question = ((JAXBElement<java.lang.String> ) value);
    }

}
