package web.service.booking.server;

//import jakarta.xml.ws.Endpoint;
import web.service.booking.services.BookingServiceImplementation;

import javax.xml.ws.Endpoint;

public class PubliserServer {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/bookingservice", new BookingServiceImplementation());
        System.err.println("BookingService Published");
    }
}
