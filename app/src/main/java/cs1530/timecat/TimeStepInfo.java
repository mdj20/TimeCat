package cs1530.timecat;

/**
 * Created by matthew on 6/17/15.
 */
public class TimeStepInfo implements Comparable<TimeStepInfo> {



        // dateTime object ?
        private long duration;

        // use once ID value
        private int id;

        // can be set from index
        private int priority;

        private String title;
        private String notes;


        TimeStepInfo(long d, int i, int p , String t, String n ){

            this.duration = d;
            this.id= i ;
            this.priority = p;
            this.title = t ;
            this.notes = n ;

        }

        // Overload
        TimeStepInfo(long d, int i, int p){

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



        public void setDuration( long d){
            duration = d;
        }

        public long getDuration(){
            return duration;
        }

        public void setPriority(int p){
            priority = p;
        }

        public int getPriority(){
            return priority;
        }

        // Comparable req (must ad d code for edge cases and null object value)

        public int compareTo( TimeStepInfo in ){

            return this.priority - in.priority;


        }



}




