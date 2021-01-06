package ThreadDesignPattern.Chapter14;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> list=new ArrayList<>();
    private int state;
    public int getState(){
        return state;
    }
    public void setState(int s){
        if(state==s){
            return;
        }
        state=s;
        notifyAllObserver();
    }
    public void attach(Observer ob){
        list.add(ob);
    }
    public void notifyAllObserver(){
        list.stream().forEach(Observer::update);
    }
}
