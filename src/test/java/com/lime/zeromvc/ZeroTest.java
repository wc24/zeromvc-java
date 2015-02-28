package com.lime.zeromvc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ZeroTest {

    public ZeroTest() {
        System.out.println("ffff");
    }

    @Test
    public void testSay()  {
        Zero<CommandEnum, MediatorEnum> h = new Zero<CommandEnum, MediatorEnum>();
//        Zero h = new Zero();
        h.addCommand(CommandEnum.TEST,TestCommand.class);

        h.addMediator(MediatorEnum.TEST,TestMediator.class);

        h.activate(MediatorEnum.TEST);
        h.command(CommandEnum.TEST,new TestVo());
        h.inactivate(MediatorEnum.TEST);
        h.command(CommandEnum.TEST,new TestVo());



//       for (Method method :ZeroTest.class.getDeclaredMethods()){
//          // System.out.println(method.getName());
//
//           if (method.getName()=="cctv"){
//
//               System.out.println("____________________");
//               System.out.println( method.getGenericParameterTypes()[0]);
//               System.out.println( method.getParameterTypes()[0].getName());
//               System.out.println( method.getModifiers());
//               System.out.println("____________________");
//               System.out.println(method.getName().intern());
//               try {
//                   method.invoke(this,new ArrayList());
//               } catch (IllegalAccessException e) {
//                   e.printStackTrace();
//               } catch (InvocationTargetException e) {
//                   e.printStackTrace();
//               }
//           }
//       }


//        for (String s :cctv()){
//
//            System.out.println(s);
//        }
//fuck();

    }
//    public void cctv(int ...arg){
//        System.out.print(a);
//        System.out.print(b);
//        System.out.print(c);
//    }
//    public void fuck(int ...args){
//        ObFunction fun= new ObFunction(this.getClass(),"cctv");
//        fun.call(this,args);
//    }
//
//    public void cctv(){
//        System.out.print("113");
//    }


//        private List<String> cctv() {
//            System.out.print("+++++++++++");
//
//            List<String> ccc =new ArrayList<String>();
//            ccc.add("1");
//            ccc.add("2");
//            ccc.add("5");
//            return ccc;
//        }




}