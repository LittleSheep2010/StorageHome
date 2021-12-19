package club.smartsheep.storagehome.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/dashboard")
@RolesAllowed({"user"})
public class DashboardController {
    @RequestMapping()
    public String Dashboard(Model model) {
        return "dashboard";
    }
}
