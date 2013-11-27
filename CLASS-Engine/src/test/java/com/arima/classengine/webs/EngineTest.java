package com.arima.classengine.webs;

import com.arima.classengine.webs.Engine;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: jayKrish
 * Date: 11/22/13
 * Time: 12:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class EngineTest {
    private static final String actualHello = "HelloUserjj!";


    public void tearup(){

    }
    @Test
    public void sayHelloCLASSTest() {
        Engine engine = new Engine();
        String expectedHello = engine.sayHelloCLASS();
        Assert.assertEquals(actualHello, expectedHello);

    }

}
