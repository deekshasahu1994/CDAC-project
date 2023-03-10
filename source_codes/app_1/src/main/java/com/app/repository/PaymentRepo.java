package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Payment;
@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long>{

}
