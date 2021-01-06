package ThreadDesignPattern.Chapter15;

public class Gate {
    private int counter=0;
    private String name="NoBody";
    private String address="NowHere";

    public void pass(String name,String address){
        counter++;
        this.name=name;
        this.address=address;
        verify();
    }

    void verify(){
        if(name.charAt(0)!=address.charAt(0)){
            System.out.println("xxxx error "+toString());
        }
    }

    public String toString(){
        return counter+"   " +name+"   "+address;
    }
}
