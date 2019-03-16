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
 * @create 2019/3/6 1:28
 * @description
 */

public class BIOEchoClient {

    public static void main(String[] args) {
        int port = 8082;
        String host = "127.0.0.1";

        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

            writer.write("Hello world!\n");
            writer.flush();

            String echo = reader.readLine();
            System.out.println("echo: " + echo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
