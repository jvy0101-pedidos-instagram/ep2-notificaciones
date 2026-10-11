package cl.duocuc.notificaciones.repository;

import cl.duocuc.notificaciones.model.Notificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByClienteId(Long clienteId);
}
