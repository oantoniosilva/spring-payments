package br.com.alurafood.spring.payments.dtos;

import br.com.alurafood.spring.payments.entities.Status;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentDto(
        BigDecimal value,
        String name,
        String number,
        String expiration,
        String code,
        Status status,
        UUID orderId,
        UUID formOfPaymentId
) {
}
