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

    public String lineUp(Line line) 
    {//蛇精排列妖怪们
        String log = new String();//交换步骤记录
        System.out.println(line.toString());

        if (sorter == null) 
        {
            return null;
        }

        Linable[] linables = line.toArray();
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
            this.execute(step,line);//执行一步交换，改变line
            System.out.println(line.toString());//输出改变后的line
            log += line.toString();
            log += "\n[frame]\n";
        }

        return log;
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(String step,Line line) 
    {
        /*根据执行的一步更改Gourd关联的position
        比如原来葫芦4 葫芦3，4在位置1，3在位置2，现在要交换，
        根据号码取出葫芦4和葫芦3并交换它们关联的位置号码。
        这样line按position打印出来就关联上与原来不同的葫芦*/
        String[] couple = step.split("<->");
        
        line.getPostionbyValue(Integer.parseInt(couple[0]))
            .swapMonster(line.getPostionbyValue(Integer.parseInt(couple[1])));
    }
}