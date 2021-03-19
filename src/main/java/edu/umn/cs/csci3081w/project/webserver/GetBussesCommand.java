package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.BusData;
import java.util.List;

public class GetBussesCommand extends MyWebServerCommand {

  private MyWebServer myWs;

  public GetBussesCommand(MyWebServer ws) {
    this.myWs = ws;
  }

  /**
   * Retrieves busses information from the simulation.
   *
   * @param session current simulation session
   * @param command the get busses command content
   * @param state   the state of the simulation session
   */
  @Override
  public void execute(MyWebServerSession session, JsonObject command,
                      MyWebServerSessionState state) {
    List<BusData> busses = myWs.busses;
    JsonObject data = new JsonObject();
    data.addProperty("command", "updateBusses");
    JsonArray bussesArray = new JsonArray();
    for (int i = 0; i < busses.size(); i++) {
      JsonObject s = new JsonObject();
      s.addProperty("id", busses.get(i).getId());
      s.addProperty("numPassengers", (double) busses.get(i).getNumPassengers());
      s.addProperty("capacity", (double) busses.get(i).getCapacity());
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("x", busses.get(i).getPosition().getXcoordLoc());
      jsonObject.addProperty("y", busses.get(i).getPosition().getYcoordLoc());
      s.add("position", jsonObject);

      JsonObject color = new JsonObject();
      if (busses.get(i).getColor().equals("gold")) {
        // bus is incoming (gold)
        color.addProperty("red", 255);
        color.addProperty("green", 215);
        color.addProperty("blue", 0);
        color.addProperty("alpha", 255);
      } else { // maroon
        color.addProperty("red", 128);
        color.addProperty("green", 0);
        color.addProperty("blue", 0);
        color.addProperty("alpha", 255);
      }
      s.add("color", color);

      bussesArray.add(s);
    }
    data.add("busses", bussesArray);
    session.sendJson(data);
  }

}