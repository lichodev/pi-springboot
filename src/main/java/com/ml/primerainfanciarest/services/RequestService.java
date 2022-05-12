package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.RequestConverter;
import com.ml.primerainfanciarest.entities.Request;
import com.ml.primerainfanciarest.models.RequestModel;
import com.ml.primerainfanciarest.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Servicio encargado de la lógica relacionada con las requests
 * @author sole
 * @version 1.0
 */
@Service("RequestService")
public class RequestService {
    @Autowired
    @Qualifier("RequestRepository")
    private RequestRepository repository;

    @Autowired
    @Qualifier("RequestConverter")
    private RequestConverter converter;

    /**
     * Obtiene el listado de requests
     * <p>Obtiene el listado de requests ordenadas, haciendo que se encuentren
     * en primer lugar aquellas requests que aún no fueron respondidas</p>
     * @return listado rdenado de requests
     */
    public List<RequestModel> get() {
        List<RequestModel> requests = new ArrayList<>();
        for (Request r: this.repository.findAll()) {
            requests.add(this.converter.convert(r));
        }
        Collections.sort(requests);
        return requests;
    }

    /**
     * Guarda la requests recibida
     * @param request a guardar
     * @return boolean indicador del éxito en la transacción
     */
    public boolean post(Request request) {
        try {
            this.repository.save(request);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Cambia el 'replied' de la requests de 'false' a 'true'
     * <p>El administrador puede indicar que ya respondió una solicitud cambiando su
     * estado de false(no respondida) a true(respondida). Esto hará que se encuentre
     * al final del listado para dar prioridad de respuesta a nuevas solicitudes</p>
     * @param id perteneciente a la request que se desea modificar
     * @return boolean idicador del éxito de la transacción
     * @see this get()
     */
    public boolean checkReplied(int id) {
        Request request = this.repository.getById(id);
        if (request != null) {
            request.setReplied(true);
            try {
                this.repository.save(request);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
