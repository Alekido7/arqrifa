package org.arqrifa.persistencia;

import org.arqrifa.datatypes.DTSolicitud;


public interface IPersistenciaSolicitud {
    void altaSolicitud(DTSolicitud solicitud) throws Exception;
    
    void verificarSolicitud(int ci) throws Exception;
}