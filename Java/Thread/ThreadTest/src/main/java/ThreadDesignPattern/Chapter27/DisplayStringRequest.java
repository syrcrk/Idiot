package ThreadDesignPattern.Chapter27;

public class DisplayStringRequest extends MethodRequest {
    private final String dis;
    public DisplayStringRequest(Servant servant, FutureResult futureResult,String str) {
        super(servant, futureResult);
        dis=str;
    }

    @Override
    public void execute() {
        servant.display(dis);
    }
}
