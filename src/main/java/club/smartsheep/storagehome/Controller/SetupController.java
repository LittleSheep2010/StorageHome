package club.smartsheep.storagehome.Controller;

import club.smartsheep.storagehome.DAO.Entity.ConfigEntity;
import club.smartsheep.storagehome.DAO.Entity.UserEntity;
import club.smartsheep.storagehome.DAO.Mappers.ConfigMapper;
import club.smartsheep.storagehome.DAO.Mappers.UserMapper;
import club.smartsheep.storagehome.Services.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    CacheService cacheService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConfigMapper configMapper;

    @RequestMapping()
    public String Setup(Model model, @RequestParam(name = "step", required = false) Integer step) {
        if(step == null) {
            step = 0;
        }
        model.addAttribute("admin", new UserEntity());
        model.addAttribute("step", step);
        return "setup";
    }

    @PostMapping("/set-administer")
    public String SetAdminister(RedirectAttributes redirectAttributes, UserEntity entity) {
        entity.setRole("administer");
        userMapper.insert(entity);

        return "redirect:/setup?step=2";
    }

    @GetMapping("/done")
    public String CleanUp() {
        ConfigEntity entity = new ConfigEntity();
        entity.setName("setup.done");
        entity.setValue("TRUE");
        configMapper.insert(entity);

        cacheService.ClearAll();

        return "redirect:/";
    }
}
