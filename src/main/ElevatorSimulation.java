package main;

import main.buttons.ElevatorInsideButtons;
import main.buttons.ElevatorOutsideButtons;

import java.util.ArrayList;
import java.util.Scanner;

// This looks ugly
// Didn't spend a lot of time on this I mostly built it so that you can test and see that the elevator works
// this will use the Default elevator just so I can pre-program button choices 5 ground and above floors 1 basement

public class ElevatorSimulation {

    public static void main(String[] args) throws ElevatorError {
        Scanner ui = new Scanner(System.in);
        System.out.println("would you like to run the elevator sim? Y/N");
        String answer = ui.next();
        // Edit the line below if you want to change the elevator
        Elevator elevator = new Elevator();
        ElevatorService elevatorService = new ElevatorService(elevator);
        while (answer.equalsIgnoreCase("Y")) {

            System.out.println("Input number for option \n 1: go up \n 2: go down " +
                    "\n 3: Start or end Maintenance \n quit Y/N");
            answer = ui.next();
            if (answer.equals("Y")){
                break;
            }
            if(answer.equals("1") || answer.equals("2") ){
                System.out.println("What Floor are you on? ");
                if (Integer.parseInt(answer) == 1) {
                    answer = ui.next();
                    System.out.println(elevatorService.selectOutsideButton(ElevatorOutsideButtons.UP, Integer.parseInt(answer)));
                } else {
                    answer = ui.next();
                    System.out.println(elevatorService.selectOutsideButton(ElevatorOutsideButtons.DOWN, Integer.parseInt(answer)));
                }
                if(!elevator.isUnderMaintenance() && !ElevatorService.floorDoesNotExist(elevator, Integer.parseInt(answer)) ) {
                    do {
                        System.out.println("Enter a buttons separate by comma. " +
                                " However, Call help open and close door can only be inputted alone" +
                                " \n CALLHELP \n OPENDOOR \n CLOSEDOOR \n -1: Basement \n 1: Level 1" +
                                " \n 2: Level 2 \n 3: Level 3 \n 4: Level 4 \n 5: Level 5");
                        answer = ui.next();
                        insideButtonOptions(answer, elevatorService);
                    } while (answer.equals("OPENDOOR") || answer.equals("CLOSEDOOR"));
                }
            }else if (answer.equals("3")){
                System.out.println("Do you want to set Maintenance Mode to true? Y/N");
                answer = ui.next();
                try {
                    System.out.println(elevatorService.setMaintenance(answer.equalsIgnoreCase("Y")));
                } catch(ElevatorError e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("would you like to use elevator again? Y/N");
            answer = ui.next();
        }

    }
    private static void insideButtonOptions(String answer, ElevatorService elevator) throws ElevatorError {
        if (answer.equals("CALLHELP")) {
            try {
                elevator.selectInsideButton(ElevatorInsideButtons.CALLHELP);
            } catch(ElevatorError e){
                System.out.println(e.getMessage());
            }
        } else if (answer.equals("OPENDOOR")) {
            System.out.println(elevator.selectInsideButton(ElevatorInsideButtons.DOOROPEN));
        } else if (answer.equals("CLOSEDOOR")) {
            System.out.println(elevator.selectInsideButton(ElevatorInsideButtons.DOORClOSE));
        } else if (answer.contains(",")) {
            ArrayList<Integer> floorButtonsClicked = new ArrayList<>();
            String[] buttonsClicked = answer.split(",");
            for (String button : buttonsClicked) {
                floorButtonsClicked.add(Integer.parseInt(button));
            }
            System.out.println(elevator.selectInsideButtons(floorButtonsClicked));
        } else {
            System.out.println(elevator.selectInsideButton(Integer.parseInt(answer)));
        }
    }
}