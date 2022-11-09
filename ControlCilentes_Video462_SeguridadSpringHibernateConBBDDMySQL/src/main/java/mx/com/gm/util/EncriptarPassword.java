package mx.com.gm.util;
//Creo esta clase de utileria para encriptar los password
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {
    public static void main(String[] args) {
        
        var password = "123";
        System.out.println("password: " + password);
        System.out.println("password encriptado:" + encriptarPassword(password));
        //Una vez que tengo en consola el password encriptado, lo copio y lo pego en el password de mi talba usuarios
        // para que cuando el user o el admin metan 123 ya se la reconozca la app y les permite loguearse
    }
    
    public static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //es una clase de Spring para encriptación
        return encoder.encode(password);
    }
}
