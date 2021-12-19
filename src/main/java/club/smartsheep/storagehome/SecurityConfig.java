package club.smartsheep.storagehome;

import club.smartsheep.storagehome.Services.Accounts.AuthDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpSession;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthDetailService authDetailService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authDetailService);
        authenticationProvider.setPasswordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return new BCryptPasswordEncoder().encode(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
            }
        });
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/setup").permitAll()
                .antMatchers("/setup/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/account/logout").authenticated()
                .antMatchers("/account/login").permitAll()
                .antMatchers("/account/login/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .anyRequest().authenticated();
        http.rememberMe().key("JSESSIONID").userDetailsService(authDetailService).and().logout().deleteCookies("JSESSIONID");

        http.cors().disable();
        http.csrf().disable();

        http
                .formLogin()
                .loginProcessingUrl("/account/login/commit")
                .loginPage("/account/login")
                .failureForwardUrl("/account/login?error=true")
                .and()
                .logout()
                .logoutUrl("/account/logout")
                .logoutSuccessUrl("/account/login?logout=true");
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.addAllowedOrigin("*");
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("*");
        configurationSource.registerCorsConfiguration("/**", cors);
        return new CorsFilter(configurationSource);
    }
}

