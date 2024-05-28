package tests;

import main.SortByNextLevel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
// Standard tests to verify that the code works the way I intended it.

public class SortTest {
    @Test
    public void goingUpSort(){
        ArrayList<Integer> goingUP = new ArrayList<>();
        goingUP.add(1);
        goingUP.add(2);
        goingUP.add(4);
        goingUP.add(5);
        ArrayList<Integer> Sorted = SortByNextLevel.sortGoingUpFirst(goingUP,3);
        Assert.assertEquals(4, (int) Sorted.get(0));
        Assert.assertEquals(5, (int) Sorted.get(1));
        Assert.assertEquals(2, (int) Sorted.get(2));
        Assert.assertEquals(1, (int) Sorted.get(3));
    }
    @Test
    public void goingDownSort(){
        ArrayList<Integer> goingUP = new ArrayList<>();
        goingUP.add(1);
        goingUP.add(2);
        goingUP.add(4);
        goingUP.add(5);
        ArrayList<Integer> Sorted = SortByNextLevel.sortGoingDownFirst(goingUP,3);
        Assert.assertEquals(2, (int) Sorted.get(0));
        Assert.assertEquals(1, (int) Sorted.get(1));
        Assert.assertEquals(4, (int) Sorted.get(2));
        Assert.assertEquals(5, (int) Sorted.get(3));
    }
}
