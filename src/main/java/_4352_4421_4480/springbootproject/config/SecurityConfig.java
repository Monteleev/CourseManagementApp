package _4352_4421_4480.springbootproject.config;

import _4352_4421_4480.springbootproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        // the boolean flags force the redirection even though
//        // the user requested a specific secured resource.
        http.formLogin().defaultSuccessUrl("/courses", true);
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/courses/**").hasRole("INSTRUCTOR")
                .antMatchers("/students/**").hasRole("INSTRUCTOR")
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/error.html")
                .and()
                .logout()
                .logoutUrl("/perform_logout");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        UserDetails user1 = User.withUsername("john")
                .password(passwordEncoder().encode("1234"))
                .roles("INSTRUCTOR")
                .build();

        UserDetails user2 = User.withUsername("mary")
                .password(passwordEncoder().encode("iammary"))
                .roles("USER")
                .build();

        auth.inMemoryAuthentication().withUser(user1).withUser(user2);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

