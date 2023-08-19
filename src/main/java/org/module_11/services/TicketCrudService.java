package org.module_11.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.module_11.entity.Client;
import org.module_11.entity.Planet;
import org.module_11.entity.Ticket;
import org.module_11.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TicketCrudService {
    public void createTicket(Client client, Planet fromPlanet, Planet toPlanet) {
        Ticket ticket = new Ticket();
        ticket.setClientId(client);
        ticket.setFrom(fromPlanet);
        ticket.setTo(toPlanet);
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(ticket);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Ticket getById(long id) {
        Ticket ticket;
        try (Session session = openSession()) {
            {
                ticket = session.get(Ticket.class, id);
            }
            return Optional.ofNullable(ticket)
                    .orElseThrow(() -> new NoSuchElementException("Ticket with that ID doesn't exist"));
        }
    }

    public List<Ticket> listAll() {
        try (Session session = openSession()) {
            return session.createQuery("from Ticket ", Ticket.class).list();
        }
    }

    public void update(long id, Client clientId, Planet from, Planet to) {
        try (Session session = openSession()) {
            Transaction  transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                ticket.setClientId(clientId);
                ticket.setFrom(from);
                ticket.setTo(to);
                session.persist(ticket);
                transaction.commit();
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Ticket with that ID doesn't exist");
        }
    }

    public void deleteById(long id) {
        Ticket ticket;
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.remove(ticket);
                transaction.commit();
            }
        }
        catch (SessionException e) {
            throw new RuntimeException();
        }
    }

    public List<Ticket> getTicketsForClient(long clientId) {
        try (Session session = openSession()) {
            Query<Ticket> query = session.createQuery(
                    "SELECT t FROM Ticket t " +
                            "WHERE t.clientId.id = :clientId", Ticket.class
            );
            query.setParameter("clientId", clientId);
            return query.list();
        }

    }


    private Session openSession() {
        return HibernateUtil
                .getInstance()
                .getSessionFactory()
                .openSession();
    }

}