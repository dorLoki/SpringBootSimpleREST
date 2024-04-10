package net.heydel.api.boudary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class APIControllerChange {

    
    @GetMapping("/admin/api")
    public String getAdminApi() {
        return "apiChange";
    }
    
}
