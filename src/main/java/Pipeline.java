import java.util.Hashtable;
import java.util.Stack;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Pipeline  {
    private Context pipeContext;
    private Hashtable<String, String> contextInit = new Hashtable<String, String>();
    private Stack<Command<Object>> pipelineCommandStack = new Stack<Command<Object>>();


    public void addCommand(Command command, String commandInput){
        pipelineCommandStack.add(command);
        contextInit.put(command.getCommandName(),commandInput);
    }

    public void execute() throws NamingException {
        String commandName;
        pipeContext = new InitialContext(contextInit);
        for (Command<Object> command : pipelineCommandStack){
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
