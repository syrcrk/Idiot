package ThreadDesignPattern.Chapter19;

public class Request {
    private final String request;

    public Request(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
