package org.module_11;

import org.module_11.entity.Client;
import org.module_11.entity.Planet;
import org.module_11.entity.Ticket;
import org.module_11.services.ClientCrudService;
import org.module_11.services.PlanetCrudService;
import org.module_11.services.TicketCrudService;
import org.module_11.util.DatabaseMigration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
////        DatabaseMigration migration = new DatabaseMigration();
////       migration.initDb();
//
        PlanetCrudService planetService = new PlanetCrudService();
        ClientCrudService clientService = new ClientCrudService();
        TicketCrudService ticketService = new TicketCrudService();

        // Створюємо тестові об'єкти
//        Planet planet1 = new Planet();
//        planet1.setId("P3");
//        planet1.setName("Neptun");
//
//        Planet planet2 = new Planet();
//        planet2.setId("P4");
//        planet2.setName("Uran");
//
//        planetService.create(planet1);
//        planetService.create(planet2);

//        Client client = new Client();
//        client.setName("New Client");
//        clientService.create(client);

        ticketService.createTicket(clientService.getById(3), planetService.getById("ERH3"), planetService.getById("MERC1"));
        System.out.println("Created ticket: " + ticketService.getById(3));

        ticketService.update(3, clientService.getById(3), planetService.getById("ERH3"), planetService.getById("MERC1"));
        System.out.println("Updated ticket: " + ticketService.getById(3));

        long clientId = 3; // id клієнта
        List<Ticket> tickets = ticketService.getTicketsForClient(clientId);
        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket);
        }
        }
//        ticketService.deleteById(1);
//        System.out.println("Deleted ticket with ID 1");

//        System.out.println("List of all tickets:");
//        ticketService.listAll().forEach(System.out::println);

    }


