package net.heydel.api.boudary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
    private String message = "Hallo von deinem Alexa Skill!";

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }

    @PostMapping("/admin/api/update-message")
    public String updateMessage(@RequestBody MessageContent content) {
        this.message = content.getMessage();
        return message;
    }

    static class MessageContent {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
