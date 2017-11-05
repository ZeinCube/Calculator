package sample;

import java.util.ArrayList;
import java.util.List;

public class ArrayOfNumbers {

    public List<NumDo> numbers = new ArrayList<>();

    public void add(NumDo numDo){
        numbers.add(numDo);
    }

    public void add(Number number){
        numbers.add(new NumDo(number , ""));
    }

    public void changeLastSimbol(String whatToDo){
        numbers.get(numbers.size()-1).whatToDo = whatToDo;
    }

    public void changeFirst(Number result){
        numbers.remove(0) ;
        numbers.get(0).number = result ;
    }

    public void changeLastNum(Number number){
        if(numbers.size()!=0){
            numbers.get(numbers.size()-1).number = number;
        }
    }

    public NumDo get(Integer index){
        return numbers.get(index);
    }

    public ArrayOfNumbers() {
        this.numbers = new ArrayList<>();
    }

    public void backSpace(ArrayOfNumbers array){

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (NumDo numDo : numbers){
            if(!numDo.whatToDo.equals("√")) {
                res.append(NumDo.roundOf(numDo.number).toString()).append(numDo.whatToDo);
            }else{
                res.append(numDo.whatToDo).append(NumDo.roundOf(numDo.number).toString());
            }
        }
        return res.toString();
    }
}
