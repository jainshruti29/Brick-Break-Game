import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener 
 {
    boolean play=false;
    int score=0;
    int totalBricks=21;
    Timer time;
    int delay=1;
    int playerX=310;
    int ballposX=120;
    int ballposY=250;
    int ballXdir=-1;
    int ballYdir=-2;
    Bricks brc;
    public Gameplay()
    {
        brc=new Bricks(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time=new Timer(delay,this); 
        time.start();
        
    }
    public void paint(Graphics g)
    {
         g.setColor(Color.black);
         g.fillRect(1, 1, 692, 592);

         brc.draw((Graphics2D) g);

         g.setColor(Color.yellow);
         g.fillRect(0,0,3,592);
         g.fillRect(0,0,692,3);
         g.fillRect(691,0,3,592);

         g.setColor(Color.white);
         g.setFont(new Font("serif",Font.BOLD,25));
         g.drawString("Score:"+score,590,30);
         
         g.setColor(Color.green);
         g.fillRect(playerX,550,100,8);

         g.setColor(Color.yellow);
         g.fillOval(ballposX,ballposY,20,20);

         if(totalBricks<=0)
         {
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.green);
            g.setFont(new Font("serif",Font.BOLD,45));
            g.drawString("You Won",260,300);
            g.setColor(Color.red);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to Restart",230,350);
         }

         if(ballposY>570)
         {
             play=false;
             ballXdir=0;
             ballYdir=0;
             g.setColor(Color.red);
             g.setFont(new Font("serif",Font.BOLD,45));
             g.drawString("Game Over \n Score:"+score,190,300);

             g.setFont(new Font("serif",Font.BOLD,30));
             g.drawString("Press Enter to Restart",230,350);

         }

         g.dispose();
    }
    public void actionPerformed(ActionEvent e)
    {
        time.start();
        if(play)
        {
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
            {
                ballYdir=-ballYdir;

            }
            A:for(int i=0;i<brc.map.length;i++)
            {
            for(int j=0;j<brc.map[0].length;j++)
            {
                if(brc.map[i][j]>0)
                {
                    int bricksX=j*brc.width+80;
                    int bricksY=i*brc.height+50;
                    int brickwidth=brc.width;
                    int brickheight=brc.height;

                    Rectangle rect=new Rectangle(bricksX,bricksY,brickwidth,brickheight);
                    Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
                    Rectangle brickRect=rect;
                    if(ballRect.intersects(brickRect))
                    {
                        brc.setValue(0,i,j);
                        totalBricks--;
                        score+=5;
                        if(ballposX+19<=brickRect.x||ballposX+1>=brickRect.x+brickRect.width)
                        {
                            ballXdir=-ballXdir;

                        }
                        else{
                            ballYdir=-ballYdir;
                        }
                        break A;
                    }
                }
            }
            }
            ballposX+=ballXdir;
            ballposY+=ballYdir;
            if(ballposX<0)
            {
                ballXdir=-ballXdir;
            }
            if(ballposY<0)
            {
                ballYdir=-ballYdir;
            }
            if(ballposX>670)
            {
                ballXdir=-ballXdir;
            }
        }
        repaint();

    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            if(playerX>=600)
            {
                playerX=600;
            }
            else{
                moveRight();
            }

        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if(playerX<=10)
            {
                playerX=10;
            }
            else{
                moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(!play)
            {
                play=true;
                ballposX=120;
                ballposY=350;
                ballXdir=-1;
                ballYdir=-2;
                playerX=350;
                score=0;
                totalBricks=21;
                brc=new Bricks(3,7);
                repaint();

            }


        }


    }
    
    public void moveRight()
    {
        play=true;
        playerX+=20;
    }
    public void moveLeft()
    {
        play=true;
        playerX-=20;
    }
}
