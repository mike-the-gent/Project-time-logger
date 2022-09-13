package projectTimer.projectData;

import com.google.common.base.Stopwatch;

import javax.swing.*;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Project {

    private String name;
    private double minutesSpent;
    //    private Date deadLine;
    private double quotedPrice;
    private double hourlyWage;
    private double newlyWorkedTime = 0.0;
    Stopwatch projectTimer;
    boolean timeRunning;

    public Project(String name, double minutesSpent, double quotedPrice) {
        this.name = name;
        this.minutesSpent = minutesSpent;
//        this.deadLine = deadLine;
        this.quotedPrice = quotedPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuotedPrice(double quotedPrice) {
        this.quotedPrice = quotedPrice;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public double getMinutesSpent() {
        return minutesSpent;
    }

//    public Date getDeadLine() {
//        return deadLine;
//    }

    public double getQuotedPrice() {
        return quotedPrice;
    }

    public double getHourlyWage() {
        return calcHourlyWage();
    }

    public double getNewlyWorkedTime() {
        return newlyWorkedTime;
    }

    public void setMinutesSpent(double timeSpent) {
        this.minutesSpent = timeSpent;
    }

    public void setTimeOn(boolean timeOn) {

        if (timeOn && !timeRunning) {
            projectTimer = Stopwatch.createStarted();
            timeRunning = true;
            newlyWorkedTime = 0.1;
//            System.out.println("time on and time wasn't previously running");
        } else if (timeOn && timeRunning) {
            projectTimer.stop();
            projectTimer.reset();
            projectTimer.start();
            timeRunning = false;
            timeRunning = true;
//            System.out.println("time on and was running");
        }
        if (!timeOn && timeRunning) {
            if (newlyWorkedTime > 0) {
                projectTimer.stop();
                double rawNewlyWorkedTime = projectTimer.elapsed(TimeUnit.MILLISECONDS);
                newlyWorkedTime = Math.ceil((rawNewlyWorkedTime / 1000)/60);
//                System.out.println(Math.ceil(newlyWorkedTime));
                timeRunning = false;
//                projectTimer.stop();
                projectTimer.reset();
//                System.out.println("time off and was running");
            }

        } else if (!timeOn && !timeRunning) {
//            System.out.println("time off and not running");
            newlyWorkedTime = 0.0;
            timeRunning = false;

        }
//                System.out.println("time off without time on");
    }




    private double calcHourlyWage() {
        if (minutesSpent >= 60) {
            return Math.round(quotedPrice / (minutesSpent / 60));
        } else {
            return 500.00;
        }
    }

}
