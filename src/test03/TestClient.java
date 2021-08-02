package test03;

import Test02.User;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
    /*
    1、创建套接字
    2、向外发送数据
    3、关闭流 + 关闭网络资源
     */
    public static void main(String[] args){
        System.out.println("客户端启动");
        //创建套接字
        Socket socket = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        InputStream is = null;
        DataInputStream dis = null;
        String hostIP = "";

        try {
            //获取IP并获得套接字
            InetAddress addr = InetAddress.getLocalHost();
            hostIP = addr.getHostAddress();
            socket = new Socket(hostIP,8080);

            Scanner sc = new Scanner(System.in);
            System.out.println("请输入您想接入的端口号：");
            String port_num = sc.next();
            os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(port_num);

            // 接收服务器的回话
            is = socket.getInputStream();
            dis = new DataInputStream(is);
            String str = dis.readUTF();

            if(!str.equals("接收成功")){
                System.out.println("服务器开启失败");
                return;
            }

            //在新的服务器
            socket.close();
            socket = new Socket(hostIP,Integer.valueOf(port_num));
            //录入用户账号和密码:

            System.out.println("账号：");
            String name = sc.next();
            System.out.println("密码：");
            String pwd = sc.next();
            //封装对象
            User user = new User(name,pwd);

            //向外发送数据
            os = socket.getOutputStream();
//        DataOutputStream dos = new DataOutputStream(os);
//        dos.writeUTF("你好啊！！");
            oos = new ObjectOutputStream(os);
            oos.writeObject(user);



            System.out.println("服务器回话"+str);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //防止空指针异常
                if(dis!=null) dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is!=null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //关闭流 + 关闭网络资源
//        dos.close();
            try {

                if(oos!=null) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os!=null) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket!=null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
