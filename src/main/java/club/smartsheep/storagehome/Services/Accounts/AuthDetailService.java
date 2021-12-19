package club.smartsheep.storagehome.Services.Accounts;

import club.smartsheep.storagehome.DAO.Entity.UserEntity;
import club.smartsheep.storagehome.DAO.Mappers.UserMapper;
import club.smartsheep.storagehome.Entity.AuthUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthDetailService implements UserDetailsService {
    @Autowired
    AccountManagementService accountManagementService;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = accountManagementService.getUserByIdentification(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not found with '%s'", username));
        }

        return new AuthUserEntity(user);
    }
}
