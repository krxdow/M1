package web.service.booking.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hotel {
    private String name;
    private float stars;
    private String country;
    private String city;
    private String street;
    private int streetNumber;
    private String gpsPosition;

    private List<Room> hotelRooms;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getStars() {
        return this.stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getGpsPosition() {
        return this.gpsPosition;
    }

    public void setGpsPosition(String gpsPosition) {
        this.gpsPosition = gpsPosition;
    }

//    public Hotel(String name, float stars, String country, String city, String street, int streetNumber) {
//        this.name = name;
//        this.stars = stars;
//        this.country = country;
//        this.city = city;
//        this.street = street;
//        this.streetNumber = streetNumber;
//    }


    public Hotel() {
    }

    public Hotel(String name, float stars, String country, String city, String street, int streetNumber, String gpsPosition, List<Room> hotelRooms) {
        this.name = name;
        this.stars = stars;
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.gpsPosition = gpsPosition;
        this.hotelRooms = hotelRooms;
    }

    List<Room> allRoom = new ArrayList<Room>();
    List<Booking> bookings = new ArrayList<Booking>();
List<Room> availalbleRooms = new ArrayList<Room>();

List<Room> getAvailalbleRooms(List<Room> rooms) {
    List<Room> currentAvailableRooms = new ArrayList<Room>();
    for (Room room : rooms) {
        if (room.isAvailbale()){
            currentAvailableRooms.add(room);
        }
    }
    return currentAvailableRooms;
}

Float getCurrentHotelMinPrice(List<Room> rooms) {
    float currentHotelRoomMinPrice = rooms.get(1).getPrice();
    float currentHotelRoomMaxPrice = rooms.get(1).getPrice();
    for (Room room : rooms) {
        if (currentHotelRoomMinPrice> room.getPrice()){
           currentHotelRoomMinPrice = room.getPrice();
        }
    }
    return currentHotelRoomMinPrice;
}

    Float getHotelMinMaxPrice(List<Room> rooms) {
        float currentHotelRoomMaxPrice = rooms.get(1).getPrice();
        for (Room room : rooms) {
            if (currentHotelRoomMaxPrice < room.getPrice()){
                currentHotelRoomMaxPrice = room.getPrice();
            }
        }
        return currentHotelRoomMaxPrice;
    }

//public List<Room> suggestRoomAvailable() {
////    Optional<Room> suggestedRooms =
//}
public  boolean receiveBooking() {
    return true;
}


}
