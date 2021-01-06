package ThreadDesignPattern.Chapter14;

public abstract class Observer {
    public Observer(Subject subj){
        this.subject=subj;
        subj.attach(this);
    }
    protected  Subject subject;

    public abstract void update();
}
