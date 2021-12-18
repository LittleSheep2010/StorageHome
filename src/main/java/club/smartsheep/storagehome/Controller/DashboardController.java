package club.smartsheep.storagehome.Controller;

import club.smartsheep.storagehome.Aspect.Login.LoginRequire;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @LoginRequire
    @RequestMapping("/")
    public String Dashboard(Model model) {
        return "dashboard";
    }
}
