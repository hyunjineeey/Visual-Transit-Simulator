package edu.umn.cs.csci3081w.project.model;

public class GoldPaint extends BusDecorator {
  public GoldPaint(Bus bus) {
    super(bus);
  }

  /**
   * Paints bus gold.
   */
  public void paintBus() {

    this.bus.getBusData().setColor("gold");

  }

}
