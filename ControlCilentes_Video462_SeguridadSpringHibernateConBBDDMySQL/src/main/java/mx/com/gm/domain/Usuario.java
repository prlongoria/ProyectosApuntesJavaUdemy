package mx.com.gm.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    @OneToMany //relacion entre tabla de usuario y la de rol(la llave foránea es la columna id_usuario de la tabla rol
    // por eso es la que pongo aquí, es la que hace la relación entre las tablas)
    @JoinColumn(name="id_usuario")
    private List<Rol> roles; //atributo que es lista de roles que se le asignan a un usuario
}
