package top.simba1949.file;

import java.io.File;
import java.io.IOException;

/**
 * @author anthony
 * @version 2023/7/26 22:58
 */
public class FileOperateApplication {

    /**
     * 文件操作
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // 创建文件
        // createFile();

        // 创建文件夹
        // createDirIfNoExists();

        // 创建文件夹
        // createDir();

        // 文件重命名
        // rename();

        // 删除文件
        // deleteFile();

        // 删除文件
        // deleteOnExist();

        // 设置文件可执行
        // setExec();

        // 设置文件可读
        // setRead();

        // 设置文件只读
        // setReadOnly();

        // 设置文件可写
        // setWrite();

        // 设置文件最新更新时间
        setModified();
    }

    /**
     * 创建文件
     *
     * @throws IOException
     */
    public static void createFile() throws IOException {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        if (file.exists()) { // 判断文件是否成功
            System.out.println("文件已经存在，无需创建！");
        } else {
            if (file.createNewFile()) { // 创建文件，成功返回 true，失败返回 false
                System.out.println("文件创建成功！");
            } else {
                System.out.println("文件创建失败");
            }
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
            System.out.println("文件夹创建成功");
        } else {
            System.out.println("文件夹创建失败");
        }
    }


    public static void createDir() {
        String filePath = "D:\\Program Files\\file.txt\\file.md";
        File file = new File(filePath);

        // 创建文件目录，如果文件目录存在，则不创建，如果此文件上层目录任意目录不存在，则一起创建
        if (file.mkdirs()) {
            System.out.println("文件夹创建成功");
        } else {
            System.out.println("文件夹创建失败");
        }
    }


    public static void rename() {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        String destFilePath = "D:\\Program Files\\file-rename.txt";
        File destFile = new File(destFilePath);

        boolean flag = file.renameTo(destFile);
        System.out.println("文件重命名是否成功 =" + flag);
    }

    public static void deleteFile() {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        // 这里只会删除最末端的文件对象（包括文件夹、文件），
        // 如果文件对象是文件，则可以直接删除；
        // 如果文件对象是文件夹，则需要文件夹下不存在文件对象，才可以删除；
        if (file.delete()) {
            System.out.println("文件删除成功");
        } else {
            System.out.println("文件删除失败");
        }
    }

    /**
     * 删除文件
     */
    public static void deleteOnExist() {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        // 删除文件，只有当 JVM 停止运行的时候才会删除文件
        // 如果文件对象是文件，则可以直接删除；
        // 如果文件对象是文件夹，则需要文件夹下不存在文件对象，才可以删除；
        file.deleteOnExit();

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    /**
     * 设置文件可执行
     */
    public static void setExec() throws IOException {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        createFileIfNoExists(file);

        // 第一个参数表示是否可执行
        // 第二个参数，为true表示所有人皆可执行，false表示所属人可执行
        boolean execFlag = file.setExecutable(true, true);
        if (execFlag) {
            System.out.println("设置成功");
        }
        System.out.println("文件是否执行" + file.canExecute());
    }

    /**
     * 设置文件可读
     */
    public static void setRead() throws IOException {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        createFileIfNoExists(file);

        // 第一个参数表示是否可读
        // 第二个参数，为true表示所有人皆可读，false表示所属人可读
        boolean readFlag = file.setReadable(true, true);
        if (readFlag) {
            System.out.println("设置成功");
        }
        System.out.println("文件是否可读" + file.canRead());
    }

    /**
     * 设置只读
     *
     * @throws IOException
     */
    public static void setReadOnly() throws IOException {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        createFileIfNoExists(file);

        boolean readOnlyFlag = file.setReadOnly();
        if (readOnlyFlag) {
            System.out.println("设置成功");
        }

        System.out.println("文件是否可读：" + file.canRead() + "，是否可写" + file.canWrite());
    }

    /**
     * 设置文件可写
     *
     * @throws IOException
     */
    public static void setWrite() throws IOException {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        createFileIfNoExists(file);

        boolean writableFlag = file.setWritable(true, true);
        if (writableFlag) {
            System.out.println("设置成功");
        }

        System.out.println("文件是否可读：" + file.canRead() + "，是否可写" + file.canWrite());
    }

    /**
     * 设置文件最后更新时间
     *
     * @throws IOException
     */
    public static void setModified() throws IOException {
        String filePath = "D:\\Program Files\\file.txt";
        File file = new File(filePath);

        createFileIfNoExists(file);

        long now = System.currentTimeMillis();
        boolean modifiedFlag = file.setLastModified(now);
        if (modifiedFlag) {
            System.out.println("设置成功");
        }

        System.out.println("文件最新更新时间是否等于设置的时间" + (file.lastModified() == now));
    }


    /**
     * 如果文件存在，则创建
     *
     * @param file
     * @throws IOException
     */
    private static void createFileIfNoExists(File file) throws IOException {
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("文件创建成功");
            }
        }
    }
}
