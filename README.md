zeromvc-java
============

观察者模式反射
在观察者对象上添加事件标识和对应要执行的类，一个标识可以对应多个类，
一个类可以添加进不对的标识。当观察者派发通知时 通知标识所对应的类所会被实例化，初始化并且执行execute方法！
若执行释放，第二接收到相应通知将会构建新的对象！否则只会产生一个识标只会产生一个实例并且常在内存！