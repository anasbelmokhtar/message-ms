package org.ac.cst8277.belmokhtar.anas.controllers;
import org.ac.cst8277.belmokhtar.anas.model.Message;
import org.ac.cst8277.belmokhtar.anas.services.MessageService;
import org.ac.cst8277.belmokhtar.anas.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {

    MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/twitter-like-app/messages")
    public ResponseEntity<List<Message>> getMessages(@RequestHeader (value = "Authorization") String auth) {
        String userId = JwtUtil.getUserId(auth);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(messageService.getMessages(userId));
    }
    @PostMapping("/twitter-like-app/message")
    public ResponseEntity<Integer> insertMessage(@RequestHeader (value = "Authorization") String auth, @RequestBody Message message) {
        String userId = JwtUtil.getUserId(auth);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(messageService.insertMessage(message,userId));
    }

    @GetMapping("/twitter-like-app/messages/producers/{producerId}")
    public ResponseEntity<List<Message>> getMessagesByProducer(@RequestHeader (value = "Authorization") String auth, @PathVariable String producerId) {

        if (!JwtUtil.isValid(auth)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(messageService.getMessagesByProducer(producerId));
    }

    @GetMapping("/twitter-like-app/messages/subscribers/{subscriberId}")
    public ResponseEntity<List<Message>> getMessagesBySubscriber(@RequestHeader (value = "Authorization") String auth, @PathVariable String subscriberId) {
        if (!JwtUtil.isValid(auth)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(messageService.getMessagesBySubscriber(subscriberId));
    }

}
