import enums.FlatType;

import java.util.Date;

public class Booking {
    private FlatType flatType = null;
    private Date bookingDate = null;

    public Booking() {
    }

    public void setFlatType(FlatType flatType) {
        this.flatType = flatType;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
