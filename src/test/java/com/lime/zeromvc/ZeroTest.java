package com.lime.zeromvc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZeroTest {

    public ZeroTest(){
        System.out.println("ffff");
    }
    @Test
    public void testSay() throws Exception {
        Zero h=new Zero();
        assertEquals(h.say(),"Zero");
        System.out.println("testSay");
    }
}