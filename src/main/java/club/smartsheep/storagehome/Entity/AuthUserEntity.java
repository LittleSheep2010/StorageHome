package club.smartsheep.storagehome.Entity;

import club.smartsheep.storagehome.DAO.Entity.UserEntity;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@ToString
public class AuthUserEntity implements UserDetails {

    private String uuid;
    private String email;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    public AuthUserEntity(UserEntity user) {
        this.username = user.getUuid();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("user"));
        roles.add(new SimpleGrantedAuthority(user.getRole()));
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
