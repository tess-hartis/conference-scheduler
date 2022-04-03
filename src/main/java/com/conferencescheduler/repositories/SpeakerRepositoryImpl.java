package com.conferencescheduler.repositories;

import com.conferencescheduler.domain.Speaker;
import io.vavr.control.Option;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class SpeakerRepositoryImpl implements SpeakerRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Option<Speaker> findByIdOption(Long id) {

        List<Speaker> result = entityManager
                .createQuery("select s from speakers s where s.speaker_id = :id")
                .setParameter("id", id)
                .getResultList();

        if (result.isEmpty())
            return Option.none();

        else return Option.some(result.get(0));
    }

    @Override
    @Transactional
    public Integer deleteByIdCustom(Long id) {

        return entityManager
                .createQuery("delete from speakers where speaker_id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
