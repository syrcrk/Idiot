package ThreadDesignPattern.Chapter27;

public class DisplayClient extends  Thread{
    private final ActiveObject activeObject;

    public DisplayClient(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for(int i=0;true;i++){
                String text=Thread.currentThread().getName()+" "+i;
                activeObject.display(text);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
