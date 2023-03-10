package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Client;

public interface ClientRepo extends JpaRepository<Client, Long>{

}
