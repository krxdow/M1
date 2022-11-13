
package web.service.booking;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the web.service.booking package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AllHotels_QNAME = new QName("http://services.booking.service.web/", "allHotels");
    private final static QName _AllHotelsResponse_QNAME = new QName("http://services.booking.service.web/", "allHotelsResponse");
    private final static QName _MakeBooking_QNAME = new QName("http://services.booking.service.web/", "makeBooking");
    private final static QName _MakeBookingResponse_QNAME = new QName("http://services.booking.service.web/", "makeBookingResponse");
    private final static QName _SearchHotel_QNAME = new QName("http://services.booking.service.web/", "searchHotel");
    private final static QName _SearchHotelResponse_QNAME = new QName("http://services.booking.service.web/", "searchHotelResponse");
    private final static QName _SearchRoom_QNAME = new QName("http://services.booking.service.web/", "searchRoom");
    private final static QName _SearchRoomResponse_QNAME = new QName("http://services.booking.service.web/", "searchRoomResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: web.service.booking
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AllHotels }
     * 
     */
    public AllHotels createAllHotels() {
        return new AllHotels();
    }

    /**
     * Create an instance of {@link AllHotelsResponse }
     * 
     */
    public AllHotelsResponse createAllHotelsResponse() {
        return new AllHotelsResponse();
    }

    /**
     * Create an instance of {@link MakeBooking }
     * 
     */
    public MakeBooking createMakeBooking() {
        return new MakeBooking();
    }

    /**
     * Create an instance of {@link MakeBookingResponse }
     * 
     */
    public MakeBookingResponse createMakeBookingResponse() {
        return new MakeBookingResponse();
    }

    /**
     * Create an instance of {@link SearchHotel }
     * 
     */
    public SearchHotel createSearchHotel() {
        return new SearchHotel();
    }

    /**
     * Create an instance of {@link SearchHotelResponse }
     * 
     */
    public SearchHotelResponse createSearchHotelResponse() {
        return new SearchHotelResponse();
    }

    /**
     * Create an instance of {@link SearchRoom }
     * 
     */
    public SearchRoom createSearchRoom() {
        return new SearchRoom();
    }

    /**
     * Create an instance of {@link SearchRoomResponse }
     * 
     */
    public SearchRoomResponse createSearchRoomResponse() {
        return new SearchRoomResponse();
    }

    /**
     * Create an instance of {@link Hotel }
     * 
     */
    public Hotel createHotel() {
        return new Hotel();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AllHotels }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AllHotels }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "allHotels")
    public JAXBElement<AllHotels> createAllHotels(AllHotels value) {
        return new JAXBElement<AllHotels>(_AllHotels_QNAME, AllHotels.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AllHotelsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AllHotelsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "allHotelsResponse")
    public JAXBElement<AllHotelsResponse> createAllHotelsResponse(AllHotelsResponse value) {
        return new JAXBElement<AllHotelsResponse>(_AllHotelsResponse_QNAME, AllHotelsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeBooking }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeBooking }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "makeBooking")
    public JAXBElement<MakeBooking> createMakeBooking(MakeBooking value) {
        return new JAXBElement<MakeBooking>(_MakeBooking_QNAME, MakeBooking.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeBookingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeBookingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "makeBookingResponse")
    public JAXBElement<MakeBookingResponse> createMakeBookingResponse(MakeBookingResponse value) {
        return new JAXBElement<MakeBookingResponse>(_MakeBookingResponse_QNAME, MakeBookingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchHotel }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchHotel }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "searchHotel")
    public JAXBElement<SearchHotel> createSearchHotel(SearchHotel value) {
        return new JAXBElement<SearchHotel>(_SearchHotel_QNAME, SearchHotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchHotelResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchHotelResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "searchHotelResponse")
    public JAXBElement<SearchHotelResponse> createSearchHotelResponse(SearchHotelResponse value) {
        return new JAXBElement<SearchHotelResponse>(_SearchHotelResponse_QNAME, SearchHotelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRoom }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchRoom }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "searchRoom")
    public JAXBElement<SearchRoom> createSearchRoom(SearchRoom value) {
        return new JAXBElement<SearchRoom>(_SearchRoom_QNAME, SearchRoom.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRoomResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchRoomResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "searchRoomResponse")
    public JAXBElement<SearchRoomResponse> createSearchRoomResponse(SearchRoomResponse value) {
        return new JAXBElement<SearchRoomResponse>(_SearchRoomResponse_QNAME, SearchRoomResponse.class, null, value);
    }

}
