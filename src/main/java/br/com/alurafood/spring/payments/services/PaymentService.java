package br.com.alurafood.spring.payments.services;

import br.com.alurafood.spring.payments.dtos.PaymentDto;
import br.com.alurafood.spring.payments.entities.Payment;
import br.com.alurafood.spring.payments.entities.Status;
import br.com.alurafood.spring.payments.repositories.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private ModelMapper modelMapper;


    public PaymentService(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Page<PaymentDto> listAllPayments(Pageable pagination) {
        return paymentRepository
                .findAll(pagination)
                .map(p -> modelMapper.map(p, PaymentDto.class));
    }

    public PaymentDto findPaymentById(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        payment.setStatus(Status.CREATED);
        paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto updatePayment(UUID paymentId, PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        payment.setPaymentId(paymentId);
        paymentRepository.save(payment);
        return modelMapper.map(payment, PaymentDto.class);
    }

    public void deletePayment(UUID paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}
