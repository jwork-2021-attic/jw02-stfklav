package w02;

public class Matrix 
{
    private Position[] positions;//一维数组表示矩阵
    private int row;
    private int col;

    public Matrix(int i, int row, int col) 
    {
        this.positions = new Position[i];
        this.row = row;
        this.col = col;
    }

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
    public String toString() {//打印矩阵
        String matrixString = "\t";
        for (int i = 0; i<this.row; ++i) 
        {
            for(int j = 0; j< this.col; ++j)
            {
                Position p = positions[i*col+j];
                matrixString += p.linable.toString();//即Monster的toString,一块彩色的打印
            }
            matrixString += "\n";
            matrixString += "\t";
        }
        /*for(int i = 0; i<positions.length; ++i)
        {
            matrixString += positions[i].linable.toString();
        }*/
        return matrixString;
    }

    public Linable[] toArray() {
        Linable[] linables = new Linable[this.positions.length];

        for (int i = 0; i < linables.length; i++) {
            linables[i] = positions[i].linable;
        }

        return linables;

    }
}