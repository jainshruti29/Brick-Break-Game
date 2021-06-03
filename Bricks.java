import java.awt.*;
public class Bricks {
    public int map[][];
    public int width;
    public int height;
    public Bricks(int row,int col)
    {
        map=new int[row][col];
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                map[i][j]=1;
            }
        }
        width=540/col;
        height=150/row;
    }
    public void draw(Graphics2D g)
    {
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                if(map[i][j]>0)
                {
                    if(i==0)
                    g.setColor(Color.red);
                    else if(i==1)
                    g.setColor(Color.green);
                    else
                    g.setColor(Color.blue);
                    g.fillRect(j*width+80, i*height+50, width, height);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j*width+80, i*height+50, width, height);
                }

            }
        }
    }
    public void setValue(int value, int row, int col)
    { 
        {
            map[row][col]=value;
        }
    }

}
