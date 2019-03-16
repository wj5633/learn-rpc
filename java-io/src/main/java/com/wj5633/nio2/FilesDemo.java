package com.wj5633.nio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 20:58
 * @description
 */

public class FilesDemo {
    public static final String ROOT_PATH = ".";

    public static void main(String[] args) throws IOException {
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(ROOT_PATH));
        for (Path path : directoryStream) {
            System.out.println(path);
        }

        Path path = Files.createDirectories(Paths.get(ROOT_PATH, "/test/test"));
        System.out.println(path.getFileName());

        Path file = Files.createFile(Paths.get(ROOT_PATH, "/test/test/test.txt"));
        String text = "Hello world.";
        try {
            BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.APPEND);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
