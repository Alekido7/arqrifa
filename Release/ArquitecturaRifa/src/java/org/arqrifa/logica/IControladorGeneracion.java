package org.arqrifa.logica;

import java.util.List;
import org.arqrifa.datatypes.DTGeneracion;
import org.arqrifa.datatypes.DTSolicitud;
import org.arqrifa.datatypes.DTUsuario;

public interface IControladorGeneracion {

    List<DTGeneracion> listarGeneraciones();

    List<DTSolicitud> ListarSolicitudes(DTUsuario usuario);

}
