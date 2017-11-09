package sample;

public class NumDo{
    Number number  = 0;
    String whatToDo = "";

    NumDo() {
    }

    NumDo(Number number, String whatToDo) {
        this.number = number;
        this.whatToDo = whatToDo;
    }

    NumDo(Number number) {
        this.number = number;
    }

    static Number roundOf(Number number){
        if (number.doubleValue()==number.longValue()){
            Controller.isDouble = false;
            return number.longValue();
        }else{
            Controller.isDouble = true;
            return number.doubleValue();
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if(!whatToDo.equals("")&!whatToDo.equals("√")) {
            res.append(roundOf(number).toString()).append(whatToDo);
        }else{
            res.append(whatToDo).append(NumDo.roundOf(number).toString());
        }
        return res.toString();
    }
}
