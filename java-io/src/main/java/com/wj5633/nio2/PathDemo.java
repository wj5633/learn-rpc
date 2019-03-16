package com.wj5633.nio2;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 20:50
 * @description
 */

public class PathDemo {
    public static void main(String[] args) {
        Path path = Paths.get("C:/Users/wangjie/workspace/java/learn-rpc/.gitignore");
        System.out.println("path1: " + path);

        path = Paths.get("C:", "Users", "wangjie", "workspace", "java", "learn-rpc", ".gitignore");
        System.out.println("path2: " + path);

        path = Paths.get("/Users/wangjie/workspace/java/learn-rpc/.gitignore");
        System.out.println("path3: " + path);

        path = Paths.get(URI.create("file:///Users/wangjie/workspace/java/learn-rpc/.gitignore"));
        System.out.println("path4: " + path);

        path = FileSystems.getDefault().getPath("/Users/wangjie/workspace/java/learn-rpc", ".gitignore");
        System.out.println("path5: " + path);

        File pathToFile = path.toFile();
        System.out.println("Path to file name: " + pathToFile.getName());

        URI pathToUri = path.toUri();
        System.out.println("Path to uri: " + pathToUri);

        Path pathToAbsolutePath = path.toAbsolutePath();
        System.out.println("Path to absolute path: " + pathToAbsolutePath.toString());
    }
}
