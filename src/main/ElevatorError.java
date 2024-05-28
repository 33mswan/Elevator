package main;

// We throw an elevatorError when the elevator receives incorrect info, is under maintenance, or when the help button is pressed.
public class ElevatorError extends Exception {
    public ElevatorError(String message){
        super(message);
    }
}
