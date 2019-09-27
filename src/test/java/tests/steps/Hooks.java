package tests.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;


public class Hooks {

    @Before
    public void beforeHook() {
        System.out.println("before");
    }

    @After
    public void afterHook() {
        System.out.println("after");
    }

}
