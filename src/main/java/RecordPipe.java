import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiOperations;

public class RecordPipe extends AgiOperations implements Pipeline.Command<String> {

    private String inputName;

    public String getInputName() {
        return inputName;
    }

    public RecordPipe(String name) {
        this.inputName = name;
    }

    @Override
    public String getCommandName() {
        return getInputName();
    }

    @Override
    public void execute(String input) {
        try {
            recordFile(input, "wav", "#", -1);
        } catch (AgiException e) {
            e.printStackTrace();
        }
    }
}
