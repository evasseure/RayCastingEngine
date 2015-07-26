/**
 * Created by Erwan on 20/07/2015.
 */
public class Player {

    private double posX = 2;
    private double posY = 2;
    private double radX;
    private double radY;
    private double partX;
    private double partY;
    private int playerAngle = 0;
    private int radAngle;
    private int fixAngle;

    public Player() {
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getRadX() {
        return radX;
    }

    public void setRadX(double radX) {
        this.radX = radX;
    }

    public double getRadY() {
        return radY;
    }

    public void setRadY(double radY) {
        this.radY = radY;
    }

    public double getPartX() {
        return partX;
    }

    public void setPartX(double partX) {
        this.partX = partX;
    }

    public double getPartY() {
        return partY;
    }

    public void setPartY(double partY) {
        this.partY = partY;
    }

    public int getPlayerAngle() {
        return playerAngle;
    }

    public void setPlayerAngle(int playerAngle) {
        this.playerAngle = playerAngle;
    }

    public int getRadAngle() {
        return radAngle;
    }

    public void setRadAngle(int radAngle) {
        this.radAngle = radAngle;
    }

    public int getFixAngle() {
        return fixAngle;
    }

    public void setFixAngle(int fixAngle) {
        this.fixAngle = fixAngle;
    }
}
