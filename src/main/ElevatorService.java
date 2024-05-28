package main;

import main.buttons.ElevatorInsideButtons;
import main.buttons.ElevatorOutsideButtons;

import java.util.ArrayList;

// The Elevator Service allows the user to utilize the Elevator.
public class ElevatorService {
    private Elevator elevator;
    private ElevatorOutsideButtons goingUpOrDown;

    public ElevatorService(){
       elevator = new Elevator();
       goingUpOrDown = ElevatorOutsideButtons.NONE;
    }
    public ElevatorService(Elevator elevator){
        this.elevator = elevator;
        goingUpOrDown = ElevatorOutsideButtons.NONE;
    }
    public String selectOutsideButton(ElevatorOutsideButtons upDown, int floor){
        try {
           return SelectedOutsideButtonLogic(upDown, floor);
        } catch(ElevatorError e) {
            return "Floor doesn't exist!";
        }
    }
    // enter negative numbers for basements
    public String selectInsideButton(int floor){
        try {
            return SelectedInsideButtonLogic(floor);
        } catch(ElevatorError e) {
            return "Floor doesn't exist!";
        }
    }
    public String selectInsideButton(ElevatorInsideButtons button) throws ElevatorError {
        if (button == null){
            return "";
        }
        switch(button){
            case CALLHELP:
                elevator.setMaintenanceMode(true);
                throw new ElevatorError("Calling Emergency Services! Elevator will not be serviceable until further notice. " +
                        "Use the complementary stairs!");
            case DOOROPEN:
                elevator.setIsDoorOpen(true);
                return "Door open";
            case DOORClOSE:
                elevator.setIsDoorOpen(false);
                return  "Door close";
            default:
                return "";
        }
    }
    // negative numbers for basements
    public String selectInsideButtons(ArrayList<Integer> buttons){
        if (buttons == null){
            return "";
        }
        buttons = sortElevatorButtons(buttons);
        for(int button : buttons){
            System.out.println(selectInsideButton(button));
        }
        elevator.setIsDoorOpen(false);
        return "Doors closing on floor " + elevator.getCurrentFloor();
    }
    public String setMaintenance(boolean underMaintenance) throws ElevatorError {
        elevator.setMaintenanceMode(underMaintenance);
        if(underMaintenance){
            System.out.println("Elevator is under Maintenance");
            throw new ElevatorError("Elevator is under Maintenance use stairs");
        } else {
            return "Elevator is serviceable again!";
        }
    }
    public static boolean floorDoesNotExist(Elevator elevator, int floor){
        return (elevator.getAmountOfFloorsGroundAndAbove() < floor || -elevator.getAmountOfFloorsBelowGround() > floor || floor == 0);
    }
    private void moveElevator(int floor){
        int current = elevator.getCurrentFloor();
        while (floor != elevator.getCurrentFloor())
            if(floor > current){
                elevator.setCurrentFloor(current++);
            }else{
                elevator.setCurrentFloor(current--);
            }
    }
    private void checkIfDoorIsOpen(){
        if (elevator.getIsDoorOpen()) {
            elevator.setIsDoorOpen(false);
        }
    }
    private String SelectedOutsideButtonLogic(ElevatorOutsideButtons upDown, int floor) throws ElevatorError {
        if (floorDoesNotExist(elevator, floor)) {
            throw new ElevatorError("Floor does not exist in this 3rd dimension");
        }
        checkIfDoorIsOpen();
        if (!elevator.isUnderMaintenance()) {
            goingUpOrDown = upDown;
            moveElevator(floor);
            elevator.setIsDoorOpen(true);
            return String.format("Door opens on floor %d, going %s", elevator.getCurrentFloor(), goingUpOrDown.toString());
        }
        return "Elevator is under maintenance use stairs";
    }
    private String SelectedInsideButtonLogic(int floor) throws ElevatorError {
        if (floorDoesNotExist(elevator, floor)) {
            throw new ElevatorError("Floor does not exist in this 3rd dimension");
        }
        System.out.println("Closing doors " + elevator.getCurrentFloor());
        elevator.setIsDoorOpen(false);
        moveElevator(floor);
        elevator.setIsDoorOpen(true);
        return "Door open on floor " + elevator.getCurrentFloor();
    }
    private ArrayList<Integer> sortElevatorButtons(ArrayList<Integer> buttons){
        if (goingUpOrDown.equals(ElevatorOutsideButtons.UP)){
            return SortByNextLevel.sortGoingUpFirst(buttons, elevator.getCurrentFloor());
        }else{
            return SortByNextLevel.sortGoingDownFirst(buttons, elevator.getCurrentFloor());
        }
    }

}
