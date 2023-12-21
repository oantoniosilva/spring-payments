package br.com.alurafood.spring.payments.repositories;

import br.com.alurafood.spring.payments.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
