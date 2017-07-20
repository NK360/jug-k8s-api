package de.nk360.jugapi.web.controller;

import de.nk360.jugapi.web.model.MessageResponse;

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
    private String message = "Hello %s!";

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> actionGet(@RequestParam(name = "name") String name) {
        String templatedMessage = String.format(message, name);
        return ResponseEntity.ok(new MessageResponse(templatedMessage));
    }
}
