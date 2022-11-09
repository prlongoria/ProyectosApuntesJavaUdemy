package mx.com.gm.dao;

import mx.com.gm.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{ //repositorio que genera los métodos más comunes
    // al usar la tabla usuario. Ese repositorio utilizará objetos de tipo Usuario y la llave
    // primaria idusuario es de tipo long
    Usuario findByUsername(String username); //este método lo defino yo porque no está en el JpaRepository
}
