package ThreadDesignPattern.Chapter27;

class Servant implements  ActiveObject {
    @Override
    public Result makeString(int count, char filChar) {
        char[] buf=new char[count];
        for(int i=0;i<count;i++) {
            buf[i] = filChar;
        }        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new RealResult(new String(buf));
    }

    @Override
    public void display(String str) {
        try {
            System.out.println(Thread.currentThread().getName()+ " display "+str);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
