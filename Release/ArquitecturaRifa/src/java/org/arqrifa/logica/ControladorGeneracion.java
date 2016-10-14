package org.arqrifa.logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.arqrifa.datatypes.DTGeneracion;
import org.arqrifa.datatypes.DTSolicitud;
import org.arqrifa.datatypes.DTUsuario;
import org.arqrifa.excepciones.ArquitecturaRifaExcepcion;
import org.arqrifa.persistencia.FabricaPersistencia;

class ControladorGeneracion implements IControladorGeneracion {

    private static ControladorGeneracion instancia = null;

    public static ControladorGeneracion getInstancia() {
        if (instancia == null) {
            instancia = new ControladorGeneracion();
        }
        return instancia;
    }

    private ControladorGeneracion() {
    }

    @Override
    public List<DTSolicitud> ListarSolicitudes(DTUsuario usuario) {
        List<DTSolicitud> solicitudes = new ArrayList();
        try {
            if (usuario == null) {
                throw new Exception("El usuario no puede ser nulo.");
            }
            solicitudes = FabricaPersistencia.getPersistenciaGeneracion().listarSolicitudes(usuario.getGeneracion());
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
        return solicitudes;
    }

    @Override
    public List<DTGeneracion> listarGeneraciones() {
        List<DTGeneracion> generaciones = new ArrayList();
        try {
            generaciones = FabricaPersistencia.getPersistenciaGeneracion().listarGeneraciones();
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
        return generaciones;
    }

    @Override
    public void altaGeneracion(DTGeneracion generacion) {
        try {
            if (generacion.getId() < 2009) {
                throw new Exception("El año ingresado es inferior al permitido.");
            }
            if (generacion.getId() > Calendar.getInstance().get(Calendar.YEAR)) {
                throw new Exception("No se puede agregar una generación cuyo año supere al actual");
            }
            FabricaPersistencia.getPersistenciaGeneracion().altaGeneracion(generacion);
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
    }

}
