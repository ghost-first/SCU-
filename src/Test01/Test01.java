package Test01;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Test01 {
    public static void main(String[] args) throws UnknownHostException {
//        InetSocketAddress isa = new InetSocketAddress("220.167.43.224",8080);
//        System.out.println(isa);
//        System.out.println(isa.getHostString());
//        System.out.println(isa.getAddress());
        getIP();
    }

    public static void learnIP() throws UnknownHostException {
        //封装IP
//        InetAddress ia = new InetAddress(); 构造器被default（默认）修饰
        InetAddress ia = InetAddress.getByName("100.75.228.116");
        System.out.println(ia);
        InetAddress host = InetAddress.getByName("localhost");
        System.out.println(host);
        //通过此电脑的计算机名得到现在的ip
        InetAddress myComputer = InetAddress.getByName("LAPTOP-NIKNGDQU");
        System.out.println(myComputer);
        //获取域名的ip
        InetAddress baiDu = InetAddress.getByName("www.baidu.com");
        System.out.println(baiDu);
    }

    public static void getIP(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println(addr.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
