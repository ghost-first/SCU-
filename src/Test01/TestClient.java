package Test01;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
    /*
    1、创建套接字
    2、向外发送数据
    3、关闭流 + 关闭网络资源
     */
    public static void main(String[] args) throws IOException {
        System.out.println("客户端启动了！！");
        String hostIP = "";
        //获取本机ip
        try {
            InetAddress addr = InetAddress.getLocalHost();
//            System.out.println(addr.getHostAddress());
            hostIP = addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //创建套接字
        Socket socket = new Socket(hostIP,8080);
        //向外发送数据
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("你好啊！！");

        // 接收服务器的回话
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readUTF();
        System.out.println("服务器回话"+str);

        dis.close();
        is.close();

        //关闭流 + 关闭网络资源
        dos.close();
        os.close();
        socket.close();

    }
}
