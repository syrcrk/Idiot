package ThreadDesignPattern.Chapter21;

public class QueryFromDBAction {

    public void execute(){
        try {
            Thread.sleep(1000);
            String name="DB "+Thread.currentThread().getId();
            ActionContext.getActionContext().getContext() .setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
