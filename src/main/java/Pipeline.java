import java.util.Hashtable;
import java.util.Stack;

public class Pipeline  {
    private Hashtable<String, String> contextInit = new Hashtable<String, String>();
    private Stack<Command<Object>> pipelineCommandStack = new Stack<Command<Object>>();


    public void addCommand(Command command, String commandInput){
        pipelineCommandStack.add(command);
        System.out.println(command.getCommandName());
        System.out.println(commandInput);
        contextInit.put(command.getCommandName(),commandInput);
    }

	public void execute(){

        for (Command<Object> command : pipelineCommandStack){
			command.execute(contextInit.get(command.getCommandName()));
        }
    }

    public interface Command<T> {
        String getCommandName();
        void execute(T input);
    }
}
