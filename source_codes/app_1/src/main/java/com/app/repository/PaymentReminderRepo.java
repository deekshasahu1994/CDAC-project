package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.PaymentReminder;
@Repository
public interface PaymentReminderRepo extends JpaRepository<PaymentReminder, Long>{

}
