package mx.com.gm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService; //se inyecta una instancia de este objeto cuando se usa el método
    // configurerGlobal, y por medio de la variable build establezco que se va a utilizar la implementación de jpa que
    // utilicé en el loadUserByUserName

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ //método que convierto en bean
        return new BCryptPasswordEncoder();
    } //creo un nuevo objeto de tipo del bean BCryptPasswordEncoder
    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{ //tipo de objeto qeu spring va
        // a inyectar de manera automática al poner autowired. A l avariable la llamo build
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/editar/**", "/agregar/**", "/eliminar")
                .hasRole("ADMIN")
                .antMatchers("/")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403");
    }
}
