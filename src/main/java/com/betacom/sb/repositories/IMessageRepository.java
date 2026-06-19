package com.betacom.sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.Message;

@Repository
public interface IMessageRepository extends JpaRepository<Message, String>{

}
