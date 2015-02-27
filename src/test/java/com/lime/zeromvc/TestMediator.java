package com.lime.zeromvc;

/**
 * Created by linming on 14-10-25.
 */
public class TestMediator extends Mediator<CommandEnum,MediatorEnum> {


    public TestProxy testProxy;
    private Test2Proxy test2Proxy;



    public void update(Proxy proxy) {

        System.out.println("_____________1");
        System.out.println(proxy);
        System.out.println(testProxy);
    }

    public void update(TestProxy proxy) {
        System.out.println(proxy.a);
    }

    public void update(Test2Proxy proxy) {
        System.out.println("_____________3");
    }

    protected void activate() {
        testProxy = getProxy(TestProxy.class);
        test2Proxy = getProxy(Test2Proxy.class);
        addProxy(testProxy);
        addProxy(test2Proxy);
        command(CommandEnum.TEST, new TestVo());
dispose();
    }

    @Override
    protected void inactivate() {

    }
}
