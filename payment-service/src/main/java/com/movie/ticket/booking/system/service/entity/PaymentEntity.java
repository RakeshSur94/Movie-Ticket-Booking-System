package com.movie.ticket.booking.system.service.entity;

import com.movie.ticket.booking.system.commons.dto.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="payments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id")
    private UUID paymentId;
    @Column(name = "booking_id")
    private UUID bookingId;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column(name = "payment_amount")
    private Double paymentAmount;
    @Column(name = "created_time")
    private LocalDateTime paymentTimestamp;



}
