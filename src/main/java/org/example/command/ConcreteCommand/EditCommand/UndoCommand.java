package org.example.command.ConcreteCommand.EditCommand;

import org.example.command.Command;
import org.example.receiver.Document;

//TODO
public class UndoCommand implements Command{
    private final Document document;

    public UndoCommand(Document document){ this.document=document; }
    @Override
    public boolean execute() {
        return document.undo();
    }
}
