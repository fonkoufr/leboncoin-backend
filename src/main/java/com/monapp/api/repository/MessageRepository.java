package com.monapp.api.repository;

import com.monapp.api.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE " +
           "(m.expediteurId = :u1 AND m.destinataireId = :u2) OR " +
           "(m.expediteurId = :u2 AND m.destinataireId = :u1) " +
           "ORDER BY m.dateEnvoi ASC")
    List<Message> findConversation(@Param("u1") Long u1, @Param("u2") Long u2);

    @Query("SELECT m FROM Message m WHERE m.destinataireId = :userId AND m.lu = false")
    List<Message> findNonLus(@Param("userId") Long userId);
}
