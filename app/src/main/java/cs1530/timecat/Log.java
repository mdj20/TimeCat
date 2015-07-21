package cs1530.timecat;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Matthew on 7/18/2015.
 */
public class Log implements Parcelable {


    // log will contain information events relating to timers

        private TimeStepInfo tsi;

        private Date timeStarted;
        private Date timeFinished;
        private boolean finished;
        private long interval;

         Log(TimeStepInfo inTsi,  Date inDate){

            tsi = inTsi;
            timeStarted = inDate;
            finished = false;


        }

        //create by parcel
        Log(Parcel in){

            this.tsi = in.readParcelable(TimeStepInfo.class.getClassLoader());
            this.timeStarted = new Date(in.readLong());
            this.timeFinished = new Date(in.readLong());
            this.interval = in.readLong();
        }


        public TimeStepInfo getTimeStepInfo(){
            return tsi;
        }



        public Date getTimeStarted() {
            return timeStarted;
        }

        public Date getTimeFinished(){
            return timeFinished;
        }

        public long getInterval(){
            return interval;
        }

        public boolean isFinished(){
            return finished;
        }

        public void setTimeFinished(Date d){
            if (!finished) {
                timeFinished = d;
                interval = timeFinished.getTime() - timeStarted.getTime();
                finished = true;
            }
        }



    // parcelable

    @Override
    public int describeContents(){return 0 ;}

    @Override
    public void writeToParcel(Parcel dest , int flags){

        // write data to parcel
        dest.writeParcelable(this.tsi, flags);
        dest.writeLong(timeStarted.getTime());
        dest.writeLong(timeFinished.getTime());
        dest.writeLong(interval);

        dest.writeByte( (byte) (finished?1:0) );

    }


    public static final Parcelable.Creator<Log> CREATOR = new Parcelable.Creator<Log>() {


        @Override
        public Log createFromParcel(Parcel source){
            return new Log(source);
        }

        @Override
        public Log[] newArray(int size){
            return new Log[size];
        }

    };




}



