import java.awt.*;

public class Ship {
    private int x;
    private int y;
    private int hight;
    private int wight;
    private  int destroied;
    private boolean shoot=false;
    private int life;
    public Ship(int x,int y)
    {
        this.x= x;
        this.y=y;
        this.life=3;
        this.hight=100;
        this.wight=100;


    }

    public void UpdateShip(Ship ship)
    {
        this.x=ship.x;
        this.y=ship.y;
        this.hight=ship.hight;
        this.wight=ship.wight;
        this.shoot=ship.shoot;
        this.destroied=ship.destroied;
        this.life=ship.life;

    }

    public int getDestroied() {
        return destroied;
    }

    public void setDestroied(int destroied) {
        this.destroied = destroied;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getHight() {
        return hight;
    }

    public int getLife() {
        return life;
    }

    public int getWight() {
        return wight;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean getShoot() {
        return shoot ;
    }


}
