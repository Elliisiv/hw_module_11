package org.module_11.services;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.module_11.entity.Planet;
import org.module_11.util.HibernateUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PlanetCrudService {
    public void  create(Planet planet) {
        try (Session session = openSession()) {
            Transaction  transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException("Planet creation failed");
        }
    }
    public Planet getById(String id) {
        Planet planet;
        try (Session session = openSession()) {
            planet = session.get(Planet.class, id);
        }
        return Optional.ofNullable(planet)
                .orElseThrow(() -> new NoSuchElementException("Planet with that ID doesn't exist"));
    }

    public void update(String id, String name) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                planet.setName(name);
                session.persist(planet);
                transaction.commit();
            }
        } catch (SessionException e) {
            throw new RuntimeException();
        }
    }
    public List<Planet> listAll() {
        try (Session session = openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        }
    }

    public void deleteById(String id) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.remove(planet);
            }
            transaction.commit();
        } catch (SessionException e) {
            throw new RuntimeException();
        }
    }
    private Session openSession() {
        return HibernateUtil
                .getInstance()
                .getSessionFactory()
                .openSession();
    }
}
