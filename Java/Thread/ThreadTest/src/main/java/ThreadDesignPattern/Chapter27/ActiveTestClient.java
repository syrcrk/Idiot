package ThreadDesignPattern.Chapter27;

public class ActiveTestClient {
    public static void main(String[] args) {
        ActiveObject object= ActiveObjectFactory.createActiveObject();
        new MakeStringClient("crk",object).start();
        new MakeStringClient("sdm",object).start();

        new DisplayClient("wmd",object).start();
    }
}
