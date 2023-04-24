package ru.netolgy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {
    Ticket ticket = new Ticket(
            "Москва",
            "Самара",
            10000,
            12_00,
            15_30
    );
    Ticket ticket1 = new Ticket(
            "Москва",
            "Самара",
            4000,
            10_00,
            13_30
    );
    Ticket ticket2 = new Ticket(
            "Москва",
            "Чита",
            9000,
            14_00,
            17_30
    );

    @Test
    public void shouldCompareTickets() {
        Assertions.assertEquals(-1, ticket1.compareTo(ticket2));
        Assertions.assertEquals(1, ticket.compareTo(ticket1));
        Assertions.assertEquals(0, ticket2.compareTo(ticket2));
    }

    @Test
    public void shouldAddFindAllTickets() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket);
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);

        Ticket[] expected = {ticket, ticket1, ticket2};
        Assertions.assertArrayEquals(expected, aviaSouls.findAll());
    }

    @Test
    public void shouldFoundOneTicket() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket);
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);

        Ticket[] expected = {ticket2};
        Assertions.assertArrayEquals(expected, aviaSouls.search("Москва", "Чита"));
    }

    @Test
    public void shouldFNotFoundTicket() {
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket);
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);

        Ticket[] expected = {};
        Assertions.assertArrayEquals(expected, aviaSouls.search("Москва", "Новосибирск"));
    }

    @Test
    public void shouldAddSearchSortArraysTicketsToPrice() {
        Ticket ticket = new Ticket(
                "Москва",
                "Самара",
                10000,
                12_00,
                15_30
        );
        Ticket ticket1 = new Ticket(
                "Москва",
                "Самара",
                4000,
                10_00,
                13_30
        );
        Ticket ticket2 = new Ticket(
                "Москва",
                "Самара",
                9000,
                14_00,
                17_30
        );
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket);
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);

        Ticket[] expected = {ticket1, ticket2, ticket};
        Assertions.assertArrayEquals(expected, aviaSouls.search("Москва", "Самара"));
    }

    @Test
    public void shouldCompareTicketsForTime() {
        Ticket ticket = new Ticket(
                "Москва",
                "Самара",
                10000,
                10_00,
                16_30
        );
        Ticket ticket1 = new Ticket(
                "Москва",
                "Самара",
                4000,
                15_00,
                18_30
        );
        Ticket ticket2 = new Ticket(
                "Москва",
                "Самара",
                9000,
                11_00,
                14_30
        );
        AviaSouls aviaSouls = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();

        aviaSouls.add(ticket);
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);

        Assertions.assertEquals(1, comparator.compare(ticket1, ticket2));
        Assertions.assertEquals(-1, comparator.compare(ticket, ticket2));
        Assertions.assertEquals(0, comparator.compare(ticket1, ticket1));
    }

    @Test
    public void shouldSearchAndSortByTime() {
        Ticket ticket = new Ticket(
                "Москва",
                "Самара",
                10000,
                10_00,
                16_30
        );
        Ticket ticket1 = new Ticket(
                "Москва",
                "Самара",
                4000,
                15_00,
                18_30
        );
        Ticket ticket2 = new Ticket(
                "Москва",
                "Самара",
                9000,
                11_00,
                14_30
        );
        AviaSouls aviaSouls = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();

        aviaSouls.add(ticket);
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);

        Ticket[] expected = {ticket, ticket2, ticket1};
        Assertions.assertArrayEquals(expected, aviaSouls.searchAndSortBy("Москва", "Самара", comparator));
    }
}
