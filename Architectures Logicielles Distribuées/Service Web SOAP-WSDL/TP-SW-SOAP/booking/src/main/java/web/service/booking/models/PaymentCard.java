package web.service.booking.models;

import web.service.booking.exceptions.InvalidCardNumberException;

public class PaymentCard {
    private String cardNumber;

    public PaymentCard() {
    }

    public PaymentCard(String cardNumber) throws InvalidCardNumberException {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) throws InvalidCardNumberException  {
        this.cardNumber = cardNumber;
    }
}
