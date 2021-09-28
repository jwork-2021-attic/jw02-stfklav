package w02;

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Scene 
{
    public static void main(String[] args)  throws IOException
    {
        final int COUNT = 256;

        //256个妖怪的一排位置
        Line myLine = new Line(COUNT);

        //256个妖怪
        Monster []monsters = new Monster[COUNT];
        int r = 255;
        int g = 0;
        int b = 0;

        for(int i = 0; i < COUNT; ++i)
        {
            if(monsters[i] == null)
            {//随机一个颜色
                /*Random random = new Random();
                r = random.nextInt(256);
                g = random.nextInt(256);
                b = random.nextInt(256); */
                if(i < COUNT/2)
                {
                    g = (g + 255/(COUNT/2)) % 256;
                    b = (b + 255/(COUNT/2)) % 256;
                }
                else
                {
                    b = 255;
                    r = Math.abs(r - 255/(COUNT/2));
                    g = Math.abs(g - 255/(COUNT/2));
                }
                monsters[i] = new Monster(i + 1 , r, g, b);
            }
            Random random2 = new Random();
            myLine.put(monsters[i], random2.nextInt(COUNT));//将全部妖怪随机放入256个位置
        }

        //myLine.output();

        //蛇精
        Snake theSnake = Snake.getTheSnake();
        
        //排序器 
        Sorter sorter = new BubbleSorter();
        //Sorter sorter = new SelectSorter();

        //蛇精拿到排序器
        theSnake.setSorter(sorter);
        //蛇精用刚设置好的位置错误的line排序，得到log
        String log = theSnake.lineUp(myLine);
 
        //把得到的记录写进result.txt
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("myresult.txt"));
        writer.write(log);
        writer.flush();
        writer.close();
 
    }
}