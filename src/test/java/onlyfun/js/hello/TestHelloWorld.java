package onlyfun.js.hello;

import org.junit.Test;

/**
 * Created by Administrator on 2015/6/30.
 */
public class TestHelloWorld {
    @Test
    public void testHelloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.greeting("Justin");
    }
}
