package com.wj5633.nio2;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 21:08
 * @description
 */

public class WatchServiceDemo {

    public void watchDir(Path path) throws InterruptedException {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = watchEvent.kind();
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
                    final Path fileName = watchEventPath.context();
                    System.out.println(kind + ": " + fileName);
                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final Path path = Paths.get("D://");
        WatchServiceDemo watch = new WatchServiceDemo();
        try {
            watch.watchDir(path);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
