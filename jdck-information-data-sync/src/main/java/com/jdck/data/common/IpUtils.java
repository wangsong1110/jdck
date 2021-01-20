package com.jdck.data.common;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class IpUtils {

    public static   String getLocalMac() {
        // TODO Auto-generated method stub
        //获取网卡，获取地址
        StringBuffer sb = null;
        try {
            InetAddress ia = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            System.out.println("mac数组长度："+mac.length);
            sb = new StringBuffer("");
            for(int i=0; i<mac.length; i++) {
                if(i!=0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i]&0xff;
                String str = Integer.toHexString(temp);
                System.out.println("每8位:"+str);
                if(str.length()==1) {
                    sb.append("0"+str);
                }else {
                    sb.append(str);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println("本机MAC地址:"+sb.toString().toUpperCase());
        String queue=sb.toString().toUpperCase();
        return queue;
    }
}
