package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import service.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/oglasi/slike/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/oglasi/{id}/").authenticated()
                .antMatchers(HttpMethod.DELETE,"/api/oglasi/{id}/").authenticated()
                .antMatchers("/api/oglasi/dodaj/{orderID}/").authenticated()
                .antMatchers("/api/oglasi/moji/").authenticated()
                .antMatchers("/api/oglasi/sacuvani/").authenticated()
                .antMatchers("/api/oglasi/sacuvaj/{id}/").authenticated()
                .antMatchers("/api/oglasi/izbrisiSacuvan/{id}/").authenticated()
                .antMatchers("/api/oglasi/obnova/{orderID}/").authenticated()
                .anyRequest()
                .permitAll();
              //.authenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
