package org.example.command.ConcreteCommand.ShowCommand;

import org.example.command.Command;
import org.example.receiver.Document;

public class ListCommand implements Command {
    private Document document;

    public ListCommand(Document document){
        this.document=document;
    }
    @Override
    public void execute(){
        document.list();
    }
}
