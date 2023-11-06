package com.movie.ticket.booking.system.service.impl;

import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import com.movie.ticket.booking.system.commons.dto.BookingStatus;
import com.movie.ticket.booking.system.service.IBookingService;
import com.movie.ticket.booking.system.service.entity.BookingEntity;
import com.movie.ticket.booking.system.service.kafka.publisher.BookingServiceKafkaPublisher;
import com.movie.ticket.booking.system.service.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookingServiceImpl implements IBookingService {
    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private BookingServiceKafkaPublisher bookingServiceKafkaPublisher;

    @Override
    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        BookingEntity bookingEntity=BookingEntity.builder()
                .userId(bookingDTO.getUserId())
                .movieId(bookingDTO.getMovieId())
                .bookingAmount(bookingDTO.getBookingAmount())
                .seatsBooked(bookingDTO.getSeatsBooked())
                .bookingStatus(BookingStatus.PENDING)
                .showDate(bookingDTO.getShowDate())
                .showTime(bookingDTO.getShowTime())
                .build();
        this.bookingRepository.save(bookingEntity);  // create a booking with booking status as PENDING
        bookingDTO.setBookingId(bookingEntity.getBookingId());
        bookingDTO.setBookingStatus(BookingStatus.PENDING);
        //publish the booking to kafka topic
        this.bookingServiceKafkaPublisher.publishPaymentRequestTopicToPaymentService(bookingDTO);
        return bookingDTO;


//        return BookingDTO.builder()
//                .bookingId(bookingEntity.getBookingId())
//                .bookingAmount(bookingEntity.getBookingAmount())
//                .bookingStatus(bookingEntity.getBookingStatus())
//                .movieId(bookingEntity.getMovieId())
//                .showTime(bookingEntity.getShowTime())
//                .showDate(bookingEntity.getShowDate())
//                .bookingAmount(bookingEntity.getBookingAmount())
//                .userId(bookingEntity.getUserId())
//                .seatsBooked(bookingEntity.getSeatsBooked())
//                .build();
    }

    @Override
    @Transactional
    public void processBooking(BookingDTO bookingDTO) {
        Optional<BookingEntity> bookingEntityOptional= this.bookingRepository.findById(bookingDTO.getBookingId());
        if(bookingEntityOptional.isPresent()){
            BookingEntity bookingEntity = bookingEntityOptional.get();
            bookingEntity.setBookingStatus(bookingDTO.getBookingStatus());
        }
    }
}
