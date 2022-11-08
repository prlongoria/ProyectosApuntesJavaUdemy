package mx.com.gm.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //Para habilitar la seguridad web
public class SecurityConfig extends WebSecurityConfigurerAdapter{ //para poder configurar los usuarios que voy a
    // utilizar debe extender de esta otra clase

    //Método (para autenticacion) para que sólo puedan acceder a mi app con autentificación:
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //Así creo usuarios directamente en la memoria (el pasaword lo hay que encriptar, pero aquí pongo noop para
        // que no tenga que encriptarlo:
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password("{noop}123")
                    .roles("ADMIN","USER")
                .and()
                .withUser("user")
                    .password("{noop}123")
                    .roles("USER")
                ;
    }

    //Este método (para autorización) es para evitar que pueda cualquier url acceder a mi app:no quiero que cualquier path que ponga editar,
    //agregar,  o eliminar pueda acceder, y también especifico los roles que pueden acceder a estos paths, y también
    // al path raiz. Da permisos especiales al admin, otros a los user y sólo login al resto
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/editar/**", "/agregar/**", "/eliminar")
                    .hasRole("ADMIN")
                .antMatchers("/")
                    .hasAnyRole("USER","ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login") //pagina para loguearse
                .and()
                    .exceptionHandling().accessDeniedPage("/errores/403")//errores es un archivo que he
        // creado para cuando no estás logueado
                ;
    }
}
