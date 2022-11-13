package web.service.booking.models;

public class Room {
    private int roomNumber;
    private int numberBed;
    private float price;
    private boolean availbale;

    public Room() {
    }

    public Room(int roomNumber, int numberBed, float price, boolean availbale) {
        this.roomNumber = roomNumber;
        this.numberBed = numberBed;
        this.price = price;
        this.availbale = availbale;
    }

    public boolean isAvailbale() {
        return this.availbale;
    }

    public void setAvailbale(boolean availbale) {
        this.availbale = availbale;
    }

public int getRoomNumber() {
    return this.roomNumber;
}

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberBed() {
        return this.numberBed;
    }

    public void setNumberBed(int numberBed) {
        this.numberBed = numberBed;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
