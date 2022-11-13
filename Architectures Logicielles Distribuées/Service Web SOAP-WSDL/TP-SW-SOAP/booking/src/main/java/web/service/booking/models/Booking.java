package web.service.booking.models;

import java.util.Date;

public class Booking {

    private String clientBookingLastName;
    private String clientBookingFirstName;
    private PaymentCard bookingPaymentCard;
    private Date arrivalDate;
    private Date checkoutDate;
    private int numberPersons;

    public Booking() {
    }

    public Booking(String clientBookingLastName, String clientBookingFirstName, PaymentCard bookingPaymentCard, Date arrivalDate, Date checkoutDate, int numberPersons) {
        this.clientBookingLastName = clientBookingLastName;
        this.clientBookingFirstName = clientBookingFirstName;
        this.bookingPaymentCard = bookingPaymentCard;
        this.arrivalDate = arrivalDate;
        this.checkoutDate = checkoutDate;
        this.numberPersons = numberPersons;
    }


    public String getClientBookingLastName() {
        return clientBookingLastName;
    }

    public void setClientBookingLastName(String clientBookingLastName) {
        this.clientBookingLastName = clientBookingLastName;
    }

    public String getClientBookingFirstName() {
        return this.clientBookingFirstName;
    }

    public void setClientBookingFirstName(String clientBookingFirstName) {
        this.clientBookingFirstName = clientBookingFirstName;
    }

    public String getBookingCardNumber() {
        return this.bookingPaymentCard.getCardNumber();
    }

    public void setBookingCardNumber(PaymentCard bookingPaymentCard) {
        this.bookingPaymentCard = bookingPaymentCard;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getNumberPersons() {
        return numberPersons;
    }

    public void setNumberPersons(int numberPersons) {
        this.numberPersons = numberPersons;
    }
}
