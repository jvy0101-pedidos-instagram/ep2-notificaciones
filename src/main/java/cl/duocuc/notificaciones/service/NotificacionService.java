package cl.duocuc.notificaciones.service;

import cl.duocuc.notificaciones.exception.RecursoNoEncontradoException;
import cl.duocuc.notificaciones.model.*;
import cl.duocuc.notificaciones.repository.NotificacionRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Capa de negocio: el controller llama al service y el service al repository. */
@Service
public class NotificacionService {

    private final NotificacionRepository repository;

    // Inyeccion de dependencias por constructor.
    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public List<Notificacion> listar() {
        return repository.findAll();
    }

    public Notificacion buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe notificacion con id " + id));
    }

    public List<Notificacion> listarPorClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    @Transactional
    public Notificacion crear(Notificacion nuevo) {
        if (nuevo.getEnviada() == null) nuevo.setEnviada(false);
        nuevo.setId(null); // el id lo genera la base de datos
        return repository.save(nuevo);
    }

    @Transactional
    public Notificacion actualizar(Long id, Notificacion datos) {
        Notificacion existente = buscarPorId(id);
        existente.setClienteId(datos.getClienteId());
        existente.setCanal(datos.getCanal());
        existente.setMensaje(datos.getMensaje());
        if (datos.getEnviada() != null) existente.setEnviada(datos.getEnviada());
        return repository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Notificacion existente = buscarPorId(id);
        repository.delete(existente);
    }
}
