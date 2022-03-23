package com.example.conferencescheduler.repositories;


import com.example.conferencescheduler.domain.Session;
import io.vavr.API;
import io.vavr.control.Option;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SessionRepositoryImpl implements SessionRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Option<Session> findByIdOption (Long id) {

        Query query = entityManager
                .createQuery("SELECT s FROM sessions s WHERE s.session_id = :id");

        List<Session> result = query.setParameter("id", id).getResultList();

        if (result.isEmpty())
            return Option.none();

        else return Option.some(result.get(0));
    }

    @Override
    @Transactional
    public Integer deleteByIdOption (Long id) {

        return entityManager
                .createQuery("DELETE FROM sessions WHERE session_id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
