
package web.service.booking;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hotel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hotel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="allRoom" type="{http://services.booking.service.web/}room" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="availalbleRooms" type="{http://services.booking.service.web/}room" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gpsPosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stars" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="street" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="streetNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hotel", propOrder = {
    "allRoom",
    "availalbleRooms",
    "city",
    "country",
    "gpsPosition",
    "name",
    "stars",
    "street",
    "streetNumber"
})
public class Hotel {

    @XmlElement(nillable = true)
    protected List<Room> allRoom;
    @XmlElement(nillable = true)
    protected List<Room> availalbleRooms;
    protected String city;
    protected String country;
    protected String gpsPosition;
    protected String name;
    protected double stars;
    protected String street;
    protected int streetNumber;

    /**
     * Gets the value of the allRoom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allRoom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllRoom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Room }
     * 
     * 
     */
    public List<Room> getAllRoom() {
        if (allRoom == null) {
            allRoom = new ArrayList<Room>();
        }
        return this.allRoom;
    }

    /**
     * Gets the value of the availalbleRooms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the availalbleRooms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAvailalbleRooms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Room }
     * 
     * 
     */
    public List<Room> getAvailalbleRooms() {
        if (availalbleRooms == null) {
            availalbleRooms = new ArrayList<Room>();
        }
        return this.availalbleRooms;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the gpsPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGpsPosition() {
        return gpsPosition;
    }

    /**
     * Sets the value of the gpsPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGpsPosition(String value) {
        this.gpsPosition = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the stars property.
     * 
     */
    public double getStars() {
        return stars;
    }

    /**
     * Sets the value of the stars property.
     * 
     */
    public void setStars(double value) {
        this.stars = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the streetNumber property.
     * 
     */
    public int getStreetNumber() {
        return streetNumber;
    }

    /**
     * Sets the value of the streetNumber property.
     * 
     */
    public void setStreetNumber(int value) {
        this.streetNumber = value;
    }

}
