package ThreadDesignPattern.Chapter21;

public class QueryFromHttpAction {
    public void execute(){
        try {
            Thread.sleep(1000);
            ActionContext.getActionContext().getContext().setCardID(getCardId( ActionContext.getActionContext().getContext().getName()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCardId(String name){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name+ " 25586666 "+Thread.currentThread().getId();
    }
}
