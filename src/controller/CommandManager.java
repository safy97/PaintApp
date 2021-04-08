package controller;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class CommandManager {
    private static Deque<ICommand> commands  = new LinkedList<>();
    private static Stack <ICommand> undoCommand = new Stack<>();
    private static CommandManager commandManager;
    private CommandManager(){}
    public static CommandManager getInstance(){
        if(commandManager == null) return new CommandManager();
        else return commandManager;
    }
    public void clearCommands(){
        commands.clear();
        undoCommand.clear();
    }
    public void addCommand(ICommand command){
        command.execute();
        commands.addLast(command);
        if(commands.size() > 21) commands.pollFirst();
        undoCommand.clear();
    }

    public void undoCommand(){
        if(commands.isEmpty()) return;
        ICommand command = commands.getLast();
        command.undoExecute();
        undoCommand.push(commands.pollLast());
    }

    public  void redoCommand(){
        if(undoCommand.isEmpty()) return;
        ICommand command = undoCommand.peek();
        command.execute();
        commands.addLast(undoCommand.pop());
    }
}
