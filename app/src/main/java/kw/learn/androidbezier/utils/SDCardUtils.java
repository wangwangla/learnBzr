package kw.learn.androidbezier.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　 ████━████     ┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　 　 ┗━━━┓
 * 　　　　　　　　　┃ 神兽保佑　　 ┣┓
 * 　　　　　　　　　┃ 代码无BUG   ┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 *
 * @Auther jian xian si qi
 * @Date 2023/4/6 9:21
 */
public class SDCardUtils {
    /**
     * 创建文件夹
     * @param folderPath folderPath
     * @throws IOException
     */
    public static boolean createFolder(String folderPath) throws IOException {
        if (!isSDCardMounted()) {
            return false;
        }
        File folder = new File(folderPath);
        if(!folder.exists()) {
            return folder.mkdirs();
        }
        return true;
    }

    /**
     * sdcard是否挂载
     */
    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
