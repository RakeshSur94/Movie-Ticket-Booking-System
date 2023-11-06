package com.movie.ticket.booking.system.notificationservice.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.system.commons.constants.LoggerConstants;
import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import com.movie.ticket.booking.system.notificationservice.service.INotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class NotificationServiceImpl implements INotificationService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Override
    public void sendConformationMail(BookingDTO bookingDTO) {
        try {
            log.info("Entered into java mailing system service");
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setSubject("Conformation from Book My Show .....");
            mimeMessageHelper.setText("Ticket Booking conformation "+bookingDTO.getBookingStatus()+
                    " !!! with seat number "+bookingDTO.getSeatsBooked().toString()+" with booking amount of :: "+
                    bookingDTO.getBookingAmount()+" Rupee show date:: "+bookingDTO.getShowDate()+" show time:: "+bookingDTO.getShowTime());
            mimeMessageHelper.setTo(bookingDTO.getUserId());
            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.addAttachment("bookmyshow.png",new ClassPathResource("bookmyshow.png"));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
           log.error("Problem in mailing services ");
        }

    }

}
