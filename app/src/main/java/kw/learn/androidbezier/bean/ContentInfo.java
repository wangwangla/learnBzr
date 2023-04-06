package kw.learn.androidbezier.bean;

import java.io.Serializable;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/5 7:36
 */
public class ContentInfo implements Serializable {
    private String color;
    private int alin;
    private int size;
    private String content;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAlin() {
        return alin;
    }

    public void setAlin(int alin) {
        this.alin = alin;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContentInfo{" +
                "color='" + color + '\'' +
                ", alin=" + alin +
                ", size=" + size +
                ", content='" + content + '\'' +
                '}';
    }
}
