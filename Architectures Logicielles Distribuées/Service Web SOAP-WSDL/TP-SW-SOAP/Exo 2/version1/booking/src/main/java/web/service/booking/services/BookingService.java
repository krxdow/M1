package web.service.booking.services;

//import jakarta.jws.WebMethod;
//import jakarta.jws.WebService;
import web.service.booking.models.Hotel;
import web.service.booking.models.Room;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService
public interface BookingService {

    @WebMethod
List<Hotel> searchHotel(String city, float minStars, float minPrice, float maxPrice, Date arrivalDate, Date departureDate) ;

    @WebMethod
    List<Hotel> allHotels();
    @WebMethod
    List<Room> searchRoom(Hotel tempHotel);

    @WebMethod
String makeBooking(String FirstName, String LastName, String cardNumber, List<Room> chosenRooms);
}
