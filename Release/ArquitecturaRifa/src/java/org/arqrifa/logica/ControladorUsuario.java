package org.arqrifa.logica;

import org.arqrifa.datatypes.DTSolicitud;
import org.arqrifa.datatypes.DTUsuario;
import org.arqrifa.persistencia.FabricaPersistencia;
import org.arqrifa.excepciones.ArquitecturaRifaExcepcion;

class ControladorUsuario implements IControladorUsuario {

    private static ControladorUsuario instancia = null;

    public static IControladorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ControladorUsuario();
        }
        return instancia;
    }

    private ControladorUsuario() {
    }

    @Override
    public DTUsuario Autenticar(int ci, String contrasena) {
        DTUsuario resp = null;
        try {
            resp = FabricaPersistencia.getPersistenciaUsuario().Autenticar(ci, contrasena);
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
        return resp;
    }

    @Override
    public void altaSolicitud(DTSolicitud solicitud) {
        try {
            if (solicitud == null) {
                throw new Exception("No se puede dar de alta una solicitud nula.");
            }
            FabricaPersistencia.getPersistenciaUsuario().AltaSolicitud(solicitud);
        } catch (Exception e) {
            throw new ArquitecturaRifaExcepcion(e.getMessage());
        }
    }
}
