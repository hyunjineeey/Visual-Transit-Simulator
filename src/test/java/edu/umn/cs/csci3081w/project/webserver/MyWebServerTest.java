package edu.umn.cs.csci3081w.project.webserver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.umn.cs.csci3081w.project.model.BusData;
import edu.umn.cs.csci3081w.project.model.PassengerFactory;
import edu.umn.cs.csci3081w.project.model.Position;
import edu.umn.cs.csci3081w.project.model.RandomPassengerGenerator;
import edu.umn.cs.csci3081w.project.model.RouteData;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyWebServerTest {
  /**
   * Setup deterministic operations before each test runs.
   */
  @BeforeEach
  public void setUp() {
    PassengerFactory.DETERMINISTIC = true;
    PassengerFactory.DETERMINISTIC_NAMES_COUNT = 0;
    PassengerFactory.DETERMINISTIC_DESTINATION_COUNT = 0;
    RandomPassengerGenerator.DETERMINISTIC = true;
  }

  /**
   * Test command for initializing the simulation.
   */
  @Test
  public void testUpdateBusDeletedTrue() {
    MyWebServer myWebServer = new MyWebServer();
    BusData tempData = new BusData("0", new Position(34.9723, -13.2434), 3, 60);
    BusData tempData2 = new BusData("1", new Position(44.972392, -93.243774), 0, 30);

    List<BusData> busses = new ArrayList<>();
    busses.add(tempData);
    busses.add(tempData2);

    myWebServer.setBusData(busses);
    myWebServer.updateBus(tempData, true);
    assertEquals("0", tempData.getId());
  }

  /**
   * Test command for initializing the simulation.
   */
  @Test
  public void testUpdateBusDeletedFalse() {
    MyWebServer myWebServer = new MyWebServer();
    BusData tempData = new BusData("0", new Position(34.9723, -13.2434), 3, 60);
    BusData tempData2 = new BusData("1", new Position(44.972392, -93.243774), 0, 30);

    List<BusData> busses = new ArrayList<>();
    busses.add(tempData);
    busses.add(tempData2);

    myWebServer.setBusData(busses);

    myWebServer.updateBus(tempData, false);
    assertEquals("0", tempData.getId());
  }

  /**
   * Test command for initializing the simulation.
   */
  @Test
  public void testUpdateBusFoundNullDeletedTrue() {
    MyWebServer myWebServer = new MyWebServer();
    BusData tempData = new BusData("0", new Position(34.9723, -13.2434), 3, 60);
    BusData tempData2 = new BusData("1", new Position(44.972392, -93.243774), 0, 30);

    List<BusData> busses = new ArrayList<>();
    busses.add(tempData);
    busses.add(tempData2);

    myWebServer.setBusData(busses);
    myWebServer.setTesting(true);
    myWebServer.updateBus(tempData, true);
    assertEquals("0", tempData.getId());
  }

  /**
   * Test command for initializing the simulation.
   */
  @Test
  public void testUpdateBusFoundNullDeletedFalse() {
    MyWebServer myWebServer = new MyWebServer();
    BusData tempData = new BusData("0", new Position(34.9723, -13.2434), 3, 60);
    BusData tempData2 = new BusData("1", new Position(44.972392, -93.243774), 0, 30);

    List<BusData> busses = new ArrayList<>();
    busses.add(tempData);
    busses.add(tempData2);

    myWebServer.setBusData(busses);
    myWebServer.setTesting(true);
    myWebServer.updateBus(tempData, false);
    assertEquals("0", tempData.getId());
  }

  /**
   * Test command for initializing the simulation.
   */
  @Test
  public void testUpdateRouteDeletedFalse() {
    MyWebServer myWebServer = new MyWebServer();
    RouteData tempData1 = new RouteData();
    RouteData tempData2 = new RouteData();

    List<RouteData> routes = new ArrayList<>();
    routes.add(tempData1);
    routes.add(tempData2);

    myWebServer.setRouteData(routes);
    myWebServer.updateRoute(tempData1, false);
    assertEquals("", tempData1.getId());
  }

  /**
   * Test command for initializing the simulation.
   */
  @Test
  public void testUpdateRouteDeletedTrue() {
    MyWebServer myWebServer = new MyWebServer();
    RouteData tempData1 = new RouteData();
    RouteData tempData2 = new RouteData();

    List<RouteData> routes = new ArrayList<>();
    routes.add(tempData1);
    routes.add(tempData2);

    myWebServer.setRouteData(routes);
    myWebServer.updateRoute(tempData1, true);
    assertEquals("", tempData1.getId());
  }
}
