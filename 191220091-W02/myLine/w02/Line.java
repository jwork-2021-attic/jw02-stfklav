package w02;

public class Line 
{
    public Line(int length) 
    {
        this.positions = new Position[length];
    }

    private Position[] positions;

    public void put(Linable linable, int i) 
    {//将要排序的和排里的位置关联
        if (this.positions[i] == null) 
        {
            this.positions[i] = new Position(null);   
        }
       
        boolean flag = true;
        if(this.positions[i].linable == null)
        {//位置尚未被占有
            this.positions[i].setLinable(linable);
            flag = true;
            return;
        }
        else
        {//位置已被占
            flag = false;
            while(!flag)
            {
                i = (i+1) % positions.length;
                if (this.positions[i] == null) 
                {
                    this.positions[i] = new Position(null);
                    flag = true;   
                }
                else if(this.positions[i].linable == null)
                {
                    flag = true;
                }
            }
            this.positions[i].setLinable(linable);
            return;
        }
        
    }

    public Linable get(int i) 
    {
        if ((i < 0) || (i > positions.length)) 
        {
            return null;
        } 
        else
        {
            return positions[i].linable;
        }
    }

    public Position getPostionbyValue(int val)
    {
        for(int i = 0;i < positions.length; ++i)
        {
            if(positions[i].linable.getValue() == val)
            {
                return positions[i];
            }
        }
        return null;
    }

    public void output()
    {
        for(int i = 0; i < positions.length; ++i)
        {
            System.out.println(positions[i].linable.getValue() + " ");
        }
    }
    class Position {

        private Linable linable;

        Position(Linable linable) {
            this.linable = linable;
        }

        public void setLinable(Linable linable) 
        {
            this.linable = linable;
            linable.setPosition(this);
        }

        public void swapMonster(Position anotherPosition)
        {//和另一个位置交换关联妖怪
            Linable anotherLinable = anotherPosition.linable;
            anotherPosition.setLinable(this.linable);
            this.setLinable(anotherLinable);
        }

    }

    @Override
    public String toString() {//一行打印
        String lineString = "\t";
        for (Position p : positions) {
            lineString += p.linable.toString();//即Gourd的toString,一块彩色的打印
        }
        return lineString;
    }

    public Linable[] toArray() {
        Linable[] linables = new Linable[this.positions.length];

        for (int i = 0; i < linables.length; i++) {
            linables[i] = positions[i].linable;
        }

        return linables;

    }
}