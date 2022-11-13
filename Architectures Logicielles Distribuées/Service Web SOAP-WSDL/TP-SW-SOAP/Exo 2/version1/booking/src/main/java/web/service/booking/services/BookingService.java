package web.service.booking.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import web.service.booking.models.Hotel;
import web.service.booking.models.Room;

import java.util.List;

@WebService
public interface BookingService {

    @WebMethod
List<Hotel> searchHotel() ;

    @WebMethod
    List<Room> searchRoom();

    @WebMethod
String makeBooking();
}
