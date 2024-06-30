package com.flipkart.business;
import com.flipkart.dao.FlipFitGymCustomerDAOImpl;
import com.flipkart.dao.interfaces.IFlipFitBookingDAO;
import com.flipkart.dao.interfaces.IFlipFitGymCustomerDAO;
import com.flipkart.model.FlipFitBooking;
import com.flipkart.model.FlipFitSlots;
import com.flipkart.dao.FlipFitBookingDAOImpl;
import com.flipkart.dao.FlipFitSlotDAOImpl;

import java.util.Random;

public class BookingsBusiness {
    private final IFlipFitBookingDAO bookingDAO ;
    public BookingsBusiness(FlipFitBookingDAOImpl FFBooking){
        this.bookingDAO=FFBooking;
    }

    public FlipFitBooking makeBooking(int centreID, int startTime) {

        //TODO:  Check if seat available > 0
        System.out.println("Making a booking for " + userId);

        FlipFitBooking booking = new FlipFitBooking();
        booking.setUserId(new Random().nextInt(100));
        booking.setSlotId(slotId);
        booking.setUserId(userId);
        booking.setIsdeleted(false);
        bookingDAO.makeBooking(booking);
        System.out.println("Booking completed");

        FlipFitSlotsBusiness flipFitSlotsBusiness = new FlipFitSlotsBusiness();
        FlipFitSlotDAOImpl flipFitSlotDAO = new FlipFitSlotDAOImpl();
        FlipFitSlots flipFitSlots = flipFitSlotDAO.getSlotDetailsById(slotId);
        FlipFitSlots currflipFitSlots = flipFitSlots;
        currflipFitSlots.setSeatsAvailable(flipFitSlots.getSeatsAvailable()-1);
        flipFitSlotsBusiness.updateAvailability(currflipFitSlots);
        return booking;
    }

    public boolean deleteBooking(int bookingId) {
        System.out.println("Deleting a booking for " + bookingId);
        FlipFitBookingDAOImpl bookingDAO = new FlipFitBookingDAOImpl();
        bookingDAO.deleteBooking(bookingId);
//        int slotId = getBookingDetails(bookingId).g
        int slotId = 1;
        System.out.println("Booking deleted");
        FlipFitSlotsBusiness flipFitSlotsBusiness = new FlipFitSlotsBusiness();
        FlipFitSlotDAOImpl flipFitSlotDAO = new FlipFitSlotDAOImpl();
        FlipFitSlots flipFitSlots = flipFitSlotDAO.getSlotDetailsById(slotId);
        FlipFitSlots currflipFitSlots = flipFitSlots;
        currflipFitSlots.setSeatsAvailable(flipFitSlots.getSeatsAvailable()+1);
        flipFitSlotsBusiness.updateAvailability(currflipFitSlots);
        return true;
    }
}
