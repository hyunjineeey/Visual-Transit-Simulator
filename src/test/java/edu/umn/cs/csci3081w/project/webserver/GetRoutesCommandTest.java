package edu.umn.cs.csci3081w.project.webserver;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.PassengerFactory;
import edu.umn.cs.csci3081w.project.model.Position;
import edu.umn.cs.csci3081w.project.model.RandomPassengerGenerator;
import edu.umn.cs.csci3081w.project.model.RouteData;
import edu.umn.cs.csci3081w.project.model.StopData;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class GetRoutesCommandTest {

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
  public void testExecute() {
    MyWebServerSession myWebServerSessionSpy = spy(MyWebServerSession.class);

    doNothing().when(myWebServerSessionSpy).sendJson(Mockito.isA(JsonObject.class));
    Session sessionDummy = mock(Session.class);
    myWebServerSessionSpy.onOpen(sessionDummy);
    JsonObject commandFromClient = new JsonObject();
    commandFromClient.addProperty("command", "getRoutes");
    myWebServerSessionSpy.onMessage(commandFromClient.toString());
    ArgumentCaptor<JsonObject> messageCaptor = ArgumentCaptor.forClass(JsonObject.class);
    verify(myWebServerSessionSpy).sendJson(messageCaptor.capture());
    JsonObject commandToClient = messageCaptor.getValue();

    // set routes
    StopData testStopData1 = new StopData("0", new Position(44.972392, -93.243774), 5);
    StopData testStopData2 = new StopData("1", new Position(44.972392, -93.243774), 5);
    StopData testStopData3 = new StopData("2", new Position(44.972392, -93.243774), 5);
    ArrayList<StopData> stopDataArrayList = new ArrayList<>();
    stopDataArrayList.add(testStopData1);
    stopDataArrayList.add(testStopData2);
    stopDataArrayList.add(testStopData3);

    RouteData routeData1 = new RouteData();
    routeData1.setId("route1");
    routeData1.setStops(stopDataArrayList);

    RouteData routeData2 = new RouteData();
    routeData2.setId("route1");
    routeData2.setStops(stopDataArrayList);

    List<RouteData> routes = new ArrayList<>();
    routes.add(routeData1);
    routes.add(routeData2);

    MyWebServer myWsSpy = spy(MyWebServer.class);
    myWsSpy.setRouteData(routes);
    myWsSpy.setTesting(true);
  }
}
