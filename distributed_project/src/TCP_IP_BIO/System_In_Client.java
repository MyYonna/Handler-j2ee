package TCP_IP_BIO;

import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.io.PrintWriter;  
import java.net.Socket;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
  
public class System_In_Client {  
    private static final int PORT = 54321;  
    // 线程池  
    private static ExecutorService exec = Executors.newCachedThreadPool();  
  
    public static void main(String[] args) throws Exception {  
        new System_In_Client();  
    }  
  
    public System_In_Client() {  
        try {  
            Socket socket = new Socket("127.0.0.1", 8888);  
            exec.execute(new Sender(socket));  
            // 设置读取套接字服务器返回流的编码格式  
            BufferedReader br = new BufferedReader(new InputStreamReader(  
                    socket.getInputStream(), "UTF-8"));  
            String msg;  
            while((msg = br.readLine()) != null) {  
                System.out.println(msg);  
            }  
        }  
        catch(Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }  
  
    // 客户端线程获取控制台输入消息  
    static class Sender implements Runnable {  
        private Socket socket;  
  
        public Sender(Socket socket) {  
            this.socket = socket;  
        }  
  
        public void run() {  
            try {  
                BufferedReader br = new BufferedReader(new InputStreamReader(  
                        System.in));  
                // 设置向套接字服务器写入流的编码格式  
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(  
                        socket.getOutputStream(), "UTF-8"), true);  
                String msg;  
  
                while(true) {  
                	//readLine会阻塞进程，只有当有数据进来的时候才释放
                    msg = br.readLine();  
                    pw.println(msg);  
  
                    if(msg.trim().equals("exit")) {  
                        pw.close();  
                        br.close();  
                        exec.shutdownNow();  
                        break;  
                    }  
                }  
            }  
            catch(Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  