package Test01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    /*
    1、套接字：指定服务器端口号
     */
    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动了");
        ServerSocket serverSocket = new ServerSocket(8080);
        //接收信息
        //阻塞方法:等待接受客户端的数据. 返回一个客户端Socket
        //客户端和服务器才真正产生连接
        Socket socket = serverSocket.accept();
        //得到数据
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readUTF();
        System.out.println("客户端发送的数据为："+str);

        //输出流
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("你好，我是服务器，已经接收到你的请求了！！");
        dos.close();
        os.close();

        //关闭
        dis.close();
        is.close();
        socket.close();
        serverSocket.close();

    }
}
