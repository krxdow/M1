package web.service.booking.models;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String name;
    private Double stars;
    private String country;
    private String city;
    private String street;
    private int streetNumber;
    private String gpsPosition;
    private List<Room> hotelRooms;


    public Hotel() {
    }

    public Hotel(String name, Double stars, String city, String street, int streetNumber) {
        this.name = name;
        this.stars = stars;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
//        this.hotelRooms = hotelRooms;
    }


    public String getName() {
        return this.name;
    }
    public   void setName(String name) {
        this.name = name;
    }

    public double getStars() {
        return this.stars;
    }

    public void setStars(double stars) {
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
//    public String getName() {
//        return this.name;
//    }

    public List<Room> getAllRoom() {
        return this.allRoom;
    }

    public List<Room> getAvailalbleRooms() {
        return availalbleRooms;
    }

  public   List<Room> allRoom = new ArrayList<Room>();



    List<Booking> bookings = new ArrayList<Booking>();
public  List<Room> availalbleRooms = new ArrayList<Room>();

    public void addRoom(int roomNumber, int numberBed, float price, boolean available) {
        allRoom.add(new Room(roomNumber, numberBed, price, available));
    }

    public List<Room> getAvailalbleRooms(List<Room> rooms) {
    List<Room> currentAvailableRooms = new ArrayList<Room>();
    for (Room room : rooms) {
        if (room.isAvailbale()){
            currentAvailableRooms.add(room);
        }
    }
    return currentAvailableRooms;
}

double getCurrentHotelMinPrice(List<Room> rooms) {
    double currentHotelRoomMinPrice = rooms.get(1).getPrice();
    double currentHotelRoomMaxPrice = rooms.get(1).getPrice();
    for (Room room : rooms) {
        if (currentHotelRoomMinPrice> room.getPrice()){
           currentHotelRoomMinPrice = room.getPrice();
        }
    }
    return currentHotelRoomMinPrice;
}

    public double getHotelMinMaxPrice(List<Room> rooms) {
        double currentHotelRoomMaxPrice = rooms.get(1).getPrice();
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
