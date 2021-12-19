package club.smartsheep.storagehome.Controller.Accounts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/account/login")
public class LoginController {

    @RequestMapping("")
    public String loginPage(Model model, Boolean error, Boolean logout) {
        model.addAttribute("logout", logout);
        model.addAttribute("error", error);
        return "account/login";
    }
}
