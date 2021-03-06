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
    private boolean isBlockedButOfAction = false;
    static boolean isDouble = false;
    static boolean isResult = false;


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
        isRedactingLastAction = true;
    }

    private boolean isNullField(){
        boolean result = false;
        if(resultField.getText().equals("0") || resultField.getText().equals("Введены неверные данные")){
            result=true;
        }
        return result;
    }

    private void numButClick(String number){
        isBlockedButOfAction = false;
        if(isNullField()) {
            resultField.setText("");
            currentNumberStr = number;
            history.add(number);
            resultField.setText(currentNumberStr);
        }else {
            resultField.appendText(number);
            historyField.appendText(number);
            currentNumberStr += number;
        }
    }

    private void rewrite(){
        historyField.setText(history.toString());
        resultField.setText(arrayOfNumbers.toString());
    }

    public void backSpClick(MouseEvent mouseEvent) {
        if (!(isNullField()||isRedactingLastAction)){
            if(currentNumberStr.length()>=1){

                currentNumberStr = resultField.getText(resultField.getLength()-currentNumberStr.length()+1,resultField.getLength()-1);
                resultField.setText(resultField.getText(0,resultField.getLength()-1));
                historyField.setText(historyField.getText(0,historyField.getLength()-1));
            }
        }else{
            resultField.setText(resultField.getText(0,resultField.getLength()-1)+"0");
        }
    }

    private void handleAction(String simbol) {
        if (!isBlockedButOfAction) {
            NumDo enteredNumDo;
            if (isRedactingLastAction & arrayOfNumbers.numbers.size() != 2) {
                if (!arrayOfNumbers.get(arrayOfNumbers.numbers.size() - 1).whatToDo.equals("")) {
                    history.changeLastSimbol(simbol);
                } else {
                    history.add(simbol);
                }
                arrayOfNumbers.changeLastSimbol(simbol);
                rewrite();
            } else {
                if (arrayOfNumbers.numbers.size() >= 1) {
                    if (!currentNumberStr.equals("")) {
                        enteredNumDo = new NumDo(Double.parseDouble(currentNumberStr), simbol);
                        arrayOfNumbers.add(enteredNumDo);
                        history.add(enteredNumDo);
                    } else {
                        arrayOfNumbers.changeLastSimbol(simbol);
                        history.add(simbol);
                    }
                    isRedactingLastAction = true;
                    calculate();
                    rewrite();
                    currentNumberStr = "";
                } else {
                    if (!currentNumberStr.equals("")) {
                        enteredNumDo = new NumDo(Double.parseDouble(currentNumberStr), simbol);
                        arrayOfNumbers.add(enteredNumDo);
                        history.add(enteredNumDo);
                    } else {
                        if (arrayOfNumbers.getSize() != 0) {
                            arrayOfNumbers.get(arrayOfNumbers.numbers.size() - 1).whatToDo = simbol;
                            history.add(simbol);
                        } else {
                            resultField.setText("0");
                        }
                    }
                    isRedactingLastAction = true;
                    currentNumberStr = "0";
                    rewrite();
                }
            }
        }
    }

    public void butClearClick(MouseEvent mouseEvent) {
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
        if (!isNullField()) {
            if (Double.parseDouble(currentNumberStr) >= 0 & !isBlockedButOfAction) {
                if (arrayOfNumbers.numbers.size() == 0) {
                    resultField.setText("0");
                }

                if (currentNumberStr.length() != 0) {
                    history.add(new NumDo(Double.parseDouble(currentNumberStr), "√"));
                    currentNumberStr = Double.toString(Math.sqrt(Double.parseDouble(currentNumberStr)));
                    arrayOfNumbers.add(new NumDo(NumDo.roundOf(Double.parseDouble(currentNumberStr))));
                } else {
                    currentNumberStr = Double.toString(Math.sqrt(arrayOfNumbers.get(0).number.doubleValue()));
                    history.add(new NumDo(Double.parseDouble(currentNumberStr), "√"));
                    arrayOfNumbers.add(new NumDo(NumDo.roundOf(Double.parseDouble(currentNumberStr))));
                }
                isRedactingLastAction = true;
                //currentNumberStr = "";
                rewrite();
            } else {
                if (arrayOfNumbers.get(arrayOfNumbers.getSize() - 1).number.doubleValue() < 0) {
                    history.clear();
                    arrayOfNumbers.clear();
                    rewrite();
                    resultField.setText("Введены неверные данные");
                    isBlockedButOfAction = true;
                }
            }
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

    public void butRevertClick(MouseEvent mouseEvent) {
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

    public void butPlusMinusClick(MouseEvent mouseEvent) {
    }

    public void butDotClick(MouseEvent mouseEvent) {
        if(!isRedactingLastAction&!isDouble&!isNullField()&!isResult){
            isDouble = true;
            currentNumberStr = currentNumberStr.concat(".");
            resultField.appendText(".");
            historyField.appendText(".");
        }
    }

    public void butPlusClick(MouseEvent mouseEvent) {
        handleAction("+");
    }

    public void butEqualsClick(MouseEvent mouseEvent) {
        if(arrayOfNumbers.getSize()!=0) {
            if (currentNumberStr.length() != 0)
                arrayOfNumbers.add(Double.parseDouble(currentNumberStr));
            calculate();
            resultField.setText(NumDo.roundOf(arrayOfNumbers.numbers.get(arrayOfNumbers.numbers.size() - 1).number).toString());
            historyField.setText("");
            history.clear();
            history.add(arrayOfNumbers.numbers.get(arrayOfNumbers.numbers.size() - 1));
            isRedactingLastAction = true;
            currentNumberStr = "";
        }
    }

    public void but5Click(MouseEvent mouseEvent) {
        numButClick("5");
    }

    public void but2Click(MouseEvent mouseEvent) {
        numButClick("2");
    }
}