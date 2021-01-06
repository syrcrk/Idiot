package ThreadDesignPattern.Chapter15;

public class User extends Thread {
    private final String myName;
    private final String myAddress;
    private final Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    public String getMyName() {
        return myName;
    }

    public String getMyAddress() {
        return myAddress;
    }

    public Gate getGate() {
        return gate;
    }
    @Override
    public void run(){
        System.out.println(myName+" begin");
        while (true){
            gate.pass(myName,myAddress);
        }
    }
}
