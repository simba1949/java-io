package top.simba1949.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author anthony
 * @version 2023/7/26 22:58
 */
public class FileOperateApplication {
    public static final Logger log = LoggerFactory.getLogger(FileOperateApplication.class);

    /**
     * 文件操作
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // 文件重命名
        // rename();

        // 创建文件
        // createFile();

        // 创建文件夹
        // createDirIfNoExists();

        // 创建文件夹
        // createDir();

        // 删除文件
        deleteFile();
    }

    public static void deleteFile() {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        // 这里只会删除最末端的文件对象（包括文件夹、文件），
        // 如果文件对象是文件，则可以直接删除；
        // 如果文件对象是文件夹，则需要文件夹下不存在文件对象，才可以删除；
        if (file.delete()) {
            log.info("文件删除成功");
        } else {
            log.info("文件删除失败");
        }
    }

    public static void createDir() {
        String filePath = "D:\\Program Files\\file.txt\\file.md";
        File file = new File(filePath);

        // 创建文件目录，如果文件目录存在，则不创建，如果此文件上层目录任意目录不存在，则一起创建
        if (file.mkdirs()) {
            log.info("文件夹创建成功");
        } else {
            log.info("文件夹创建失败");
        }
    }

    public static void createDirIfNoExists() {
        // 第一种：上层文件夹不存在时（示例上层文件夹：file.txt）
        // String filePath = "D:\\Program Files\\file.txt\\file.md";
        // 第二种：上层文件夹都存在时
        String filePath = "D:\\Program Files\\file.md";
        File file = new File(filePath);

        // 创建文件目录，如果文件目录存在，则不创建，如果此文件上层目录任意目录不存在也不创建
        if (file.mkdir()) {
            log.info("文件夹创建成功");
        } else {
            log.info("文件夹创建失败");
        }
    }


    public static void createFile() throws IOException {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        if (file.exists()) { // 判断文件是否成功
            log.info("文件已经存在，无需创建！");
        } else {
            if (file.createNewFile()) { // 创建文件，成功返回 true，失败返回 false
                log.info("文件创建成功！");
            } else {
                log.info("文件创建失败");
            }
        }
    }

    public static void rename() {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        String destFilePath = "D:\\Program Files\\file-rename.txt";
        File destFile = new File(destFilePath);

        boolean flag = file.renameTo(destFile);
        log.info("文件重命名是否成功 = {}", flag);
    }
}
