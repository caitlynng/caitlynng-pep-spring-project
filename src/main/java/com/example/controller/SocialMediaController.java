package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.BadRequestException;
import com.example.exception.ConflictException;
import com.example.exception.UnauthorizedException;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }
    /*
     * 1R: Handler to register a new account
     */
    @PostMapping("/register")
    public ResponseEntity<Account> registerAccountHandler(@RequestBody Account account) throws BadRequestException, ConflictException {
        if(account.getUsername().isEmpty() || account.getPassword().length() <= 4){
            throw new BadRequestException();
        }

        Account result = accountService.registerAccount(account);
        if (result == null){
            throw new ConflictException();
        }
        return ResponseEntity.ok(result);
    }
    /*
     * 2R: Handler to login existing account
     */
    @PostMapping("/login")
    public ResponseEntity<Account> loginAccountHandler(@RequestBody Account account) throws UnauthorizedException {
        Account result =  accountService.loginAccount(account);
        if (result == null){
            throw new UnauthorizedException();
        }
       return ResponseEntity.ok(result);
    }
    /*
     * 3R: Handler to create a new message
     */
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessageHandler(@RequestBody Message message) throws BadRequestException {
        Message result = messageService.createMessage(message);
        String messageText = message.getMessage_text();
        if (messageText.isEmpty() || messageText.length() > 254 || result == null){
            throw new BadRequestException();
        }
        return ResponseEntity.ok(result);
    }
    /*
     * 4R: Handler to retrieve all messages
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> createMessageHandler() {
        List<Message> result = messageService.getAllMessages();
        return ResponseEntity.ok(result);
    }

    /*
     * 5R: Handler to retrieve a message using a message ID
     */
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageByMessageIDHandler(@PathVariable int message_id){
        Message result = messageService.getMessageByMessageID(message_id);
        return ResponseEntity.ok(result);
    }

    /*
     * 6R: Handler to delete a message using a message ID
     */
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageByMessageIDHandler(@PathVariable int message_id){
        int result = messageService.deleteMessageByMessageID(message_id);
        return ResponseEntity.ok(result);
    }

    /*
     * 7R: Handler to update a message using a message ID
     */
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessageByMessageIDHandler( @RequestBody Message message, @PathVariable int message_id) throws BadRequestException {
        String message_text = message.getMessage_text();
        if (message_text.length() < 254 && !message_text.isEmpty()){
            int result = messageService.updateMessageByMessageID(message_id, message_text);
            if (result >= 1){
                return ResponseEntity.ok(result);
            }
        }
        throw new BadRequestException();
    }

    /*
     * 8R: Handler to retrieve all messages using an account ID
     */
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountIDHandler(@PathVariable int account_id){
        System.out.println(account_id);
        List<Message> messages = messageService.getAllMessagesByAccountID(account_id);
        return ResponseEntity.ok(messages);
    }
}
