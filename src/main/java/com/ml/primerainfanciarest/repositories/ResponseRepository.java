package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.Response;
import com.ml.primerainfanciarest.models.ResponseJoinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.List;

@Repository("ResponseRepository")
public interface ResponseRepository extends JpaRepository<Response, Serializable> {

    @Query("SELECT new com.ml.primerainfanciarest.models.ResponseJoinModel (q.text, r.text) FROM Response r JOIN Question q on q.id = r.questionId")
    public List<ResponseJoinModel> get();
}
