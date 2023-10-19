package org.example.command.ConcreteCommand.EditCommand;

import org.example.command.Command;
import org.example.receiver.Document;

public class RedoCommand implements Command {
    private final Document document;

    public RedoCommand(Document document){ this.document=document; }
    @Override
    public boolean execute() {
        return document.redo();
    }
}
