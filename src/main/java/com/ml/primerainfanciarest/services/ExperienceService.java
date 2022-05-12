package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.ExperienceConverter;
import com.ml.primerainfanciarest.entities.Experience;
import com.ml.primerainfanciarest.entities.Reminder;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.ExperienceModel;
import com.ml.primerainfanciarest.models.ReminderModel;
import com.ml.primerainfanciarest.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Servicio encargado de la lógica relacionada con las experiences
 * @author sole
 * @version 1.0
 */
@Service("ExperienceService")
public class ExperienceService {

    @Autowired
    @Qualifier("ExperienceRepository")
    private ExperienceRepository repository;

    @Autowired
    @Qualifier("ExperienceConverter")
    private ExperienceConverter converter;

    /**
     * Obtiene el listado de todas las experiences, cuyo estado sea igual al recibido
     * <p>Las experiences con status = true son aquellas que ya fueron aprobadas por un
     * administrador para ser exhibidas en la página accesible para el usuario sin loguear.
     * Mientras que las experiences con status = false, son aquellas que aún no fueron
     * aprobadas, por lo que sólo serán visiblespara los administradores hasta que uno
     * de estos cambie su status a true</p>
     * @param status varía según el método del controllerque esté haciendo el llamado
     * @return listado de experiences con el status correspondiente
     * @see com.ml.primerainfanciarest.controllers.ExperienceController getAccept()
     * @see com.ml.primerainfanciarest.controllers.ExperienceController getPending()
     */
    public List<ExperienceModel> getByStatus(boolean status) {
        List<ExperienceModel> experiences = new ArrayList<>();
        for (Experience e: this.repository.findAllByStatus(status)) {
            if (e.getImage() != null)
                e.setImage(FileHelper.decompressBytes(e.getImage()));
            experiences.add(this.converter.convert(e));
        }
        Collections.sort(experiences);
        return experiences;
    }

    /**
     * Guarda una experience
     * @param experience a guardar
     * @return boolean indicador del éxito e la transacción
     */
    public boolean post(Experience experience) {
        try {
            repository.save(experience);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene la experiencia coincidente con el id recibido
     * <p>Cambia el status a true haciendo que esta sea visible para los usuarios
     * no logueados</p>
     * @param id identificador de la experience que se desea modificar
     * @return boolean indicador del éxito en la transacción
     * @see this getByStatus()
     */
    public boolean accept(int id) {
        Experience e = this.repository.findById(id);
        e.setStatus(true);
        try {
            this.repository.save(e);
            return true;
        } catch (Exception er) {
            return false;
        }
    }

    /**
     * Elimina una experience
     * <p>Si existe una experiencia con el id recibido por parámetro la elimina de la
     * base de datos</p>
     * <p>El administrador tiene la posibilidad de hacer visible una experience
     * o directamente eliminarla del sistema</p>
     * @param id identificador de la experience a eliminar
     * @return boolean indicador del éxito en la transacción
     */
    public boolean delete(int id) {
        try {
            if (this.repository.existsById(id)) {
                this.repository.deleteById((Serializable) id);
                return true;
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }
}
