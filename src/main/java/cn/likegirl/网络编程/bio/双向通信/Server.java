package cn.likegirl.网络编程.bio.双向通信;

import cn.likegirl.utils.ThreadPoolManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author LikeGirl
 */
public class Server {

    public static final int PORT = 8400;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true){
            Socket socket = serverSocket.accept();
            ThreadPoolManager.newInstance().addExecuteTask(new ReadHandlerThread(socket));
            ThreadPoolManager.newInstance().addExecuteTask(new WriteHandlerThread(socket));
//            new Thread(new ReadHandlerThread(socket)).start();
//            new Thread(new WriteHandlerThread(socket)).start();
        }
    }
}


/**
 * 处理读操作的线程
 */
class ReadHandlerThread implements Runnable{
    private Socket client;

    public ReadHandlerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        try{
            while(true){
                //读取客户端数据
                dis = new DataInputStream(client.getInputStream());
                String reciver = dis.readUTF();
                System.out.println("客户端发过来的内容:" + reciver);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(dis != null){
                    dis.close();
                }
                if(client != null){
                    client = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 处理写操作的线程
 */
class WriteHandlerThread implements Runnable{
    private Socket client;

    public WriteHandlerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        DataOutputStream dos = null;
        BufferedReader br = null;
        try{
            while(true){
                //向客户端回复信息
                dos = new DataOutputStream(client.getOutputStream());
                System.out.print("请输入:\t");
                // 键盘录入
                br = new BufferedReader(new InputStreamReader(System.in));
                String send = br.readLine();
                //发送数据
                dos.writeUTF(send);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(dos != null){
                    dos.close();
                }
                if(br != null){
                    br.close();
                }
                if(client != null){
                    client = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
