package kw.learn.androidbezier.data;

import java.io.Serializable;

import kw.learn.androidbezier.bean.NoteBean;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/1 10:01
 */
public class TimeLineAllItem implements Serializable {
    private boolean title;
    private NoteBean msg;

    public void setMsg(NoteBean msg) {
        this.msg = msg;
    }

    public NoteBean getMsg() {
        return msg;
    }

    public void setTitle(boolean title) {
        this.title = title;
    }

    public boolean isTitle() {
        return title;
    }
}
