package kw.learn.androidbezier.sqlite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import kw.learn.androidbezier.application.MyApplication;
import kw.learn.androidbezier.bean.NoteBean;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/5 7:32
 */
public class NoteSeriUtils {
    public static void saveNote(NoteBean content){
        String absolutePath = MyApplication.getInstance().getFilesDir().getAbsolutePath();
        File file = new File(absolutePath+"/writenote/"+content.getTime());
        file.getParentFile().mkdir();
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(absolutePath+"---------------");
        try(ObjectOutputStream outputStream
                    = new ObjectOutputStream(
                new FileOutputStream(file))){
            outputStream.writeObject(content);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void readNote(){
        String absolutePath = MyApplication.getInstance().getFilesDir().getAbsolutePath();
        File file = new File(absolutePath+"/writenote/");
        if (file.listFiles()!=null) {
            for (int i = 0; i < file.listFiles().length; i++) {
                File file1 = file.listFiles()[i];
                readObject(file1);
            }
        }
    }

    public static void readObject(File file){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            //  使用 readObject() 反序列化
            Object obj = ois.readObject();
            //  使用对象
            System.out.println(obj);
            //  关闭流
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
