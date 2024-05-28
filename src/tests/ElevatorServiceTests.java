package tests;
import main.Elevator;
import main.ElevatorError;
import main.ElevatorService;
import main.buttons.ElevatorInsideButtons;
import main.buttons.ElevatorOutsideButtons;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
// Standard tests to verify that the code works the way I intended it.
public class ElevatorServiceTests {
    Elevator defaultElevator;
    ElevatorService service;
    @Test
    public void testElevatorSelectOutsideButton(){
        defaultElevator =  new Elevator();
        service = new ElevatorService(defaultElevator);
        Assert.assertFalse(defaultElevator.getIsDoorOpen());
        Assert.assertEquals("Door opens on floor 3, going UP", service.selectOutsideButton(ElevatorOutsideButtons.UP, 3));
        Assert.assertEquals("Door opens on floor 1, going DOWN", service.selectOutsideButton(ElevatorOutsideButtons.DOWN, 1));
        Assert.assertTrue(defaultElevator.getIsDoorOpen());
    }
    @Test
    public void testElevatorSelectInsideButton(){
        defaultElevator =  new Elevator();
        service = new ElevatorService(defaultElevator);
        Assert.assertEquals("Door open on floor 3", service.selectInsideButton(3));
        Assert.assertEquals("Door open on floor 1", service.selectInsideButton(1));
        Assert.assertTrue(defaultElevator.getIsDoorOpen());
    }
    @Test
    public void testElevatorSelectMiscInsideButton() throws ElevatorError {
        defaultElevator =  new Elevator();
        service = new ElevatorService(defaultElevator);
        Assert.assertEquals("Door open", service.selectInsideButton(ElevatorInsideButtons.DOOROPEN));
        Assert.assertEquals("Door close", service.selectInsideButton(ElevatorInsideButtons.DOORClOSE));
    }
    @Test(expected = ElevatorError.class)
    public void testElevatorSelectInsideButtonThrowsMaintenanceError() throws ElevatorError {
        defaultElevator =  new Elevator();
        service = new ElevatorService(defaultElevator);
        service.selectInsideButton(ElevatorInsideButtons.CALLHELP);
    }
    @Test
    public void testElevatorSelectInsideButtonDoesNotExist(){
        defaultElevator =  new Elevator();
        service = new ElevatorService(defaultElevator);
        Assert.assertEquals("Floor doesn't exist!", service.selectInsideButton(10));
        Assert.assertEquals("Floor doesn't exist!", service.selectInsideButton(-10));
        Assert.assertEquals("Floor doesn't exist!", service.selectInsideButton(0));
    }
    @Test
    public void testElevatorSelectButtonsGoingUp(){
        defaultElevator =  new Elevator();
        service = new ElevatorService(defaultElevator);
        ArrayList<Integer> manyButtons = new ArrayList<>();
        manyButtons.add(5);
        manyButtons.add(3);
        manyButtons.add(2);
        manyButtons.add(4);
        service.selectOutsideButton(ElevatorOutsideButtons.UP, 1);
        Assert.assertEquals("Doors closing on floor 5", service.selectInsideButtons(manyButtons));
        manyButtons.clear();
        service.selectOutsideButton(ElevatorOutsideButtons.UP, 2);
        manyButtons.add(4);
        manyButtons.add(3);
        manyButtons.add(-1);
        Assert.assertEquals("Doors closing on floor -1", service.selectInsideButtons(manyButtons));
    }
    @Test
    public void testElevatorSelectButtonsGoingDown(){
        defaultElevator =  new Elevator();
        service = new ElevatorService(defaultElevator);
        ArrayList<Integer> manyButtons = new ArrayList<>();
        manyButtons.add(5);
        manyButtons.add(3);
        manyButtons.add(2);
        manyButtons.add(4);
        manyButtons.add(-1);
        service.selectOutsideButton(ElevatorOutsideButtons.DOWN, 1);
        Assert.assertEquals("Doors closing on floor 5", service.selectInsideButtons(manyButtons));
        manyButtons.clear();
        service.selectOutsideButton(ElevatorOutsideButtons.DOWN, 2);
        manyButtons.add(4);
        manyButtons.add(3);
        manyButtons.add(1);
        Assert.assertEquals("Doors closing on floor 4", service.selectInsideButtons(manyButtons));
    }
    @Test(expected = ElevatorError.class)
    public void testSetMaintenanceException() throws ElevatorError {
        defaultElevator =  new Elevator();
        service = new ElevatorService(defaultElevator);
        service.setMaintenance(true);
    }
    @Test
    public void testSetMaintenance() throws ElevatorError {
        defaultElevator =  new Elevator();
        service = new ElevatorService(defaultElevator);
        Assert.assertEquals("Elevator is serviceable again!", service.setMaintenance(false));

    }
}
