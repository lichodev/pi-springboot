package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.QuestionConverter;
import com.ml.primerainfanciarest.entities.Question;
import com.ml.primerainfanciarest.models.QuestionModel;
import com.ml.primerainfanciarest.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Servicio encargado de la lógica relacionada con las questions
 * @author sole
 * @version 1.0
 */
@Service("QuestionService")
public class QuestionService {
    @Autowired
    @Qualifier("QuestionRepository")
    private QuestionRepository repository;

    @Autowired
    @Qualifier("QuestionConverter")
    private QuestionConverter converter;

    /**
     * Obtiene el listado de questions
     * <p>Obtiene el listado de questions ordenadas, haciendo que se encuentren
     * en primer lugar aquellas questions que aún no fueron respondidas</p>
     * @return listado ordenado de questions
     */
    public List<QuestionModel> get() {
        List<QuestionModel> questions = new ArrayList<>();
        for (Question q : this.repository.findAll()) {
            questions.add(this.converter.convert(q));
        }
        Collections.sort(questions);
        return questions;
    }

    /**
     * Guarda la question recibida
     * @param question a guardar
     * @return boolean que indica el éxito de la transacción
     */
    public boolean post(Question question) {
        try {
            this.repository.save(question);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Cambia el replied de la question de 'false' a 'true'
     * <p>El administrador puede indicar que ya respondió una pregunta cambiando su
     * estado de false(no respondida) a true(respondida). Esto hará que se encuentre
     * al final del listado para dar prioridad de respuesta a nuevas preguntas</p>
     * @param id perteneciente a la question que se desea modificar
     * @return boolean idicador del éxito de la transacción
     * @see this get()
     */
    public boolean checkReplied(int id) {
        Question q = this.repository.getById(id);
        if (q != null) {
            q.setReplied(true);
            try {
                this.repository.save(q);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
