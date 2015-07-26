import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by Erwan on 20/07/2015.
 */
public class MainPanel extends JPanel {

    private BufferedImage display;
    private Player player;

    public MainPanel(Player p, int w, int h) {
        this.player = p;

        this.setLayout(new BorderLayout());
        this.display = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                double oldX;
                double oldY;
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    oldX = player.getPosX();
                    oldY = player.getPosY();
                    player.setPosX(player.getPosX() + Math.cos(degreeToRad(player.getPlayerAngle())) / 16);
                    player.setPosY(player.getPosY() + Math.sin(degreeToRad(player.getPlayerAngle())) / 16);
                    if (Engine.map.get((int) player.getPosX()).get((int) player.getPosY()) > 0)
                    {
                        player.setPosX(oldX);
                        player.setPosY(oldY);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    oldX = player.getPosX();
                    oldY = player.getPosY();
                    player.setPosX(player.getPosX() - Math.cos(degreeToRad(player.getPlayerAngle())) / 16);
                    player.setPosY(player.getPosY() - Math.sin(degreeToRad(player.getPlayerAngle())) / 16);
                    if (Engine.map.get((int) player.getPosX()).get((int) player.getPosY()) > 0)
                    {
                        player.setPosX(oldX);
                        player.setPosY(oldY);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    player.setPlayerAngle(player.getPlayerAngle() + 30);
              }
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    player.setPlayerAngle(player.getPlayerAngle() - 30);
               }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private double degreeToRad(int angle)
    {
        return angle * (Math.PI / 2400);
    }

    public void updateDisplay()
    {
        this.revalidate();
        this.repaint();

        for (int i = 0; i < this.getWidth(); ++i)
            for (int j = 0; j < this.getHeight(); ++j)
                display.setRGB(i, j, new Color(255, 255, 255).getRGB());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.display, 0, 0, null);
    }

    public BufferedImage getDisplay() {
        return display;
    }

    public void setDisplay(BufferedImage display) {
        this.display = display;
    }
}
