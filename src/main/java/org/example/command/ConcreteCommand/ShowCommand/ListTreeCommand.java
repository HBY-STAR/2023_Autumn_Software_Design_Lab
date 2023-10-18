package org.example.command.ConcreteCommand.ShowCommand;

import org.example.command.Command;
import org.example.receiver.Document;

public class ListTreeCommand implements Command {
    private final Document document;
    public ListTreeCommand(Document document){
        this.document=document;
    }
    @Override
    public boolean execute(){
        return document.list_tree(0,0,-1);
    }
}
