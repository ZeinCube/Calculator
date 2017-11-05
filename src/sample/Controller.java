package sample;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class Controller {

    public TextField resultField;
    private ArrayOfNumbers arrayOfNumbers = new ArrayOfNumbers();
    private String currentNumberStr = "0";
    private History history = new History();
    private boolean isRedactingLastAction = false;
    public TextField historyField;
    private Memory memory = new Memory();


    private void calculate(){
        while(arrayOfNumbers.numbers.size()>1){
            switch (arrayOfNumbers.get(0).whatToDo){
                    case "+" :
                        arrayOfNumbers.changeFirst(arrayOfNumbers.get(0).number.doubleValue()+arrayOfNumbers.get(1).number.doubleValue());
                        break;

                    case "-" :
                        arrayOfNumbers.changeFirst(arrayOfNumbers.get(0).number.doubleValue()-arrayOfNumbers.get(1).number.doubleValue());
                        break;

                    case "*" :
                        arrayOfNumbers.changeFirst(arrayOfNumbers.get(0).number.doubleValue()*arrayOfNumbers.get(1).number.doubleValue());
                        break;

                    case "/" :
                        arrayOfNumbers.changeFirst(arrayOfNumbers.get(0).number.doubleValue()/arrayOfNumbers.get(1).number.doubleValue());
                        break;

                    case "" :
                        resultField.setText(NumDo.roundOf(arrayOfNumbers.numbers.get(0).number).toString());
                        break;
                }
            }
            currentNumberStr = arrayOfNumbers.numbers.toString();
    }

    private boolean isNullField(){
        boolean result = false;
        if(resultField.getText().equals("0")){
            result=true;
        }
        return result;
    }

    private void numButClick(String number){
        isRedactingLastAction = false;
        if(isNullField())
            resultField.setText("");
        resultField.setText(resultField.getText() + number);
        historyField.setText(historyField.getText() + number);
        currentNumberStr = currentNumberStr +number;
    }

    private void rewrite(){
        historyField.setText(history.toString());
        resultField.setText(arrayOfNumbers.toString());
    }

    public void backSpClick(MouseEvent mouseEvent) {
        if (!isNullField()){
            
        }
    }
    private void handleAction(String simbol){
        NumDo enteredNumDo;
        if(isRedactingLastAction&arrayOfNumbers.numbers.size()!=2){
            if(!arrayOfNumbers.get(arrayOfNumbers.numbers.size()-1).whatToDo.equals("")) {
                history.changeLastSimbol(simbol);
            }else{
                history.add(simbol);
            }
            arrayOfNumbers.changeLastSimbol(simbol);
            rewrite();
        }else{
            if(arrayOfNumbers.numbers.size()>=1){
                if(!currentNumberStr.equals("")) {
                    enteredNumDo = new NumDo(Double.parseDouble(currentNumberStr), simbol);
                    arrayOfNumbers.add(enteredNumDo);
                    history.add(enteredNumDo);
                }else {
                    arrayOfNumbers.changeLastSimbol(simbol);
                    history.add(simbol);
                }
                isRedactingLastAction = true;
                historyField.setText(history.toString());
                calculate();
                resultField.setText(NumDo.roundOf(arrayOfNumbers.numbers.get(0).number).toString()+arrayOfNumbers.numbers.get(0).whatToDo);
                currentNumberStr = "";
            }else{
                if(!currentNumberStr.equals("")) {
                    enteredNumDo = new NumDo(Double.parseDouble(currentNumberStr), simbol);
                    arrayOfNumbers.add(enteredNumDo);
                    history.add(enteredNumDo);
                    historyField.setText(history.toString());
                    resultField.setText(arrayOfNumbers.toString());
                }else{
                    arrayOfNumbers.get(arrayOfNumbers.numbers.size()-1).whatToDo = simbol;
                    history.add(simbol);
                }
                isRedactingLastAction = true;
                currentNumberStr = "";
                rewrite();
            }
        }
    }

    public void butClearClick(MouseEvent mouseEvent) {
        currentNumberStr = "0";

    }

    public void butMCClick(MouseEvent mouseEvent) {
        memory.memoryClear();
    }

    public void butClearAllClick(MouseEvent mouseEvent) {
        resultField.setText("0");
        arrayOfNumbers = new ArrayOfNumbers();
        historyField.setText("");
        currentNumberStr = "";
        history.clear();
    }

    public void but7Click(MouseEvent mouseEvent) {
        numButClick("7");
    }

    public void but8Click(MouseEvent mouseEvent) {
        numButClick("8");
    }

    public void but9Click(MouseEvent mouseEvent) {
        numButClick("9");
    }

    public void butDivClick(MouseEvent mouseEvent) {
        handleAction("/");
    }

    public void butSqrtClick(MouseEvent mouseEvent) {
        if(!isNullField()) {
            if (arrayOfNumbers.numbers.size() == 0) {
                resultField.setText("");
            }

            if (currentNumberStr.length() != 0) {
                history.add(new NumDo(Double.parseDouble(currentNumberStr), "√"));
                currentNumberStr = Double.toString(Math.sqrt(Double.parseDouble(currentNumberStr)));
                arrayOfNumbers.numbers.add(new NumDo(NumDo.roundOf(Double.parseDouble(currentNumberStr))));
            } else {
                currentNumberStr = Double.toString(Math.sqrt(arrayOfNumbers.get(0).number.doubleValue()));
                history.add(new NumDo(Double.parseDouble(currentNumberStr), "√"));
                arrayOfNumbers.numbers.add(new NumDo(NumDo.roundOf(Double.parseDouble(currentNumberStr))));
            }
            isRedactingLastAction = true;
            currentNumberStr = "";
            rewrite();
        }
    }

    public void butMRClick(MouseEvent mouseEvent) {
        arrayOfNumbers.numbers.get(arrayOfNumbers.numbers.size()-1).number = memory.memory;
        rewrite();
    }

    public void but4Click(MouseEvent mouseEvent) {
        numButClick("4");
    }

    public void but6Click(MouseEvent mouseEvent) {
        numButClick("6");
    }

    public void butMultiplyClick(MouseEvent mouseEvent) {
        handleAction("*");
    }

    public void butProcClick(MouseEvent mouseEvent) {
    }

    public void butMSClick(MouseEvent mouseEvent) {
        if(currentNumberStr.length()!=0)
        memory.setMemory(Double.parseDouble(currentNumberStr));
    }

    public void but1Click(MouseEvent mouseEvent) {
        numButClick("1");
    }

    public void but3Click(MouseEvent mouseEvent) {
        numButClick("3");
    }

    public void butMinusClick(MouseEvent mouseEvent) {
        handleAction("-");
    }

    public void butMinDegClick(MouseEvent mouseEvent) {
    }

    public void butMPlusClick(MouseEvent mouseEvent) {
    }

    public void but0Click(MouseEvent mouseEvent) {
        isRedactingLastAction = false;
        if(!Objects.equals(resultField.getText(), "0")){
            resultField.appendText("0");
            currentNumberStr = currentNumberStr.concat("0");
        }
    }

    public void butPMClick(MouseEvent mouseEvent) {
    }

    public void butDotClick(MouseEvent mouseEvent) {
        if(!isRedactingLastAction){
            currentNumberStr = currentNumberStr.concat(".");
            resultField.appendText(".");
            historyField.appendText(".");
        }
    }

    public void butPlusClick(MouseEvent mouseEvent) {
        handleAction("+");
    }

    public void butEqualsClick(MouseEvent mouseEvent) {
        if(currentNumberStr.length()!=0)
            arrayOfNumbers.add(Double.parseDouble(currentNumberStr));
        calculate();
        resultField.setText(NumDo.roundOf(arrayOfNumbers.numbers.get(arrayOfNumbers.numbers.size()-1).number).toString());
        historyField.setText("");
        history.clear();
        history.add(arrayOfNumbers.numbers.get(arrayOfNumbers.numbers.size()-1));
        isRedactingLastAction = true;
        currentNumberStr = "";
    }

    public void but5Click(MouseEvent mouseEvent) {
        numButClick("5");
    }

    public void but2Click(MouseEvent mouseEvent) {
        numButClick("2");
    }
}