package web.service.booking.services;

//import jakarta.jws.WebService;
//import jakarta.xml.ws.Endpoint;
//import jakarta.xml.ws.EndpointReference;
import web.service.booking.models.Hotel;
import web.service.booking.models.Room;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@WebService(endpointInterface = "web.service.booking.services.BookingService")
public class BookingServiceImplementation implements BookingService{

    List<Hotel> availableHotels;

    public BookingServiceImplementation() {
availableHotels = new ArrayList<>();
availableHotels.addAll(Arrays.asList(
        new Hotel("Donald", 3.3,  "Paris", "Versailles", 91 ),
        new Hotel("Cote d'azur", 5.0,  "Madrid", "Chanel", 252 ),
        new Hotel("Radisson", 4.5,  "Lyon", "urbanville", 75 ),
        new Hotel("Goyal", 4.3,  "Mumbai", "panchakh", 20 )
));
availableHotels.get(0).addRoom(1, 1,15, true);
        availableHotels.get(0).addRoom(5, 2,35, true);
        availableHotels.get(1).addRoom(105, 1,10, true);
        availableHotels.get(1).addRoom(20, 2,15, true);
//        availableHotels.get(0).availalbleRooms.get(0).
    }



    //    @Override
    public List<Hotel> searchHotel(String city, float minStars, float minPrice, float maxPrice, Date arrivalDate, Date departureDate) {
        return availableHotels;
    }

    @Override
    public List<Hotel> allHotels() {
        return availableHotels;
    }

    @Override
    public List<Room> searchRoom(Hotel tempHotel) {
        return tempHotel.getAvailalbleRooms();
    }

//    @Override
    public String makeBooking(String FirstName, String LastName, String cardNumber, List<Room> chosenRooms) {
        return null;
    }

}
