package test;

import entities.Operation;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by the Focking Jendoliver on 16/10/2017
 * GitHub: github.com/Jendoliver
 */
public class OperationTest {
    @Test
    public void intAndFloatSumShouldBeDoneByTheSameMethodTest() throws Exception {
        assertEquals("Integer sum", 10, Operation.add(5,5));
    }

    @Test
    public void subMethodShouldSubstractTheSmallerFromTheBiggerTest() throws Exception {
        assertEquals("10 - 5 must be 5", 5, Operation.sub(10, 5));
        assertEquals("5 - 10 must be 5", 5, Operation.sub(5, 10));
        assertEquals("5 - 5 must be 0", 0, Operation.sub(5, 5));
    }
}