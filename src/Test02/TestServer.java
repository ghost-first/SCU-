package Test02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    /*
    1、套接字：指定服务器端口号
     */
    public static void main(String[] args) {
        System.out.println("服务器启动了！！");
        ServerSocket serverSocket = null;
        Socket socket = null;
        int count = 0; //用来计数 客户端请求
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println(serverSocket.getLocalPort());
            //接收信息
            //阻塞方法:等待接受客户端的数据. 返回一个客户端Socket
            //客户端和服务器才真正产生连接
            while (true){//服务器一直监听客户端是否发送数据
                socket = serverSocket.accept();
                //每次过来的客户端请求通过线程处理
                new ServerThread(socket).start();
                count++;
                System.out.println("我们服务器目前已接收到"+count+"个请求，次请求的ip是："+socket.getInetAddress());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
