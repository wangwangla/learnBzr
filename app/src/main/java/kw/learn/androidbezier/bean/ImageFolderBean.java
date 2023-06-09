package kw.learn.androidbezier.bean;

import java.io.Serializable;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/6 9:34
 */
public class ImageFolderBean implements Serializable {

    /**  */
    private static final long serialVersionUID = 6645873496414509455L;
    /** 文件夹下第一张图片路径 */
    public String path;
    /**缩略图*/
    public String thumbnailsPath;
    /** 总图片数 */
    public int pisNum = 0;
    /** 文件夹名 */
    public String fileName;

    /**当图片选择后，索引值*/
    public int selectPosition;

    public int _id;

    /**当前图片在列表中顺序*/
    public int position;

}
