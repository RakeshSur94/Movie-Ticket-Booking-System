package com.movie.ticket.booking.system.notificationservice.service;

import com.movie.ticket.booking.system.commons.dto.BookingDTO;

public interface INotificationService {

    public void sendConformationMail(BookingDTO bookingDTO);

}
