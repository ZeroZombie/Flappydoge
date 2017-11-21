/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentativa1;

/**
 *
 * @author Beatriz
 */
public class Score {
    private int points;
    private int record;
    
    Score() {
        points = 0;
        record = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }
    
    public void addPoint() {
        points ++;
    }
    
}
