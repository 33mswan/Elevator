package main;

// This is the elevator object

public class Elevator {
    private final int amountOfFloorsGroundAndAbove;
    private final int amountOfFloorsBelowGround;
    private int currentFloor;
    private boolean underMaintenance;
    private boolean isDoorOpen;

    public Elevator(){
        amountOfFloorsGroundAndAbove = 5;
        amountOfFloorsBelowGround = 1;
        currentFloor = 1;
        underMaintenance = false;
        isDoorOpen =false;
    }
    public Elevator(int floorsGroundAndAbove, int floorsBelowGround){
        amountOfFloorsGroundAndAbove = floorsGroundAndAbove;
        amountOfFloorsBelowGround = floorsBelowGround;
        currentFloor = 1;
        underMaintenance = false;
        isDoorOpen =false;
    }
    public int getAmountOfFloorsGroundAndAbove(){
        return amountOfFloorsGroundAndAbove;
    }
    public int getAmountOfFloorsBelowGround(){
        return amountOfFloorsBelowGround;
    }
    public int getCurrentFloor(){
        return currentFloor;
    }
    public void setCurrentFloor(int floor){
        currentFloor = floor;
    }
    public boolean isUnderMaintenance(){
        return underMaintenance;
    }
    public void setMaintenanceMode(boolean needsMaintenance){
        underMaintenance = needsMaintenance;
    }
    public boolean getIsDoorOpen(){
        return isDoorOpen;
    }
    public void setIsDoorOpen(boolean isOpen){
        isDoorOpen = isOpen;
    }
}
