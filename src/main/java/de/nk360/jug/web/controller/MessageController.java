package de.nk360.jug.web.controller;

import de.nk360.jug.web.model.MessageResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MessageController {
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> actionGet(@RequestParam(name = "name") String name) {
        logger.info("Sent message 'Hello " + name + "!'.");
        return ResponseEntity.ok(new MessageResponse("Hello " + name + "!"));
    }
}
