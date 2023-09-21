package com.example.service;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    //3R
    public Message createMessage(Message message){
        Optional<Account> accountOptional = accountRepository.findById(message.getPosted_by());
        if(accountOptional.isPresent()){
            return messageRepository.save(message);
        }
        return null;
    }

    //4R
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    //5R
    public Message getMessageByMessageID(int message_id) {
        Optional<Message> messageOptional = messageRepository.findById(message_id);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            messageRepository.save(message);
            return message;
        }
        return null;
    }

    //6R
    public int deleteMessageByMessageID(int message_id){
        try {
            messageRepository.deleteById(message_id);
            return 1;
        } catch (EmptyResultDataAccessException e) {
            // Entity with specified ID does not exist
            return 0; //
        }
    }

    //7R
    public int updateMessageByMessageID(int message_id, String message_text){
            Optional<Message> messageOptional = messageRepository.findById(message_id);
            if(messageOptional.isPresent()){
                Message message = messageOptional.get();
                message.setMessage_text(message_text);
                messageRepository.save(message);
                return 1;
            }
            return 0;
    }

    //8R
    public List<Message>  getAllMessagesByAccountID(int account_id){
        return messageRepository.findAllByPostedBy(account_id);
    }
}
