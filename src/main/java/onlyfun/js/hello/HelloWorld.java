package onlyfun.js.hello;
/**
 * Created by Administrator on 2015/6/30.
 */
public class HelloWorld {

    public void greeting(String name) {
        System.out.println("Hello " + name);
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.greeting("Justin");
    }
}
