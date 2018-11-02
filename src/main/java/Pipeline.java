import java.util.Random;
import java.util.Stack;
import javax.naming.Context;
import javax.naming.NamingException;

public class Pipeline  {
    private Context pipeContext;
    private Stack<Command<Object>> pipelineCommandStack = new Stack<Command<Object>>();


    public void addCommand(Command command, String commandInput){
        pipelineCommandStack.add(command);
        try {
            pipeContext.bind(command.getCommandName(),commandInput);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void fillPipeContext(){
        Random fileNumber = new Random();
        String fileName = "/tmp/google_audio"+fileNumber.nextInt();
    }

    public void execute(){
        String commandName;
        for (Command command : pipelineCommandStack){
            try {
                commandName = (String) pipeContext.lookup(command.getCommandName());
                command.execute(commandName);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    public interface Command<T> {
        String getCommandName();
        void execute(T input);
    }
}
