package ThreadDesignPattern.Chapter21;

public class ExecutionTask implements Runnable {
    private QueryFromDBAction action=new QueryFromDBAction();
    private QueryFromHttpAction action1=new QueryFromHttpAction();
    @Override
    public void run() {
        action.execute();
        action1.execute();
        System.out.println("use name is "+ActionContext.getActionContext().getContext().getCardID());
    }
}
