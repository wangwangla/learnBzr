package kw.learn.androidbezier.sqlite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import kw.learn.androidbezier.application.MyApplication;
import kw.learn.androidbezier.bean.NoteBean;
import kw.learn.androidbezier.listener.SignListener;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/5 7:32
 */
public class NoteSeriUtils {
    public static void saveNote(NoteBean content, SignListener runnable){
        String absolutePath = MyApplication.getInstance().getFilesDir().getAbsolutePath();
        File file = new File(absolutePath+"/writenote/"+content.getTime());
        file.getParentFile().mkdir();
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                runnable.sign(0);
                return;
            }
        }
        try(ObjectOutputStream outputStream
                    = new ObjectOutputStream(
                new FileOutputStream(file))){
            outputStream.writeObject(content);
            runnable.sign(1);
        }catch (Exception e){
            e.printStackTrace();
            runnable.sign(0);
        }
    }

    public static List<NoteBean> readNote(){
        String absolutePath = MyApplication.getInstance().getFilesDir().getAbsolutePath();
        File file = new File(absolutePath+"/writenote/");
        List<NoteBean> allNote = new ArrayList<>();
        if (file.listFiles()!=null) {
            for (int i = 0; i < file.listFiles().length; i++) {
                File file1 = file.listFiles()[i];
                allNote.add(readObject(file1));
            }
        }
        return allNote;
    }

    public static NoteBean readObject(File file){
        ObjectInputStream ois = null;
        NoteBean noteBean = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            //  使用 readObject() 反序列化
            Object obj = ois.readObject();
            //  使用对象
            System.out.println(obj);
            noteBean = (NoteBean) obj;
            //  关闭流
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return noteBean;
    }

    public static void delete(String fileName) {
        String absolutePath = MyApplication.getInstance().getFilesDir().getAbsolutePath();
        File file = new File(absolutePath+"/writenote/"+fileName);
        if (file.delete()) {

        }
    }
}
