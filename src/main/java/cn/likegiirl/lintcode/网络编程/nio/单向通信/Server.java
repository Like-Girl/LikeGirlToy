package cn.likegiirl.lintcode.网络编程.nio.单向通信;

import cn.likegirl.utils.ThreadPoolManager;


public class Server {

    public static void main(String[] args) {
        ThreadPoolManager.newInstance().addExecuteTask(new ServerHandler());
    }
}
