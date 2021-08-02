package Test02;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SecondClient {
    public static void main(String[] args){
        System.out.println("客户端启动");
        //创建套接字
        Socket socket = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        InputStream is = null;
        DataInputStream dis = null;

        try {
            socket = new Socket("220.167.43.224",8080);

            //录入用户账号和密码:
            Scanner sc = new Scanner(System.in);
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


            // 接收服务器的回话
            is = socket.getInputStream();
            dis = new DataInputStream(is);
            String str = dis.readUTF();
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
