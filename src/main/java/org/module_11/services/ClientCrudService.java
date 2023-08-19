package org.module_11.services;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.module_11.entity.Client;
import org.module_11.util.HibernateUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ClientCrudService {
    public void create(Client client) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("User creation failed");
        }
    }

    public Client getById(long id) {
        Client client;
        try (Session session = openSession()) {
            client = session.get(Client.class, id);
        }
        return Optional.ofNullable(client)
                .orElseThrow( () -> new NoSuchElementException("Client with that ID doesn't exist"));
    }


//    списку всіх сутностей
    public List<Client> listAll() {
    try (Session session = openSession()) {
        return session.createQuery("from Client", Client.class).list();
        }
    }
    public void update(long id, String name) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                client.setName(name);
                session.persist(client);
                transaction.commit();
            }
        } catch (SessionException e) {
            throw new RuntimeException();
        }
    }

        public void deleteById(long id) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                session.remove(client);
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
