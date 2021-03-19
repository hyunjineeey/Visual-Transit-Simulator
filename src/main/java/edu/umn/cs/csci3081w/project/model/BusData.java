package edu.umn.cs.csci3081w.project.model;

public class BusData {
  private String id;
  private Position position;
  private int numPassengers;
  private int capacity;
  private String color = "maroon";

  /**
   * Stores details of a bus.
   *
   * @param id   bus id
   * @param pos  position of the bus
   * @param pass number of passengers
   * @param cap  capacity of bus to be created
   */
  public BusData(String id, Position pos, int pass, int cap) {
    this.id = id;
    this.position = pos;
    this.numPassengers = pass;
    this.capacity = cap;
    this.color = "maroon";
  }

  public BusData() {
    this.position = new Position();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public int getNumPassengers() {
    return numPassengers;
  }

  public void setNumPassengers(int numPassengers) {
    this.numPassengers = numPassengers;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    this.color = color;
  }

}
