package cl.duocuc.notificaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notificaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El clienteId es obligatorio")
    @Column(nullable = false)
    private Long clienteId;

    @NotNull(message = "El canal es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CanalNotificacion canal;

    @NotBlank(message = "El mensaje es obligatorio")
    @Column(nullable = false, length = 500)
    private String mensaje;

    @Column(nullable = false)
    private Boolean enviada = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    /** Se ejecuta antes del INSERT: registra la fecha automaticamente. */
    @PrePersist
    void alCrear() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
