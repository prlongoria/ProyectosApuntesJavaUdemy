package mx.com.gm.web;
//Creo esta clase que implementa la interface para implementar varios métodos de ella y poder tener manejo de
// múltiples idiomas
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration //para registrar esta clase como un bean porque sino nada va a funcionar
public class WebConfig implements WebMvcConfigurer{
    
    @Bean //así creo un bean y lo agrega al contenedor de spring
    public LocaleResolver localeResolver(){ //se importa de webserver OJO
        var slr = new SessionLocaleResolver(); //creo objeto de esta clase para el manejo de varios idiomas
        slr.setDefaultLocale(new Locale("es")); //para que se maneje el español por defecto
        return slr;
    }
    
    @Bean //creo otro bean para poder cambiar a otro idioma de manera dinámica
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang"); //parametro para poder cambiar de idioma (lang)
        return lci;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor()); //para registrar el interceptor
    }
    
}
