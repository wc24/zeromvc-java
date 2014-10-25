package com.lime.zeromvc;

import org.junit.Test;

public class ZeroTest {

    public ZeroTest() {
        System.out.println("ffff");
    }

    @Test
    public void testSay() throws Exception {
        Zero<CommandEnum, MediatorEnum> h = new Zero<CommandEnum, MediatorEnum>();
//        Zero h = new Zero();
        h.addCommand(CommandEnum.TEST,TestCommand.class);

        h.addMediator(MediatorEnum.TEST,TestMediator.class);

        h.activate(MediatorEnum.TEST);
        h.command(CommandEnum.TEST,new TestVo());
        h.inactivate(MediatorEnum.TEST);
        h.command(CommandEnum.TEST,new TestVo());




    }
}