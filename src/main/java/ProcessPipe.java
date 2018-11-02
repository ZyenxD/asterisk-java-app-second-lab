import org.asteriskjava.fastagi.AgiOperations;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ProcessPipe extends AgiOperations implements Pipeline.Command<String> {

    public String getInputName() {
        return inputName;
    }

    private String inputName;

    public ProcessPipe(String name) {
        this.inputName = inputName;
    }

    public void runShCommand(String fullCommand) {

        try {
            Process process = Runtime.getRuntime().exec(fullCommand);
            int exitCode = 0;
            if(fullCommand.contains(HelloAgiScript.GOOGLE_ASSISTANT_COMMAND)){
                process.waitFor(30, TimeUnit.SECONDS);
            }else{
                exitCode = process.waitFor();
            }
            System.out.println(fullCommand+" exit with code "+exitCode);
            System.out.println("");
        } catch (IOException | InterruptedException e) {
            System.out.println(fullCommand+" failed");
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
        runShCommand(input);
    }
}
