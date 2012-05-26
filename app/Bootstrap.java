import controllers.Application;
import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
        //Check if the database is empty
        if(School.count() == 0) {
        	Fixtures.deleteDatabase();
            //Fixtures.loadModels("seeddata.yml");
            School s = new School("SST");
            s.save();
            School s2 = new School("ANOTHER");
            s2.save();
            Teacher t = new Teacher("Johari", s);
            t.save();
            Teacher t2 = new Teacher("Another", s);
            t2.save();
            String ubase = "S";
            Classroom c = new Classroom(s, t, "Test Class", 2012);
            c.save();
            Classroom c2 = new Classroom(s, t, "A Second Class", 2012);
            c2.save();
            for (int i = 1; i<31; i++ )
            {
            	String uid = String.valueOf(i);
            	if (i<10)
            		uid = "0"+uid;
            	StudentUser su = new StudentUser(ubase+uid, c);
            	su.save();
            }
            
            //create a fake session
            //Application.startActivity(t.username, t.school.schoolName, c.classname, c.startYear, "Test Activity", "Dummy Loader");
            //Application.startActivity("Johari", "SST", "Test Class", 2012, "Test Activity", "Dummy Loader");
            Session se = new Session(c, "Fake Source");
            se.save();
        }
        
    }
 
}