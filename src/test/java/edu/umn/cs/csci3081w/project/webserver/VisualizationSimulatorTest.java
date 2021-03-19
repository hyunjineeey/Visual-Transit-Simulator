package edu.umn.cs.csci3081w.project.webserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.Bus;
import edu.umn.cs.csci3081w.project.model.BusCreator;
import edu.umn.cs.csci3081w.project.model.PassengerFactory;
import edu.umn.cs.csci3081w.project.model.PassengerGenerator;
import edu.umn.cs.csci3081w.project.model.RandomPassengerGenerator;
import edu.umn.cs.csci3081w.project.model.Route;
import edu.umn.cs.csci3081w.project.model.Stop;
import edu.umn.cs.csci3081w.project.model.TestUtils;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class VisualizationSimulatorTest {

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
   * Test toggle pause.
   */
  @Test
  public void testTogglePauseTrue() {
    ConfigManager configM = new ConfigManager();
    MyWebServer webI = new MyWebServer();
    MyWebServerSession myWebServerSessionSpy = spy(MyWebServerSession.class);
    doNothing().when(myWebServerSessionSpy).sendJson(Mockito.isA(JsonObject.class));
    Session sessionDummy = mock(Session.class);
    myWebServerSessionSpy.onOpen(sessionDummy);

    VisualizationSimulator visualizationSimulator =
        new VisualizationSimulator(webI, configM, myWebServerSessionSpy);
    visualizationSimulator.setPaused(true);
    visualizationSimulator.togglePause();
  }

  /**
   * Test toggle pause.
   */
  @Test
  public void testTogglePauseFalse() {
    ConfigManager configM = new ConfigManager();
    MyWebServer webI = new MyWebServer();
    MyWebServerSession myWebServerSessionSpy = spy(MyWebServerSession.class);
    doNothing().when(myWebServerSessionSpy).sendJson(Mockito.isA(JsonObject.class));
    Session sessionDummy = mock(Session.class);
    myWebServerSessionSpy.onOpen(sessionDummy);

    VisualizationSimulator visualizationSimulator =
        new VisualizationSimulator(webI, configM, myWebServerSessionSpy);
    visualizationSimulator.setPaused(false);
    visualizationSimulator.togglePause();
  }

  /**
   * Test Start.
   */
  @Test
  public void testStart() {
    ConfigManager configM = new ConfigManager();
    MyWebServer webI = new MyWebServer();
    MyWebServerSession myWebServerSessionSpy = spy(MyWebServerSession.class);
    doNothing().when(myWebServerSessionSpy).sendJson(Mockito.isA(JsonObject.class));
    Session sessionDummy = mock(Session.class);
    myWebServerSessionSpy.onOpen(sessionDummy);

    VisualizationSimulator visualizationSimulator =
        new VisualizationSimulator(webI, configM, myWebServerSessionSpy);
    List<Integer> busStartTimingsParam = new ArrayList<>();
    busStartTimingsParam.add(1);
    busStartTimingsParam.add(2);
    busStartTimingsParam.add(3);

    // set prototypeRoutes
    Stop stop = new Stop(0, 44.972392, -93.243774);
    Stop stop2 = new Stop(1, 44.973580, -93.235071);
    Stop stop3 = new Stop(2, 44.975392, -93.226632);
    List<Stop> stops = new ArrayList<>(); //for route
    stops.add(stop);
    stops.add(stop2);
    stops.add(stop3);
    List<Double> distances = new ArrayList<>();
    distances.add(0.9712663713083954);
    distances.add(0.961379387775189);
    List<Double> probabilities = new ArrayList<>();
    probabilities.add(.15);
    probabilities.add(0.3);
    probabilities.add(.0);
    PassengerGenerator generator = new RandomPassengerGenerator(probabilities, stops);
    Route route1 = new Route("route1", stops, distances, 3, generator);
    Route route2 = new Route("route2", stops, distances, 3, generator);
    List<Route> routes = new ArrayList<>();
    routes.add(route1);
    routes.add(route2);

    configM.setRoutes(routes);

    visualizationSimulator.start(busStartTimingsParam, 3);
  }

  /**
   * Test adding bus observer.
   */
  @Test
  public void testAddBusObserver() {
    ConfigManager configM = new ConfigManager();
    MyWebServer webI = new MyWebServer();
    MyWebServerSession myWebServerSessionSpy = spy(MyWebServerSession.class);
    doNothing().when(myWebServerSessionSpy).sendJson(Mockito.isA(JsonObject.class));
    Session sessionDummy = mock(Session.class);
    myWebServerSessionSpy.onOpen(sessionDummy);

    VisualizationSimulator visualizationSimulator =
        new VisualizationSimulator(webI, configM, myWebServerSessionSpy);

    // set bus
    Stop stop1 = new Stop(0, 44.972392, -93.243774);
    Stop stop2 = new Stop(1, 44.973580, -93.235071);
    Stop stop3 = new Stop(2, 44.975392, -93.226632);
    List<Stop> stopsIn = new ArrayList<Stop>();
    stopsIn.add(stop1);
    stopsIn.add(stop2);
    stopsIn.add(stop3);
    List<Double> distancesIn = new ArrayList<Double>();
    distancesIn.add(0.008784);
    distancesIn.add(0.008631);
    List<Double> probabilitiesIn = new ArrayList<Double>();
    probabilitiesIn.add(.15);
    probabilitiesIn.add(0.3);
    probabilitiesIn.add(.025);
    PassengerGenerator generatorIn =
        new RandomPassengerGenerator(probabilitiesIn, stopsIn);
    Route testRouteIn = new Route("testRouteIn", stopsIn, distancesIn, 3, generatorIn);
    List<Stop> stopsOut = new ArrayList<>();
    stopsOut.add(stop3);
    stopsOut.add(stop2);
    stopsOut.add(stop1);
    List<Double> distancesOut = new ArrayList<>();
    distancesOut.add(0.008631);
    distancesOut.add(0.008784);
    List<Double> probabilitiesOut = new ArrayList<>();
    probabilitiesOut.add(.025);
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(.15);
    PassengerGenerator generatorOut =
        new RandomPassengerGenerator(probabilitiesOut, stopsOut);
    Route testRouteOut =
        new Route("testRouteIn", stopsOut, distancesOut, 3, generatorOut);
    Bus bus1 = new Bus("TestBus1", testRouteOut, testRouteIn, 5, 1);
    Bus bus2 = new Bus("TestBus2", testRouteOut, testRouteIn, 5, 1);
    Bus bus3 = new Bus("TestBus3", testRouteOut, testRouteIn, 5, 1);

    List<Bus> busses = new ArrayList<>();
    busses.add(bus1);
    busses.add(bus2);
    busses.add(bus3);
    visualizationSimulator.setBus(busses);
    visualizationSimulator.addBusObserver("TestBus1");

    assertEquals("TestBus1", bus1.getName());
  }

  /**
   * Test stopping observer.
   */
  @Test
  public void testStopObserver() {
    ConfigManager configM = new ConfigManager();
    MyWebServer webI = new MyWebServer();
    MyWebServerSession myWebServerSessionSpy = spy(MyWebServerSession.class);
    doNothing().when(myWebServerSessionSpy).sendJson(Mockito.isA(JsonObject.class));
    Session sessionDummy = mock(Session.class);
    myWebServerSessionSpy.onOpen(sessionDummy);

    VisualizationSimulator visualizationSimulator =
        new VisualizationSimulator(webI, configM, myWebServerSessionSpy);

    Route testRoute1 = TestUtils.createRoute();
    Route testRoute2 = TestUtils.createRoute();
    Route testRoute3 = TestUtils.createRoute();

    List<Route> prototypeRoutes = new ArrayList<>();
    prototypeRoutes.add(testRoute1);
    prototypeRoutes.add(testRoute2);
    prototypeRoutes.add(testRoute3);

    visualizationSimulator.setPrototypeRoutes(prototypeRoutes);
    visualizationSimulator.addStopObserver("0");
  }

  /**
   * Test stopping observer.
   */
  @Test
  public void testStopObserverConfigM() {
    ConfigManager configM = new ConfigManager();

    configM.setTesting(true);

    MyWebServer webI = new MyWebServer();
    MyWebServerSession myWebServerSessionSpy = spy(MyWebServerSession.class);
    doNothing().when(myWebServerSessionSpy).sendJson(Mockito.isA(JsonObject.class));
    Session sessionDummy = mock(Session.class);
    myWebServerSessionSpy.onOpen(sessionDummy);

    VisualizationSimulator visualizationSimulator =
        new VisualizationSimulator(webI, configM, myWebServerSessionSpy);

    Route testRoute1 = TestUtils.createRoute();
    Route testRoute2 = TestUtils.createRoute();
    Route testRoute3 = TestUtils.createRoute();

    List<Route> prototypeRoutes = new ArrayList<>();
    prototypeRoutes.add(testRoute1);
    prototypeRoutes.add(testRoute2);
    prototypeRoutes.add(testRoute3);

    visualizationSimulator.setPrototypeRoutes(prototypeRoutes);
    visualizationSimulator.addStopObserver("0");
  }

  /**
   * Test updating.
   */
  @Test
  public void testUpdate() {
    ConfigManager configM = new ConfigManager();
    MyWebServer webI = new MyWebServer();
    MyWebServerSession myWebServerSessionSpy = spy(MyWebServerSession.class);
    doNothing().when(myWebServerSessionSpy).sendJson(Mockito.isA(JsonObject.class));
    Session sessionDummy = mock(Session.class);
    myWebServerSessionSpy.onOpen(sessionDummy);

    VisualizationSimulator visualizationSimulator =
        new VisualizationSimulator(webI, configM, myWebServerSessionSpy);
    List<Integer> busStartTimingsParam = new ArrayList<>();
    busStartTimingsParam.add(1);
    busStartTimingsParam.add(2);
    busStartTimingsParam.add(3);

    // set prototypeRoutes
    Route route1 = TestUtils.createRoute();
    Route route2 = TestUtils.createRoute();
    Route route3 = TestUtils.createRoute();
    Route route4 = TestUtils.createRoute();
    List<Route> prototypeRoutes = new ArrayList<>();
    prototypeRoutes.add(route1);
    prototypeRoutes.add(route2);
    prototypeRoutes.add(route3);
    prototypeRoutes.add(route4);
    visualizationSimulator.setPrototypeRoutes(prototypeRoutes);

    //set timeSinceLastBus
    List<Integer> timeSinceLastBus = new ArrayList<>();
    timeSinceLastBus.add(1);
    timeSinceLastBus.add(5);
    timeSinceLastBus.add(3);

    visualizationSimulator.setNumTimeSteps(1);
    visualizationSimulator.setTimeSinceLastBus(timeSinceLastBus);

    visualizationSimulator.update();
  }

  /**
   * Test updating.
   */
  @Test
  public void testUpdateInside() {
    ConfigManager configM = new ConfigManager();
    MyWebServer webI = new MyWebServer();
    MyWebServerSession myWebServerSessionSpy = spy(MyWebServerSession.class);
    doNothing().when(myWebServerSessionSpy).sendJson(Mockito.isA(JsonObject.class));
    Session sessionDummy = mock(Session.class);
    myWebServerSessionSpy.onOpen(sessionDummy);

    VisualizationSimulator visualizationSimulator =
        new VisualizationSimulator(webI, configM, myWebServerSessionSpy);
    List<Integer> busStartTimingsParam = new ArrayList<>();
    busStartTimingsParam.add(1);
    busStartTimingsParam.add(2);
    busStartTimingsParam.add(3);

    // set prototypeRoutes
    Route route1 = TestUtils.createRoute();
    Route route2 = TestUtils.createRoute();
    Route route3 = TestUtils.createRoute();
    Route route4 = TestUtils.createRoute();
    List<Route> prototypeRoutes = new ArrayList<>();
    prototypeRoutes.add(route1);
    prototypeRoutes.add(route2);
    prototypeRoutes.add(route3);
    prototypeRoutes.add(route4);
    visualizationSimulator.setPrototypeRoutes(prototypeRoutes);

    //set timeSinceLastBus
    List<Integer> timeSinceLastBus = new ArrayList<>();
    timeSinceLastBus.add(1);
    timeSinceLastBus.add(5);
    timeSinceLastBus.add(3);

    // set BusCreator
    BusCreator busCreator = new BusCreator() {
      @Override
      public Bus createBus(String name, Route outbound, Route inbound, double speed) {
        return null;
      }
    };

    // set busStartTimings
    List<Integer> busStartTimings = new ArrayList<>();
    busStartTimings.add(1);
    busStartTimings.add(2);
    busStartTimings.add(3);

    // set busses
    Bus bus1 = TestUtils.createBus();
    Bus bus2 = TestUtils.createBus();
    List<Bus> busses = new ArrayList<>();
    busses.add(bus1);
    busses.add(bus2);

    // set route
    Route testRoute = TestUtils.createRoute();
    testRoute.setDestinationStopIndex(10);
    bus2.setOutgoingRoute(testRoute);

    visualizationSimulator.setBus(busses);
    visualizationSimulator.setTimeSinceLastBus(timeSinceLastBus);
    visualizationSimulator.setBusStartTimings(busStartTimings);
    visualizationSimulator.setSimulationConcreteBusCreator(busCreator);

    visualizationSimulator.update();
  }
}
