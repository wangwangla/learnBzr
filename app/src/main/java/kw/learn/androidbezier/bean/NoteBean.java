package kw.learn.androidbezier.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/5 7:32
 */
public class NoteBean implements Serializable {
    private long time;
    private ArrayList<ContentInfo> content;
    private String weather;
    private String mood;
    private String tag;
    private String bgColor;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ArrayList<ContentInfo> getContent() {
        return content;
    }

    public void setContent(ArrayList<ContentInfo> content) {
        this.content = content;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    public String toString() {
        return "NoteBean{" +
                "time=" + time +
                ", content=" + content +
                ", weather='" + weather + '\'' +
                ", mood='" + mood + '\'' +
                ", tag='" + tag + '\'' +
                ", bgColor='" + bgColor + '\'' +
                '}';
    }
}
