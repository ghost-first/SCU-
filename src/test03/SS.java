package test03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SS {
    /*
    1、套接字：指定服务器端口号
     */
    public static void main(String[] args) {
        System.out.println("服务器启动了！！");
        ServerSocket serverSocket = null;
        Socket socket = null;
        int count = 0; //用来计数 客户端请求

        //开启一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            serverSocket = new ServerSocket(8080);
//            System.out.println(serverSocket.getLocalPort());
            //接收信息
            //阻塞方法:等待接受客户端的数据. 返回一个客户端Socket
            //客户端和服务器才真正产生连接
            while (true){
                //服务器一直监听客户端是否发送数据
                socket = serverSocket.accept();

                InputStream is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                String str = dis.readUTF();
                System.out.println("客户端发送的数据为："+str);
                int port_num = Integer.valueOf(str);
                //回复信息
                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF("接收成功");
                dos.close();
                os.close();
                //新的线程
                RealServer rs = new RealServer(port_num);
                Thread t = new Thread(rs);
                executorService.execute(t);
//                t.start();

                count++;
                System.out.println("我们服务器目前已接收到"+count+"个请求，次请求的ip是："+socket.getInetAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //关闭线程池
        executorService.shutdown();
    }
}
