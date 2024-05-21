package es.jcyl.formacion.backendapi.persistencia.entidades;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@Builder
//@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USUARIOS")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="NOMBRE_COMPLETO", length = 200)
    private String nombreCompleto;

    @Column(name="INICIALES", length = 10)
    private String iniciales;

    @Column(name="CORREO", length = 100, nullable = false, unique = true)
    private String correo;

    @Column(name="CLAVE", length = 100, nullable = false)
    private String clave;

    @ManyToMany
    @JoinTable(name="USUARIOS_ROLES",
      joinColumns=@JoinColumn(name="rol_id",
      referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name = "usuario_id",
            referencedColumnName="id"))
    private List<Rol> roles;

    @CreatedDate
    @Column(name="F_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name="F_MODIFICACION",insertable = false)
    private LocalDateTime fechaModificacion;
}
