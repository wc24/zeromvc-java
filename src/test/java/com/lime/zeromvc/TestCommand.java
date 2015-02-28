package com.lime.zeromvc;

/**
 * Created by linming on 14-10-25.
 */
public class TestCommand extends Command<CommandEnum, MediatorEnum> {


    public void execute(TestVo testVo) {

        System.out.println(testVo);
    }

    @Override
    public void init() {

        System.out.println("cctvcctvcctvcctvcctvcctvcctvcctv");
    }
}
