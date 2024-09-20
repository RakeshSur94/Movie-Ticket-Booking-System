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

//how docker container work?


        try {
            log.info("Entered into java mailing system service");
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setSubject("Conformation mail from Book My Show .....Its an auto generated mail don't reply");
            mimeMessageHelper.setText("Tickets Booking "+bookingDTO.getBookingStatus()+ "\n"+"\n"+

                    "Seat Number "+bookingDTO.getSeatsBooked().toString()+"\n"+"\n" +
                    "Booking Amount Rupees: "+bookingDTO.getBookingAmount()+"\n"+"\n" +"Show Date: "+bookingDTO.getShowDate()+"\n"+"\n" +
                    "Show Time: "+bookingDTO.getShowTime()+"\n"+"Thank you for using Book My Show");
            mimeMessageHelper.setTo(bookingDTO.getUserId());
            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.addAttachment("bookmyshow.png",new ClassPathResource("bookmyshow.png"));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
           log.error("Problem in mailing services ");
        }

    }

}
