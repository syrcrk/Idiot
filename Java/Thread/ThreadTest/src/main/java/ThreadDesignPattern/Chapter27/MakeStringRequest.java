package ThreadDesignPattern.Chapter27;

/***
 *
 */
public class MakeStringRequest extends  MethodRequest {
    final int count;
    final char filChar;
    public MakeStringRequest(Servant servant, FutureResult futureResult,int count ,char filChar) {
        super(servant, futureResult);
        this.count=count;
        this.filChar=filChar;
    }

    @Override
    public void execute() {
        Result result= servant.makeString(count,filChar);
        futureResult.setResult(result);
    }
}
