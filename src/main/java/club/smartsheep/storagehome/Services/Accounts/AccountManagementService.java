package club.smartsheep.storagehome.Services.Accounts;

import club.smartsheep.storagehome.DAO.Entity.UserEntity;
import club.smartsheep.storagehome.DAO.Mappers.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountManagementService {

    @Autowired
    UserMapper userMapper;

    public AddResponse addNewUser(UserEntity user) {
        if(userMapper.selectList(new QueryWrapper<UserEntity>().eq("username", user.getUsername())).toArray().length != 0) {
            return AddResponse.UsernameRepeat;
        }

        if(userMapper.selectList(new QueryWrapper<UserEntity>().eq("email", user.getEmail())).toArray().length != 0) {
            return AddResponse.EmailRepeat;
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insert(user);
        return AddResponse.Successful;
    }

    public LoginResponse checkUserCredentials(String identification, String password) {
        UserEntity user = this.getUserByIdentification(identification);
        if(user == null) {
            return LoginResponse.UserNonExist;
        }

        if(!user.getPassword().equalsIgnoreCase(new BCryptPasswordEncoder().encode(password))) {
            return LoginResponse.PasswordNonSame;
        }

        return LoginResponse.Successful;
    }

    public UserEntity getUserByIdentification(String identification) {
        UserEntity user = userMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", identification));
        if(user == null) {
            // If system cannot find target account by username, try find it use email
            log.warn("This identification isn't username: " + identification);
            user = userMapper.selectOne(new QueryWrapper<UserEntity>().eq("email", identification));
        }

        return user;
    }


    public enum AddResponse {
        UsernameRepeat,
        EmailRepeat,

        Successful
    }

    public enum LoginResponse {
        UserNonExist,
        PasswordNonSame,

        Successful
    }
}
