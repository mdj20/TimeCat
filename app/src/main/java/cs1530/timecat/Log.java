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
        private int type;
        private Date date;


         Log(TimeStepInfo inTsi, int inType , Date inDate){

            tsi = inTsi;
            type = inType;
            date = inDate;


        }

        //create by parcel
        Log(Parcel in){

            this.tsi = in.readParcelable(TimeStepInfo.class.getClassLoader());
            this.type = in.readInt();
            this.date = new Date(in.readLong());

        }


        public TimeStepInfo getTimeStepInfo(){
            return tsi;
        }

        public int getType(){
            return type;
        }

        public Date getDate() {
            return date;
        }


    // parcelable

    @Override
    public int describeContents(){return 0 ;}

    @Override
    public void writeToParcel(Parcel dest , int flags){

        // write data to parcel
        dest.writeParcelable(this.tsi, flags);
        dest.writeInt(type);
        dest.writeLong(date.getTime());

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



