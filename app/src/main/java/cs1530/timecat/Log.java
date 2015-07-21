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
        private boolean isFinshed;

         Log(TimeStepInfo inTsi,  Date inDate){

            tsi = inTsi;
            timeStarted = inDate;
            isFinshed = false;


        }

        //create by parcel
        Log(Parcel in){

            this.tsi = in.readParcelable(TimeStepInfo.class.getClassLoader());
            this.timeStarted = new Date(in.readLong());
            this.timeFinished = new Date(in.readLong());
        }


        public TimeStepInfo getTimeStepInfo(){
            return tsi;
        }



        public Date getTimeStarted() {
            return timeStarted;
        }

        public boolean isFinshed(){
            return isFinshed;
        }

        public void setTimeFinished(Date d){
            timeFinished = d;
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

        dest.writeByte( (byte) (isFinshed?1:0) );

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



