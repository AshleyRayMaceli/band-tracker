import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Band Tracker");
  }

  @Test
  public void allBandsPageIsDisplayed() {
    goTo("http://localhost:4567/");
    click("a", withText("View All Bands"));
    assertThat(pageSource().contains("All Bands"));
  }

  @Test
  public void individualBandPageIsDisplayed() {
    Band testBand = new Band("Led Zeppelin");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    assertThat(pageSource()).contains("Led Zeppelin");
  }

  @Test
  public void bandFormIsDisplayed() {
    goTo("http://localhost:4567/");
    click("a", withText("Add New Band"));
    assertThat(pageSource()).contains("Add New Band");
  }

  @Test
  public void bandNameIsUpdated() {
    Band testBand = new Band("The White Stripes");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    fill("#update").with("Jack White");
    submit("#update-submit");
    assertThat(pageSource().contains("Jack White"));
  }

  @Test
  public void bandIsDeleted() {
    Band testBand = new Band("The Black Keys");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    submit("#delete-band");
    assertFalse(pageSource().contains("The Black Keys"));
  }

  @Test
  public void venueAddedToBand() {
    Band testBand = new Band("The Dear Hunter");
    testBand.save();
    Venue testVenue = new Venue("The Sinclair");
    testVenue.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    fill("#venue-name").with("The Sinclair");
    submit("#add-venue");
    assertThat(pageSource()).contains("The Sinclair");
  }

  @Test
  public void allVenuesPageIsDisplayed() {
    goTo("http://localhost:4567/");
    click("a", withText("View All Venues"));
    assertThat(pageSource().contains("All Venues"));
  }

  @Test
  public void individualVenuePageIsDisplayed() {
    Venue testVenue = new Venue("The Palladium");
    testVenue.save();
    String url = String.format("http://localhost:4567/venues/%d", testVenue.getId());
    goTo(url);
    assertThat(pageSource()).contains("The Palladium");
  }
}
