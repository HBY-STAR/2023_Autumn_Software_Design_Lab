package org.example.command.ConcreteCommand.FileCommand;

import org.example.command.Command;
import org.example.receiver.Document;

public class SaveCommand implements Command {
    private Document document;
    public SaveCommand(Document document){
        this.document=document;
    }

    @Override
    public boolean execute(){
        return document.save();
    }
}
