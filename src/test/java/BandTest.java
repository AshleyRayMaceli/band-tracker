import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Band_instantiatesCorrectly_true() {
    Band myBand = new Band("Coheed and Cambria");
    assertEquals(true, myBand instanceof Band);
  }

  @Test
  public void getName_bandInstantiatesWithName_String() {
    Band myBand = new Band("The Dear Hunter");
    assertEquals("The Dear Hunter", myBand.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBandsAreTheSame_true() {
    Band firstBand = new Band("The 1975");
    Band secondBand = new Band("The 1975");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesBandCorrectly_1() {
    Band newBand = new Band("Panic! at the Disco");
    newBand.save();
    assertEquals(1, Band.all().size());
  }

  @Test
  public void find_findsBandInDatabase_true() {
    Band myBand = new Band("Mae");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void update_updatesBandName_true() {
    Band myBand= new Band("The Deer Hunter");
    myBand.save();
    myBand.update("The Dear Hunter");
    assertEquals("The Dear Hunter", Band.find(myBand.getId()).getName());
  }

  @Test
  public void addVenue_addVenueToBand() {
    Venue myVenue = new Venue("Aladdin Theater");
    myVenue.save();
    Band myBand = new Band("Purity Ring");
    myBand.save();
    myBand.addVenue(myVenue);
    Venue savedVenue = myBand.getVenues().get(0);
    assertTrue(myVenue.equals(savedVenue));
  }

  // @Test Need to write addBand()
  // public void getVenues_returnsAllVenues_List() {
  //   Venue myVenue = new Venue("The Middle East");
  //   myVenue.save();
  //   Band myBand = new Band("Florence + the Machine");
  //   myBand.save();
  //   myVenue.addBand(myBand);
  //   List savedVenues = myBand.getVenues();
  //   assertEquals(1, savedVenues.size());
  // }

}
