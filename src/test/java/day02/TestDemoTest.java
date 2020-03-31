package day02;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDemoTest {
    public String say(){
        System.out.println("hello");
        return "hello";
    }
    @Test
    public void t1(){
        say();
    }
}