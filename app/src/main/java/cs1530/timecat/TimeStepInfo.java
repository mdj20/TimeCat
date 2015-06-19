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
        private int id;

        // can be set from index
        private int priority;

        private String title;
        private String notes;


        TimeStepInfo(int d, int i, int p , String t, String n ){

            this.duration = d;
            this.id= i ;
            this.priority = p;
            this.title = t ;
            this.notes = n ;

        }

        // Overload
        TimeStepInfo(int d, int i, int p){

            this( d, i,  p,"", "");

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

            return this.priority - in.priority;


        }



        // code below is required for parcelable

        @Override
        public int describeContents(){
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest , int flags){

            // ints to string
            dest.writeInt(this.duration);
            dest.writeInt(this.id);
            dest.writeInt(this.priority);
            dest.writeStringArray(new String[] {this.title,this.notes});
        }


}




