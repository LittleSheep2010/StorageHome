package club.smartsheep.storagehome.Controller.Accounts;

import club.smartsheep.storagehome.DAO.Entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/login")
public class LoginController {
    @RequestMapping
    public String loginPage(Model model, String message) {
        model.addAttribute("user", new UserEntity());

        if(message != null) {
            model.addAttribute("lmessage_danger", message);
        }

        return "accounts/login";
    }

    @PostMapping("/commit")
    public String validationLogin(UserEntity user) {
        return "redirect:/account/login?message=Please%20check%20username%20and%20password";
    }
}
