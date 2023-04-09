package kw.learn.androidbezier.bean;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/6 19:35
 */
public class DateBean {
    private int year;
    private int month;
    private int day;
    private int weekDay;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString() {
        return "DateBean{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", weekDay=" + weekDay +
                '}';
    }
}
