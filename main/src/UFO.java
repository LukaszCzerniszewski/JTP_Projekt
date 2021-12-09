public class UFO {
    private int x;
    private int y;
    private int hight;
    private int wight;
    public UFO(int x, int y)
    {
        this.x=x;
        this.y=y;
        this.hight=20;
        this.wight=50;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getWight() {
        return wight;
    }

    public int getHight() {
        return hight;
    }

    public int getY() {
        return y;
    }
}
