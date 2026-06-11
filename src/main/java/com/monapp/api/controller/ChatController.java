package com.monapp.api.controller;

import com.monapp.api.model.Message;
import com.monapp.api.repository.MessageRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "Chat", description = "Messagerie privée entre utilisateurs")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload Message message) {
        message.setDateEnvoi(LocalDateTime.now());
        message.setLu(false);
        messageRepository.save(message);
        messagingTemplate.convertAndSendToUser(
            String.valueOf(message.getDestinataireid()),
            "/queue/messages",
            message
        );
    }

    @GetMapping("/api/messages/{userId1}/{userId2}")
    @Operation(summary = "Récupérer l'historique de conversation entre deux utilisateurs")
    public List<Message> getConversation(@PathVariable Long userId1, @PathVariable Long userId2) {
        return messageRepository.findConversation(userId1, userId2);
    }

    @GetMapping("/api/messages/nonlus/{userId}")
    @Operation(summary = "Récupérer les messages non lus d'un utilisateur")
    public List<Message> getNonLus(@PathVariable Long userId) {
        return messageRepository.findNonLus(userId);
    }

    @PutMapping("/api/messages/{messageId}/lu")
    @Operation(summary = "Marquer un message comme lu")
    public void marquerLu(@PathVariable Long messageId) {
        messageRepository.findById(messageId).ifPresent(m -> {
            m.setLu(true);
            messageRepository.save(m);
        });
    }
}
