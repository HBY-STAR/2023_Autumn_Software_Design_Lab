package org.example.command.ConcreteCommand.ShowCommand;

import org.example.command.Command;
import org.example.receiver.Document;

import java.util.List;

public class DirTreeCommand implements Command {
    private final Document document;
    private final List<String> text;

    public DirTreeCommand(List<String> cmd_args, Document document){
        this.text=cmd_args;
        this.document=document;
    }
    @Override
    public boolean execute(){
        return document.dir_tree(text);
    }
}
