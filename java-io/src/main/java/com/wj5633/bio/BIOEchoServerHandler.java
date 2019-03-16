package com.wj5633.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 1:36
 * @description
 */

public class BIOEchoServerHandler implements Runnable {

    private Socket socket;

    public BIOEchoServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println("BIOEchoServerHandler: ");
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println("server read: " + line);
                writer.write(line + "\n");
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
