package cl.duocuc.notificaciones.controller;

import cl.duocuc.notificaciones.model.Notificacion;
import cl.duocuc.notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Capa de presentacion: expone los endpoints REST del microservicio. */
@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    // GET /api/v1/notificaciones
    @GetMapping
    public List<Notificacion> listar() {
        return service.listar();
    }

    // GET /api/v1/notificaciones/{id}
    @GetMapping("/{id}")
    public Notificacion buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // GET /api/v1/notificaciones/cliente/{clienteId}
    @GetMapping("/cliente/{clienteId}")
    public List<Notificacion> listarPorClienteId(@PathVariable Long clienteId) {
        return service.listarPorClienteId(clienteId);
    }

    // POST /api/v1/notificaciones  -> 201 Created
    @PostMapping
    public ResponseEntity<Notificacion> crear(@Valid @RequestBody Notificacion notificacion) {
        Notificacion creado = service.crear(notificacion);
        return ResponseEntity.created(URI.create("/api/v1/notificaciones/" + creado.getId())).body(creado);
    }

    // PUT /api/v1/notificaciones/{id}
    @PutMapping("/{id}")
    public Notificacion actualizar(@PathVariable Long id, @Valid @RequestBody Notificacion notificacion) {
        return service.actualizar(id, notificacion);
    }

    // DELETE /api/v1/notificaciones/{id}  -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
