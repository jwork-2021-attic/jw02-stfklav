package w02;

public class Snake //蛇精
{
    private static Snake theSnake;
    private Sorter sorter;
    
    
    private Snake(){}

    public static Snake getTheSnake() {
        if (theSnake == null) {
            theSnake = new Snake();
        }
        return theSnake;
    }
     
    public void setSorter(Sorter sorter) 
    {
        this.sorter = sorter;
    }

    public String lineUp(Matrix matrix) 
    {//蛇精排列妖怪们
        String log = new String();//交换步骤记录
        System.out.println(matrix.toString());

        if (sorter == null) 
        {
            return null;
        }

        Linable[] linables = matrix.toArray();
        int[] ranks = new int[linables.length];

        for (int i = 0; i < linables.length; i++) 
        {
            ranks[i] = linables[i].getValue();
        }

        sorter.load(ranks);
        sorter.sort();
        //System.out.println(sorter.getPlan()); 

        String[] sortSteps = this.parsePlan(sorter.getPlan());//用换行符分开每一步骤

        for (String step : sortSteps) {
            //System.out.println(step);//输出步骤以调试
            this.execute(step, matrix);//执行一步交换，改变 matrix
            System.out.println( matrix.toString());//输出改变后的 matrix
            log +=  matrix.toString();
            log += "\n[frame]\n";
        }

        return log;
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(String step,Matrix matrix) 
    {
        /*根据执行的一步更改Monster关联的position
        比如原来妖怪4 妖怪3，4在位置1，3在位置2，现在要交换，
        根据号码取出妖怪4和妖怪3并交换它们关联的位置号码。
        这样Monster按position打印出来就关联上与原来不同的妖怪*/
        String[] couple = step.split("<->");
        
        matrix.getPostionbyValue(Integer.parseInt(couple[0]))
            .swapMonster(matrix.getPostionbyValue(Integer.parseInt(couple[1])));
    }
}