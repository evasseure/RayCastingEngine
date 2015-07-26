import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Erwan on 20/07/2015.
 */
public class Engine
{
    MainPanel panel;
    public static ArrayList<ArrayList<Integer>> map;
    private Integer mapWidth;
    private Integer mapHeight;
    private Player player;
    private static Integer delay = 10;
    private double time = 0;
    private double oldTime = 0;

    private int width;
    private int height;
    private int centerX;
    private int centerY;
    private int angle6 = 80;
    private int angle30 = 400;
    private int angle60 = 800;
    private int angle180 = 2400;
    private int angle360 = 4800;

    private Color[] colors = new Color[]{Color.green, Color.black.red, Color.orange, Color.white, Color.yellow};

    public Engine(Player player, MainPanel p, String mapPath, int w, int h)
    {
        this.width = w;
        this.height = h;
        this.centerX = w / 2;
        this.centerY = h / 2;
        this.player = player;
        panel = p;
        map = new ArrayList<>();
        loadMap(mapPath);
        mapWidth = map.size();
        mapHeight = map.get(0).size();
        for (int i = 0; i < mapWidth; ++i) {
            for (int j = 0; j < mapHeight; ++j)
                System.out.print(map.get(i).get(j));
            System.out.println();
        }
    }

    private void loadMap(String path)
    {
        BufferedReader br = null;

        try {

            String line;

            br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int i = 0; i < line.length(); ++i)
                    tmp.add((int)line.charAt(i) - 48);
                map.add(tmp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private double degreeToRad(int angle)
    {
        return angle * (Math.PI / angle180);
    }

    public void startSimulation()
    {
        ActionListener taskPerformer = evt -> {
            panel.updateDisplay();
            player.setRadAngle(player.getPlayerAngle() - angle30);
            for (int r = 0; r < angle60; r++)
            {
                player.setFixAngle(r - angle30);
                player.setRadX(player.getPosX());
                player.setRadY(player.getPosY());
                player.setPartX((Math.cos(degreeToRad(player.getRadAngle())) * (1 / Math.cos(degreeToRad(player.getFixAngle())))) / 80);
                player.setPartY((Math.sin(degreeToRad(player.getRadAngle())) * (1 / Math.cos(degreeToRad(player.getFixAngle())))) / 80);
                int hit = 0;
                int distance = 1;
                while (hit == 0)
                {
                    player.setRadX(player.getRadX() + player.getPartX());
                    player.setRadY(player.getRadY() + player.getPartY());
                    hit = map.get((int)player.getRadX()).get((int) player.getRadY());
                    distance++;
                }

                int wallDist = 20000 / distance;

                Graphics2D g = panel.getDisplay().createGraphics();
                g.setColor(Color.cyan);
                g.drawLine(r, 0, r, centerY - wallDist);
                g.setColor(colors[hit - 1]);
                g.drawLine(r, centerY - wallDist, r, centerY + wallDist);
                g.setColor(Color.lightGray);
                g.drawLine(r, centerY + wallDist, r, height);
                g.dispose();
                player.setRadAngle(player.getRadAngle() + 1);
            }
            panel.repaint();
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.setRepeats(true);
        timer.start();
    }
}
