package club.smartsheep.storagehome.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RolesAllowed({"user", "administer"})
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }
}
