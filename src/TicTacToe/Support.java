
package TicTacToe;

/**
 *
 * @author tharaka
 */
public class Support {

    DB db;
    String name1;
    String name2;
    int marks1;
    int marks2;

    public Support(DB db, String name1, String name2) {
        this.db = db;
        this.name1 = name1;
        this.name2 = name2;
        marks1 = 0;
        marks2 = 0;
    }

    public DB getDb() {
        return db;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public int getMarks1() {
        return marks1;
    }

    public int getMarks2() {
        return marks2;
    }
    public void inMark1(){
        marks1++;
    }
    public void inMark2(){
        marks2++;
    }
    
}
