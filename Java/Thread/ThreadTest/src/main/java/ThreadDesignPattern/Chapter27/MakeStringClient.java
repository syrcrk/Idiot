package ThreadDesignPattern.Chapter27;

public class MakeStringClient extends Thread {
    private final ActiveObject activeObject;
    private final char filChar;
    public MakeStringClient(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        filChar=name.charAt(0);
    }

    @Override
    public void run() {
        try {
            for(int i=0;true;i++){
                Result res= this.activeObject.makeString((i+1)%10,filChar);
                Thread.sleep(200);
                String value= (String) res.getResultValue();
                System.out.println(Thread.currentThread().getName()+" makestring "+value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
