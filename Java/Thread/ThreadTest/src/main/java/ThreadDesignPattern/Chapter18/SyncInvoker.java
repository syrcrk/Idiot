package ThreadDesignPattern.Chapter18;

public class SyncInvoker {
    public static void main(String[] args) {
        FutureService service=new FutureService();
        Future<String> fu= service.submit(()->{
            try {
                Thread.sleep(1230);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1213";
        },System.out::println);
        try {
            System.out.println("blablabla");
            Thread.sleep(1000);
            System.out.println("blablabla1");
            //String res=fu.get();
            //System.out.println("blablabla2 "+res );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String get(){
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "AAA";
    }
}
