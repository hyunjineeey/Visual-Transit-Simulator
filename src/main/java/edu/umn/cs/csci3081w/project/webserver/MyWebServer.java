package edu.umn.cs.csci3081w.project.webserver;

import edu.umn.cs.csci3081w.project.model.BusData;
import edu.umn.cs.csci3081w.project.model.Position;
import edu.umn.cs.csci3081w.project.model.RouteData;
import edu.umn.cs.csci3081w.project.model.StopData;
import java.util.ArrayList;
import java.util.List;

public class MyWebServer implements WebInterface {

  public List<BusData> busses;
  public List<RouteData> routes;
  public BusData found;
  private boolean testing = false;

  /**
   * Constructor.
   */
  public MyWebServer() {
    busses = new ArrayList<BusData>();
    routes = new ArrayList<RouteData>();
  }

  /**
   * Updates a bus in the web server.
   *
   * @param tempData incoming data
   * @param deleted if bus was deleted
   */
  public void updateBus(BusData tempData, boolean deleted) {
    BusData found = null;
    for (BusData bd : busses) {
      if (bd.getId().equals(tempData.getId())) {
        found = bd;
      }
    }

    if (!testing) {
      found = null;
    }

    if (found != null) {
      if (deleted) {
        busses.remove(found);
        return;
      }
      int index = busses.indexOf(found);
      busses.get(index).setId(tempData.getId());
      busses.get(index).setPosition(tempData.getPosition());
      busses.get(index).setNumPassengers(tempData.getNumPassengers());
      busses.get(index).setCapacity(tempData.getCapacity());
    } else {
      busses.add(tempData);
    }
  }

  /**
   * Updates route in the web server.
   *
   * @param tempData incoming data.
   *
   * @param deleted if route was deleted
   */
  public void updateRoute(RouteData tempData, boolean deleted) {
    RouteData found = null;
    for (RouteData rd : routes) {
      if (rd.getId().equals(tempData.getId())) {
        found = rd;
      }
    }
    if (found != null) {
      if (deleted) {
        routes.remove(found);
        return;
      }
      int index = routes.indexOf(found);
      routes.get(index).setId(tempData.getId());
      routes.get(index).setStops(tempData.getStops());
    } else {
      routes.add(tempData);
    }
  }

  public void setTesting(boolean testing) {
    this.testing = testing;
  }

  public void setBusData(List<BusData> busses) {
    this.busses = busses;
  }

  public void setRouteData(List<RouteData> routes) {
    this.routes = routes;
  }

  /**
   * Update bus data.
   *
   * @return busses list of BusData
   */
  public List<BusData> getBusses() {
    BusData busData1 = new BusData("0", new Position(44.972392, -93.243774), 8, 30);
    BusData busData2 = new BusData("1", new Position(42.972392, -91.243774), 4, 60);

    List<BusData> busses = new ArrayList<>();
    busses.add(busData1);
    busses.add(busData2);

    return busses;
  }

  /**
   * Update route data.
   *
   * @return routes list of RouteData
   */
  public List<RouteData> getRoutes() {
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

    return routes;
  }
}
