import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {

    @Test
    void shouldFindAndSortTickets() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 5000, "FRU", "LED", 90);
        Ticket ticket2 = new Ticket(2, 7000, "FRU", "LED", 95);
        Ticket ticket3 = new Ticket(3, 4000, "VKO", "LED", 85);
        Ticket ticket4 = new Ticket(4, 4500, "FRU", "LED", 100);
        Ticket ticket5 = new Ticket(5, 3000, "SVO", "AER", 120);

        repository.add(ticket1);
        repository.add(ticket2);
        repository.add(ticket3);
        repository.add(ticket4);
        repository.add(ticket5);

        Ticket[] expected = {ticket4, ticket1, ticket2};
        Ticket[] actual = manager.findAll("FRU", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindTickets() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 5000, "FRU", "VKO", 90);
        Ticket ticket2 = new Ticket(2, 7000, "FRU", "VKO", 95);

        repository.add(ticket1);
        repository.add(ticket2);

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("FRU", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSingleTicket() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 5000, "FRU", "LED", 90);
        Ticket ticket2 = new Ticket(2, 7000, "VKO", "FRU", 120);

        repository.add(ticket1);
        repository.add(ticket2);

        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.findAll("VKO", "FRU");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSortTicketsByPrice() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 3000, "FRU", "LED", 90);
        Ticket ticket2 = new Ticket(2, 1000, "FRU", "LED", 95);
        Ticket ticket3 = new Ticket(3, 2000, "FRU", "LED", 85);

        repository.add(ticket1);
        repository.add(ticket2);
        repository.add(ticket3);

        Ticket[] expected = {ticket2, ticket3, ticket1};
        Ticket[] actual = manager.findAll("FRU", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    void testTicketComparison() {
        Ticket cheaper = new Ticket(1, 1000, "FRU", "LED", 90);
        Ticket moreExpensive = new Ticket(2, 2000, "FRU", "LED", 95);

        assertTrue(cheaper.compareTo(moreExpensive) < 0);
        assertTrue(moreExpensive.compareTo(cheaper) > 0);
        assertEquals(0, cheaper.compareTo(cheaper));
    }
}