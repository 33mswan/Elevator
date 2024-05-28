package main;

import java.util.ArrayList;
import java.util.Comparator;

// I wanted to sort the buttons in a way that if the elevator is going up it will do all the floors above the current floor then the floors below.
// I am sure there is an easier way to do this. I am open to ideas!
public class SortByNextLevel {
    // sort by going to the top floors first example if current floor is 3 and the buttons that were pressed were 1245 the order then would be 4521
    public static ArrayList<Integer> sortGoingUpFirst(ArrayList<Integer> elevatorButtons, int currentFloor){
        ArrayList<Integer> largerFloors = new ArrayList<>();
        ArrayList<Integer> smallerFloors = new ArrayList<>();
        for (int button : elevatorButtons ){
            if(button < currentFloor){
                smallerFloors.add(button);
            }else{
                largerFloors.add(button);
            }
        }
        largerFloors.sort(Comparator.naturalOrder());
        smallerFloors.sort(Comparator.naturalOrder());
        largerFloors.addAll(smallerFloors.reversed());
        return largerFloors;
    }
    // sort by going to the top floors first example if current floor is 3 and the buttons that were pressed were 1245 the order then would be 2145
    public static ArrayList<Integer> sortGoingDownFirst(ArrayList<Integer> elevatorButtons, int currentFloor){
        ArrayList<Integer> largerFloors = new ArrayList<>();
        ArrayList<Integer> smallerFloors = new ArrayList<>();
        for (int button : elevatorButtons ){
            if(button < currentFloor){
                smallerFloors.add(button);
            }else{
                largerFloors.add(button);
            }
        }
        largerFloors.sort(Comparator.naturalOrder());
        smallerFloors.sort(Comparator.naturalOrder());
        smallerFloors.sort(Comparator.reverseOrder());
        smallerFloors.addAll(largerFloors);
        return smallerFloors;
    }
}
