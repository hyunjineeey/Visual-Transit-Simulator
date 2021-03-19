package edu.umn.cs.csci3081w.project.webserver;

import edu.umn.cs.csci3081w.project.model.Bus;
import edu.umn.cs.csci3081w.project.model.BusData;
import edu.umn.cs.csci3081w.project.model.Route;
import edu.umn.cs.csci3081w.project.model.Stop;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Logger {

  private static Logger instance;
  private String path;
  private FileWriter fw;
  private boolean testing = false;

  private Logger(String path) {
    this.path = path;
    try {
      this.fw = new FileWriter(path);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Logger.
   */
  public Logger() {
    String path = "log.txt";

    try {
      this.fw = new FileWriter(path);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }


  /**
   * Logs bus details.
   *
   * @param busses                List of Busses
   * @param simulationTimeElapsed current time step
   */
  public void logBusses(List<Bus> busses, int simulationTimeElapsed) {
    for (Bus bus : busses) {
      BusData busData = bus.getBusData();
      try {
        fw.append("BUS," + simulationTimeElapsed + ","
            + busData.getId() + ","
            + busData.getPosition().getXcoordLoc() + ","
            + busData.getPosition().getYcoordLoc() + ","
            + busData.getNumPassengers() + ","
            + busData.getCapacity() + "\n");
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Logs bus details.
   *
   * @param routes                List of route objects
   * @param simulationTimeElapsed current time step
   */
  public void logRoutes(List<Route> routes, int simulationTimeElapsed) {
    for (Route route : routes) {
      for (Stop stop : route.getStops()) {
        try {
          fw.append("STOP," + simulationTimeElapsed + ","
              + stop.getId() + "," + stop.getLongitude()
              + "," + stop.getLatitude() + ","
              + stop.getNumPassengersPresent() + "\n");
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  /**
   * Flush and closes the file writer.
   */
  public void closeLogger() {
    try {
      fw.flush();
      fw.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Logs bus details.
   *
   * @param path path of the output file.
   * @return Logger object.
   */

  public static Logger getInstance(String path) {
    if (instance == null) {
      instance = new Logger(path);
    }
    return instance;
  }
}
