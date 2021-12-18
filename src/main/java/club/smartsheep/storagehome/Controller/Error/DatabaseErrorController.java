package club.smartsheep.storagehome.Controller.Error;

import club.smartsheep.storagehome.Services.CacheService;
import club.smartsheep.storagehome.Services.InitDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/database")
public class DatabaseErrorController {
    @Autowired
    InitDatabaseService initDatabaseService;

    @Autowired
    CacheService cacheService;

    @RequestMapping()
    public String processGuide() {
        return "error/data-access-error";
    }

    @RequestMapping("/init")
    public String initDatabase() {
        initDatabaseService.init();
        return "redirect:/setup";
    }

    @RequestMapping("/retry")
    public String retry() {
        cacheService.ClearAll();
        return "redirect:/";
    }
}
