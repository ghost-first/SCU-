package Test02;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    //线程：专门处理客户端的请求
    InputStream is = null;
    ObjectInputStream ois = null;
    OutputStream os = null;
    DataOutputStream dos = null;
    Socket socket = null;
    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            //得到数据
            is = socket.getInputStream();
//        DataInputStream dis = new DataInputStream(is);
            ois = new ObjectInputStream(is);
            User user = (User)(ois.readObject());

            System.out.println("客户端发送的数据为：账号"+user.getName()+"密码"+user.getPassword());

            //输出流
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
            dos.writeUTF("你好，我是服务器，已经接收到你的请求了！！");
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            try {
                if(dos!=null) dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os!=null) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //关闭
            try {
                if(ois!=null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is!=null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
