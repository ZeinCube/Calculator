package sample;

public class Memory {

    public Number memory = 0;

    public void memoryPlus(Number number){
        memory = memory.doubleValue() + number.doubleValue();
    }

    public void memoryMinus(Number number){
        memory = memory.doubleValue() - number.doubleValue();
    }

    public void memoryClear(){
        memory = 0;
    }

    public void setMemory(Number number){
        memory = number.doubleValue();
    }
//    public Number getMemory (){
//
//    }
}
