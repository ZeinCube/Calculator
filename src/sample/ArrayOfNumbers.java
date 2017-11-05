package sample;

import java.util.ArrayList;
import java.util.List;

public class ArrayOfNumbers {

    List<NumDo> numbers = new ArrayList<>();

    void add(NumDo numDo){
        numbers.add(numDo);
    }

    void add(Number number){
        numbers.add(new NumDo(number , ""));
    }

    void changeLastSimbol(String whatToDo){
        numbers.get(numbers.size()-1).whatToDo = whatToDo;
    }

    void changeFirst(Number result){
        numbers.remove(0) ;
        numbers.get(0).number = result ;
    }

    public void changeLastNum(Number number){
        if(numbers.size()!=0){
            numbers.get(numbers.size()-1).number = number;
        }
    }

    NumDo get(Integer index){
        return numbers.get(index);
    }

    void clear(){
        numbers.clear();
    }

    public Integer getSize(){
        return numbers.size();
    }

    ArrayOfNumbers() {
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
