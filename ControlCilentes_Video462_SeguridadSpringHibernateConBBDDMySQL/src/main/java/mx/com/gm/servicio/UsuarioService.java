package mx.com.gm.servicio;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService") //convierto la clase a un servicio y le pongo el nombre de bean
@Slf4j
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioDao usuarioDao; //Inyecto una instancia de tipo UsuarioDao y con esta clase voy a interactuar
    // con las tablas usuario y rol
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username); //así recupero el objeto de usuario
        
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        
        var roles = new ArrayList<GrantedAuthority>(); //Si tengo exito encontrando al usuario, recupero la lista con los permisos
        
        for(Rol rol: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre())); //en vuelvo cada rol en un nuevo objeto de tipo SimpleGrantedAuthority
        }
        
        return new User(usuario.getUsername(), usuario.getPassword(), roles); //regresa un objeto con todos los
        // elementos que recuperé. Este User implementa userDetails de
        // package org.springframework.security.core.userdetails, OJO con esto a la hora de importar esta clase de User
    }
    
}
