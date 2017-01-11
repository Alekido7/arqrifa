package org.arqrifa.logica;

import org.arqrifa.datatypes.DTEncuesta;
import org.arqrifa.datatypes.DTReunion;
import org.arqrifa.datatypes.DTVoto;
import org.arqrifa.excepciones.ArquitecturaRifaException;
import org.arqrifa.persistencia.FabricaPersistencia;

public class ControladorEncuesta implements IControladorEncuesta {

    private static ControladorEncuesta instancia = null;

    public static ControladorEncuesta getInstancia() {
        if (instancia == null) {
            instancia = new ControladorEncuesta();
        }
        return instancia;
    }

    private ControladorEncuesta() {
    }

    @Override
    public void agregarEncuesta(DTReunion reunion) {
        try {
            if (reunion == null) {
                throw new Exception("La reunión no puede ser nula.");
            }
            if (reunion.getEstado().equals(DTReunion.FINALIZADA)) {
                throw new Exception("No se puede crear encuestas para reuniones finalizadas.");
            }
            if (reunion.getEncuesta() == null) {
                throw new Exception("La encuesta no puede ser nula.");
            }
            if (reunion.getEncuesta().getPropuestas().isEmpty()) {
                throw new Exception("No se puede crear una encuesta sin propuestas.");
            }

            FabricaPersistencia.getPersistenciaEncuesta().agregarEncuesta(reunion);
        } catch (Exception e) {
            throw new ArquitecturaRifaException(e.getMessage());
        }
    }

    @Override
    public void agregarVoto(DTVoto voto) {
        try {
            FabricaPersistencia.getPersistenciaEncuesta().agregarVoto(voto);
        } catch (Exception e) {
            throw new ArquitecturaRifaException(e.getMessage());
        }
    }

    @Override
    public DTEncuesta buscarEncuesta(int id_encuesta) {
        try {
            return FabricaPersistencia.getPersistenciaEncuesta().buscar(id_encuesta);
        } catch (Exception e) {
            throw new ArquitecturaRifaException(e.getMessage());
        }
    }

    @Override
    public void habilitarVotacionEncuesta(DTReunion reunion) {
        try {
            if (!reunion.getEstado().equals(DTReunion.INICIADA)) {
                throw new Exception("No es posible habilitar la votación de una encuesta si la reunión no está en progreso.");
            }
            if (reunion.getEncuesta().isHabilitada()) {
                throw new Exception("No es posible habilitar la votación de la encuesta si ésta ya fue habilitada.");
            }
            FabricaPersistencia.getPersistenciaEncuesta().habilitarVotacion(reunion.getEncuesta());
        } catch (Exception e) {
            throw new ArquitecturaRifaException(e.getMessage());
        }
    }

}
