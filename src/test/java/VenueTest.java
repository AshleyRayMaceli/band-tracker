import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Venue_instantiatesCorrectly_true() {
    Venue myVenue = new Venue("House of Blues");
    assertEquals(true, myVenue instanceof Venue);
  }

  @Test
  public void getName_venueInstantiatesWithName_String() {
    Venue myVenue = new Venue("The Paramount");
    assertEquals("The Paramount", myVenue.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfVenuesAreTheSame_true() {
    Venue firstVenue = new Venue("The Downtown");
    Venue secondVenue = new Venue("The Downtown");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesVenueCorrectly_1() {
    Venue newVenue = new Venue("Mississippi Studios");
    newVenue.save();
    assertEquals(1, Venue.all().size());
  }

}
