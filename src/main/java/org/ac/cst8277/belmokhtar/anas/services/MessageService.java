package org.ac.cst8277.belmokhtar.anas.services;

import org.ac.cst8277.belmokhtar.anas.dao.MessageDao;
import org.ac.cst8277.belmokhtar.anas.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao){
        this.messageDao = messageDao;
    }

    public List<Message> getMessages(String userID){
        return messageDao.getMessages(userID);
    }

    public int insertMessage(Message message, String userId) {
        return messageDao.insertMessage(message, userId);
    }

    public List<Message> getMessagesByProducer(String userId) {
        return messageDao.getMessagesByProducer(userId);
    }

    public List<Message> getMessagesBySubscriber(String userId) {
        return messageDao.getMessagesBySubscriber(userId);
    }
}
