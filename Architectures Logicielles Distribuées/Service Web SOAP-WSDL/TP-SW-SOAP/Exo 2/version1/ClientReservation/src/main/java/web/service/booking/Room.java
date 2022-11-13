
package web.service.booking;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for room complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="room"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="availbale" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="numberBed" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="roomNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "room", propOrder = {
    "availbale",
    "numberBed",
    "price",
    "roomNumber"
})
public class Room {

    protected boolean availbale;
    protected int numberBed;
    protected double price;
    protected int roomNumber;

    /**
     * Gets the value of the availbale property.
     * 
     */
    public boolean isAvailbale() {
        return availbale;
    }

    /**
     * Sets the value of the availbale property.
     * 
     */
    public void setAvailbale(boolean value) {
        this.availbale = value;
    }

    /**
     * Gets the value of the numberBed property.
     * 
     */
    public int getNumberBed() {
        return numberBed;
    }

    /**
     * Sets the value of the numberBed property.
     * 
     */
    public void setNumberBed(int value) {
        this.numberBed = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the roomNumber property.
     * 
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the value of the roomNumber property.
     * 
     */
    public void setRoomNumber(int value) {
        this.roomNumber = value;
    }

}
