package uk.ac.nesc.idsd.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jiangj on 29/03/2015.
 */
public class ParameterTest {

    @Test
    public void testConstructor() {
        Parameter parameter = new Parameter();
        parameter.setLabel("");
        Parameter clone = new Parameter(parameter);
        Assert.assertNull(clone.getLabel());
    }
}
