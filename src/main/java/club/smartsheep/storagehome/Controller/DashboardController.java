package club.smartsheep.storagehome.Controller;

import club.smartsheep.storagehome.DAO.Mappers.StorageItemMapper;
import club.smartsheep.storagehome.DAO.Mappers.StorageRegionMapper;
import club.smartsheep.storagehome.DAO.Mappers.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/dashboard")
@RolesAllowed({"user"})
public class DashboardController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StorageRegionMapper storageRegionMapper;

    @Autowired
    StorageItemMapper storageItemMapper;

    @RequestMapping()
    public String Dashboard(Model model) {
        model.addAttribute("count_users", userMapper.selectCount(new QueryWrapper<>()));
        model.addAttribute("count_items", storageItemMapper.selectCount(new QueryWrapper<>()));
        model.addAttribute("count_regions", storageRegionMapper.selectCount(new QueryWrapper<>()));
        return "dashboard";
    }
}
