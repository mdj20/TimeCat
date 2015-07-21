package cs1530.timecat;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  Created by matthew on 6/17/15.
 *
 *  This class is the timer building block
 *
 *  provides data fields for
 *
 *
 */
public class TimeStepInfo implements Comparable<TimeStepInfo> , Parcelable {



        // dateTime object mayu be use in the future Maybe?
        private int duration;

        // use once ID value (not implemented yet, but these should have a unique id number)
        //private int id;

        // can be set from index
        private int priority;
        private String procedure;
        private String title;
        private String notes;


        // explicit constructor
        TimeStepInfo(int d, int p , String pro, String t, String n ){

            this.duration = d;
         //   this.id= i ;
            this.priority = p;
            this.title = t ;
            this.notes = n ;
            this.procedure = pro;
        }

        // Overloaded constructor
        TimeStepInfo(int d, int p, String pro){

            this( d,  p,pro,"", "");

        }

        // constructor via parcel
        private TimeStepInfo(Parcel in){

            this.duration = in.readInt();
            //this.id = in.readInt();
            this.priority = in.readInt();
            this.procedure = in.readString();
            this.title = in.readString();
            this.notes = in.readString();
        }
 


        // Data Methods

        public void setNotes( String n ){
            this.notes = n;
        }

        public String getNotes(){
            return this.notes;
        }

        // NOTE doesn't create a new string reuses pooled objects. ( may be noteworthy for serialization)
        public void setTitle(String t){
            title = t;
        }

        public String getTitle(){
            return title;
        }

        public void setProcedure(String pro) {procedure = pro;}

        public String getProcedure(){return procedure;}

        public void setDuration( int d){
            duration = d;
        }

        public int getDuration(){
            return duration;
        }

        public void setPriority(int p){
            priority = p;
        }

        public int getPriority(){
            return priority;
        }

        // Comparable req (must add code for edge cases and null object value)
        public int compareTo( TimeStepInfo in ){

            return this.priority - in.getPriority();


        }



        // code below is required for parcelable

        @Override
        public int describeContents(){
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest , int flags){

            // write data to parcel
            dest.writeInt(this.duration);
            //dest.writeInt(this.id);
            dest.writeInt(this.priority);
            dest.writeString(this.procedure);
            dest.writeString(this.title);
            dest.writeString(this.notes);

        }

        public static final Parcelable.Creator<TimeStepInfo> CREATOR = new Parcelable.Creator<TimeStepInfo>() {


            @Override
            public  TimeStepInfo createFromParcel(Parcel source){
                return new TimeStepInfo(source);
            }

            @Override
            public TimeStepInfo[] newArray(int size){
                return new TimeStepInfo[size];
            }

        };



}




