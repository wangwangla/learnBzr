package kw.learn.androidbezier.bean;

/**
 * @Auther jian xian si qi
 * @Date 2023/6/9 15:05
 */
public class KeyBean {
    private int year;
    private int month;

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "KeyBean{" +
                "year=" + year +
                ", month=" + month +
                '}';
    }
}
