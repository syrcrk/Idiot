package ThreadDesignPattern.Chapter25;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream()
        ) {
            BufferedReader reader=new BufferedReader(new InputStreamReader(input));
            PrintWriter printWriter=new PrintWriter(output);
            while (running) {
                String str=reader.readLine();
                if(str==null){
                    break;
                }
                System.out.println("msg from client "+str);
                printWriter.write("echo "+str);
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.running=false;
        }finally {
            this.stop();
        }
    }

    public void stop() {
        if (!running) {
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
