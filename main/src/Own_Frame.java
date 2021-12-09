import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;


public class Own_Frame extends JFrame implements KeyListener {
    LinkedList<UFO> alien = new LinkedList<>();
    LinkedList<Dimension> buletsOfUfo = new LinkedList<>();
    LinkedList<Dimension> bonus = new LinkedList<>();
    LinkedList<Dimension> buletsOfShip = new LinkedList<>();
    String file = System.getProperty("user.dir");
    private static JFrame frame;
    private Dimension sizes;
    private boolean shoot = false;
    private boolean ifLiffButton = false;
    private boolean pauses = false;
    private int x = 0;
    Random rand = new Random();
    public int aim = 0;


    JFrame getFrame() {
        sizes = Toolkit.getDefaultToolkit().getScreenSize();

        frame = new JFrame();
        invidualeLauout();
        return frame;
    }

    private void invidualeLauout() {

        frame.setSize(sizes.width, sizes.height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setIconImage(name(this.toString(file, "\\src\\Backgruond\\ship.png")).getImage());
        frame.setTitle("Inverdersi");
        Draw parts = new Draw();
        frame.getContentPane().add(parts);
        frame.addKeyListener(this);
        frame.pack();
        frame.setVisible(true);
        Calendar c = Calendar.getInstance();
        long start = c.getTimeInMillis();
        int x;
        int NumberOfShips = 5;
        Ship ship = new Ship(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height - 130);


        do {


            ship.setX(ship.getX() + getX());
            setX(0);
            ship.setShoot(shoot);


            while (pauses == true) {
                try {

                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (pauses == false)
                    break;

            }


            while (aim == 0) {       //Odpowiada za wyświetlenie początkowego ekranu
                repaint();
                try {

                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
                aim = 1;
                parts.setHit(aim);
            }


            if (alien.size() == 0) {    //Po zniszczeniu wszystkich statków generuje nowy poziom

                alien = level(NumberOfShips, sizes);
                NumberOfShips *= 2;
            }

            parts.setUfo(alien); //Pauzuje gre na 10 ms
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ship.getX() < 0)
                ship.setX(0);
            if (ship.getX() > sizes.width)
                ship.setX(sizes.width);


            if (ship.getShoot() == true && ifLiffButton == true) {
                buletsOfShip.add(new Dimension(ship.getX() + 50, Toolkit.getDefaultToolkit().getScreenSize().height - 130));
                ship.setShoot(false);
                ifLiffButton = false;
            }

            for (int i = 0; i < buletsOfShip.size(); i++)    //Co ture przesuwa pocisk statku
            {
                if (buletsOfShip.get(i).height <= 0) {
                    buletsOfShip.remove(i);
                } else {
                    buletsOfShip.get(i).height -= 10;
                }
            }


            for (int i = 0; i < alien.size(); i++)          //Jeśli statek trafi obcego to go niszczy
            {
                for (int j = 0; j < buletsOfShip.size(); j++)
                    if (alien.get(i).getY() <= buletsOfShip.get(j).height) {

                        if ((alien.get(i).getY() + alien.get(i).getHight() >= buletsOfShip.get(j).height)) {

                            if (alien.get(i).getX() <= buletsOfShip.get(j).width) {
                                if (alien.get(i).getX() + alien.get(i).getWight() >= buletsOfShip.get(j).width) {
                                    if (rand.nextInt(100) == 1) {
                                        bonus.add(new Dimension(alien.get(i).getX(), alien.get(i).getY()));
                                    }

                                    alien.remove(i);
                                    buletsOfShip.remove(j);
                                    ship.setDestroied(ship.getDestroied() + 1);
                                    break;
                                }

                            }
                        }
                    }

            }

            for (int i = 0; i < bonus.size(); i++)        //Gdy bonus trafi w statek to zwiększa ilość żyć
            {

                if (((int) ship.getX() + ship.getWight() >= (int) bonus.get(i).width)) {
                    if (((int) ship.getX() <= (int) bonus.get(i).width)) {
                        if ((int) ship.getY() - ship.getHight() - 30 <= (int) bonus.get(i).height) {
                            if ((int) ship.getY() - 30 >= (int) bonus.get(i).height) {
                                ship.setLife(ship.getLife() + 1);
                                bonus.remove(i);
                                break;
                            }

                        }
                    }
                }

            }


            for (int i = 0; i < bonus.size(); i++) {           //Przesuwa bonusy
                if (bonus.get(i).height < sizes.height)
                    bonus.get(i).height += 10;
                else
                    bonus.remove(i);
            }


            for (int i = 0; i < alien.size(); i++)            //Co ture przesuwa obcego
            {
                alien.get(i).setX((alien.get(i).getX() + 1) % sizes.width);
            }

            for (int i = 0; i < alien.size(); i++)          //Co ture losuje czy obcy wystrzeli pocisk
            {
                if (rand.nextInt(1000) == 1) {
                    buletsOfUfo.add(new Dimension(alien.get(i).getX(), alien.get(i).getY()));

                }
            }

            for (int i = 0; i < buletsOfUfo.size(); i++) {            //Przesuwa Pociski
                if (buletsOfUfo.get(i).height > sizes.height) {
                    buletsOfUfo.remove(i);
                } else {
                    buletsOfUfo.get(i).height += 10;
                }


            } //CO ture przesuwa pociski_obcych
            Boolean flaga = true;
            for (int i = 0; i < buletsOfUfo.size(); i++) { //Po trafieniu odejmuje życie
                if (flaga == false)
                    break;
                if (((int) ship.getX() + 100 >= (int) buletsOfUfo.get(i).width)) {
                    if (((int) ship.getX() <= (int) buletsOfUfo.get(i).width)) {
                        if ((int) sizes.height - 130 <= (int) buletsOfUfo.get(i).height) {
                            if ((int) sizes.height - 30 >= (int) buletsOfUfo.get(i).height) {
                                ship.setLife(ship.getLife() - 1);
                                buletsOfUfo.remove(i);
                                if (ship.getLife() == 0) {
                                    flaga = false;
                                    aim = 2;
                                   parts.setHit(2);
                                }
                                break;
                            }

                        }
                    }
                }
            }


            frame.repaint();
            parts.setHit(aim);
            parts.setBuletsOfUfo(buletsOfUfo);
            parts.setBonus(bonus);
            parts.setBuletsOfShip(buletsOfShip);
            parts.setShip(ship);
            parts.setUfo(alien);

        } while (ship.getLife() > 0);
        parts.setTime(((Calendar.getInstance().getTimeInMillis() - start) / 1000));

        frame.repaint();


    }





    public static ImageIcon name(String s) {
        ImageIcon imageincon = null;
        imageincon = new ImageIcon(s);

        return imageincon;
    }


    public String toString(String a, String s) {
        return a + s;
    }


    LinkedList<UFO> level(int NumberofEnemies, Dimension Sizes) {
        LinkedList<UFO> alien = new LinkedList<>();

        for (int i = 0; i < NumberofEnemies; i++) {
            alien.add(new UFO(100 * i, 100 + (100 * (i % 2))));
        }

        return alien;

    }


    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == 'p' || 'P' == c) {
            if (pauses == false)
                pauses = true;
            else
                pauses = false;
        }


    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == 'a' || 'A' == c) setX(getX() - 30);
        if (c == 'd' || 'D' == c) setX(getX() + 30);
        if (c == 'm' || 'M' == c) shoot = true;


    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == 'm' || 'M' == c) ifLiffButton = true;


    }

    public void setX(int x) {
        this.x = x;
    }


    public int getX() {
        return x;
    }


}




