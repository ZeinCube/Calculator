package sample;

public class History {
    private StringBuilder history = new StringBuilder();

    public void add(NumDo numDo){
        history.append(numDo.toString());
    }

    public void add(String string){
        history.append(string);
    }

    public void clear(){
        history = new StringBuilder();
    }

    public void changeLastSimbol(String simbol){
        history.deleteCharAt(history.length()-1);
        history.append(simbol);
    }

    public void deleteFrom(Integer index){
        history.delete(history.length()-1-index,history.length()-1);
    }

    public void backSpaceClick(){
        history.deleteCharAt(history.length()-1);
    }

    @Override
    public String toString() {
        return history.toString();
    }
}
