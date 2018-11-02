import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiOperations;

public class StreamPipe extends AgiOperations implements Pipeline.Command<String> {

    private String inputName;

    public StreamPipe(String name) {
        this.inputName = name;
    }

    public void playBack(String audioRoute){
        try {
            streamFile(audioRoute);
            setExtension("9997");
            setPriority("1");
        } catch (AgiException e) {
            System.out.println("could not play audio file "+audioRoute);
            System.out.println(e.toString());
            System.out.println("");
        }
    }

    @Override
    public String getCommandName() {
        return getInputName();
    }

    @Override
    public void execute(String input) {
        playBack(input);
    }
    public String getInputName() {
        return inputName;
    }
}
