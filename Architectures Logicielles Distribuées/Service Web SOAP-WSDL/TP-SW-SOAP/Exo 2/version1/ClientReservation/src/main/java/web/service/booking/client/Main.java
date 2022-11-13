package web.service.booking.client;

import web.service.booking.BookingService;
import web.service.booking.BookingServiceImplementationService;
import web.service.booking.Hotel;
import web.service.booking.Room;

import java.net.MalformedURLException;
import java.net.URL;


public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/bookingservice?wsdl");
            BookingServiceImplementationService serviceImplementationService = new BookingServiceImplementationService(url);
            BookingService proxy = serviceImplementationService.getBookingServiceImplementationPort();
            for (Hotel hotel : proxy.allHotels()) {
                System.out.println( hotel.getName() +  " "+hotel.getCity()+" "+hotel.getStars()+ " \u272D ");
for (Room room : hotel.getAllRoom()) {
    System.out.println( "\tRoom No: "+room.getRoomNumber()+" Capacity: " + room.getNumberBed() +" beds "+"Price: "+room.getPrice()+" $");
}
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}