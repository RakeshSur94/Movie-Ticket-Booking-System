package com.movie.ticket.booking.system.service.repository;

import com.movie.ticket.booking.system.service.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IPaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}
