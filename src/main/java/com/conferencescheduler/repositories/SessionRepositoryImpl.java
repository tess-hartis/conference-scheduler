package com.conferencescheduler.repositories;


import com.conferencescheduler.domain.Session;
import io.vavr.control.Option;
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
                .createQuery("select s from sessions s where s.session_id = :id");

        List<Session> result = query.setParameter("id", id).getResultList();

        if (result.isEmpty())
            return Option.none();

        else return Option.some(result.get(0));
    }

    @Override
    @Transactional
    public Integer deleteByIdCustom(Long id) {

        return entityManager
                .createQuery("delete from sessions where session_id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
