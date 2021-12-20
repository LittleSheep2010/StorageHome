package club.smartsheep.storagehome.Controller.Accounts;

import club.smartsheep.storagehome.DAO.Entity.UserEntity;
import club.smartsheep.storagehome.DAO.Mappers.UserMapper;
import club.smartsheep.storagehome.Services.Accounts.AccountManagementService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/account")
@RolesAllowed({"administer"})
public class AccountManagementController {

    @Autowired
    AccountManagementService accountManagementService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping()
    public String Management(Model model, @RequestParam(defaultValue = "list") String focusAction, String error, String success) {
        model.addAttribute("message_danger", error);
        model.addAttribute("message_success", success);

        model.addAttribute("list_users", userMapper.selectList(new QueryWrapper<>()));

        model.addAttribute("count_users", userMapper.selectCount(new QueryWrapper<>()));
        model.addAttribute("count_common_users", userMapper.selectCount(new QueryWrapper<UserEntity>().ne("role", "administer")));
        model.addAttribute("count_admin", userMapper.selectCount(new QueryWrapper<UserEntity>().eq("role", "administer")));

        model.addAttribute("focus_action", focusAction);

        model.addAttribute("template_account", new UserEntity());
        return "account/accountManagement";
    }

    @RequestMapping("/delete")
    public String DeleteUser(String uuid) {
        UserEntity user = userMapper.selectOne(new QueryWrapper<UserEntity>().eq("uuid", uuid));
        if(user == null) {
            return "redirect:/account?error=Cannot%20found%20target%20user!";
        }

        if(user.getRole().equalsIgnoreCase("administer")) {
            return "redirect:/account?error=Cannot%20delete,%20because%20target%20user%20is%20a%20administrator!";
        }

        userMapper.delete(new QueryWrapper<UserEntity>().eq("uuid", uuid));

        return "redirect:/account?success=Delete%20successful!";
    }

    @PostMapping("/new")
    public String NewUser(UserEntity user) {
        UserEntity checkUser = userMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", user.getUsername()));
        if(checkUser != null) {
            return "redirect:/account?error=Username%20is%20repeated!";
        }

        checkUser = userMapper.selectOne(new QueryWrapper<UserEntity>().eq("email", user.getEmail()));
        if(checkUser != null) {
            return "redirect:/account?error=Email%20is%20repeated!";
        }

        user.setRole("user");
        accountManagementService.addNewUser(user);

        return "redirect:/account?success=Create%20new%20account%20successful!";
    }
}
