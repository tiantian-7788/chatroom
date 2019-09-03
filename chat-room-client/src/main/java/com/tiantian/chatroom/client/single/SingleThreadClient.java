package com.tiantian.chatroom.client.single;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 聊天室的客户端程序
 * Author：tiantian
 * Created：2019/2/25
 */
public class SingleThreadClient {
    public static void main(String[] args) {


        try {
            //0.通过命令行获取参数
            int port=6666;
            if(args.length>0){
                try {
                    port=Integer.parseInt(args[0]);
                }catch (NumberFormatException e){
                    System.out.println("端口参数不正确，采用默认端口"+port);
                }
            }
            String host="127.0.0.1";
            if(args.length>1){
                host=args[1];
            }
            //1.创建客户端，连接到服务器
            Socket clientSocket=new Socket(host,port);

            //2.发送数据，接收数据
            //2.1 发送数据
            OutputStream clientOutput=clientSocket.getOutputStream();
            OutputStreamWriter writer=new OutputStreamWriter(clientOutput);
            writer.write("你好，我是客户端。\n");
            writer.flush();

            //2.2 接收数据
            InputStream clientInput=clientSocket.getInputStream();
            Scanner scanner=new Scanner(clientInput);
            String serverData=scanner.nextLine();
            System.out.println("来自服务端的数据："+serverData);

            //3.关闭客户端（服务器不需要关闭）
            clientSocket.close();
            System.out.println("客户端关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
