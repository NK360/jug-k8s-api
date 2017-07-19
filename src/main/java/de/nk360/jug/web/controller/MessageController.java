package de.nk360.jug.web.controller;

import de.nk360.jug.web.model.MessageResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MessageController
 *
 * @author Jochen Niebuhr
 */
@RestController
@RequestMapping("message")
@ConfigurationProperties("application.messages")
public class MessageController {
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    private String message = "Hello %s!";

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> actionGet(@RequestParam(name = "name") String name) {
        String templatedMessage = String.format(message, name);
        logger.info("Sent message '" + templatedMessage + "'.");
        return ResponseEntity.ok(new MessageResponse(templatedMessage));
    }

    public String getMessage() {
        return message;
    }

    public MessageController setMessage(String message) {
        this.message = message;
        return this;
    }
}
