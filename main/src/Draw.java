import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class Draw extends JPanel {

    private String file = System.getProperty("user.dir");
    private LinkedList<UFO> ufo = new LinkedList<>();
    private LinkedList<Dimension> buletsOfUfo = new LinkedList<>();
    private LinkedList<Dimension> buletsOfShip = new LinkedList<>();
    private LinkedList<Dimension>bonus = new LinkedList<>();
    private float time = 0;
    private int hit = 0;
    Ship ship = new Ship(Toolkit.getDefaultToolkit().getScreenSize().width/2,Toolkit.getDefaultToolkit().getScreenSize().height-130);


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (hit == 0) {
            g2d.drawImage(name(toString(file, "\\src\\Backgruond\\start.jpg")).getImage(), 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, null);
        }

        if (hit == 2) {
            g2d.setColor(Color.WHITE);
            g2d.drawImage(name(toString(file, "\\src\\Backgruond\\end.png")).getImage(), 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, null);
            g2d.drawString("Game took you: " + time + " seconds", Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 120, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 40);
            g2d.drawString("You were able to: " + ship.getDestroied() + " aliens", Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 120, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 20);
        }
        if (hit == 1) {
            g2d.drawImage(name(toString(file, "\\src\\Backgruond\\background.jpg")).getImage(), 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, null);
            g2d.drawImage(name(toString(file, "\\src\\Backgruond\\ship.png")).getImage(), ship.getX(), ship.getY(), 100, 100, null);
            for (int i = 0; i < ufo.size(); i++) {
                g2d.drawImage(name(toString(file, "\\src\\Backgruond\\ufo.png")).getImage(), ufo.get(i).getX(), ufo.get(i).getY(), 50, 20, null);
            } //Draw aliens
            g2d.setColor(Color.ORANGE);
            for (int i = 0; i < buletsOfUfo.size(); i++)
            {

                g2d.fillRect(buletsOfUfo.get(i).width - 10, buletsOfUfo.get(i).height + 10, 5, 20);
            }
            g2d.setColor(Color.ORANGE);
            for (int i = 0; i < buletsOfShip.size(); i++) {
                g2d.fillRect(buletsOfShip.get(i).width - 10, buletsOfShip.get(i).height + 10, 5, 20);
            }
            for(int i=0; i < ship.getLife(); i++)
            {
                g2d.drawImage(name(toString(file, "\\src\\Backgruond\\heart.png")).getImage(), 10, 100+(i *80), 80, 80, null);
            }

            for(int i=0; i < bonus.size(); i++)
            {
                g2d.drawImage(name(toString(file, "\\src\\Backgruond\\heart.png")).getImage(), bonus.get(i).width, bonus.get(i).height, 40, 40, null);
            }
        }
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }





    public void setTime(float time) {
        this.time = time;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }




    public static ImageIcon name(String s) {
        ImageIcon imageincon = null;
        imageincon = new ImageIcon(s);
        return imageincon;
    }


    public String toString(String a, String s) {
        return a + s;
    }


    public void setBonus(LinkedList<Dimension> bonus) {
        this.bonus = bonus;
    }

    public void setBuletsOfShip(LinkedList<Dimension> buletsOfShip) {
        this.buletsOfShip = buletsOfShip;
    }

    public void S  (LinkedList<Dimension> buletsofship) {
        this.buletsOfShip = buletsofship;
    }

    public void setBuletsOfUfo(LinkedList<Dimension> buletsofufo) {
        this.buletsOfUfo = buletsofufo;
    }

    public void setFile(String file) {
        this.file = file;
    }


    public void setUfo(LinkedList<UFO> ufo) {
        this.ufo = ufo;
    }

    public float getTime() {
        return time;
    }

    public int getHit() {
        return hit;
    }

    public LinkedList<Dimension> getBonus() {
        return bonus;
    }

    public LinkedList<Dimension> getBuletsofship() {
        return buletsOfShip;
    }

    public LinkedList<Dimension> getBuletsofufo() {
        return buletsOfUfo;
    }


    public LinkedList<UFO> getUfo() {
        return ufo;
    }

    public String getFile() {
        return file;
    }


}