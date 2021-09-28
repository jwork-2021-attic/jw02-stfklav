package w02;

import w02.Line.Position;

public class Monster implements Linable
{
    private final int val;//代表该妖精的编号，排序用（升序），从1开始
    
    private final int r;
    private final int g;
    private final int b;

    private Position position;

    Monster(int val, int r, int g, int b) 
    {
        this.val = val;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int rank() 
    {
        return this.val;
    }

    @Override
    public String toString() 
    {
        String s = Integer.toString(this.rank());
        if(s.length()<3)
        {
            if(s.length()==1) 
            {
                s = s + "  ";
            }
            else
            {
                s = s + " ";
            }
        }
        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m" + s + "\033[0m";
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }


    @Override
    public int getValue() {
        return this.rank();
    }

    public void swapPosition(Monster another) 
    {
        Position p = another.position;//拿到another的position
        this.position.setLinable(another);//把原来这个monster关联的position关联的monster换成another
        p.setLinable(this);//把另一个position和本monster关联好
    }
}