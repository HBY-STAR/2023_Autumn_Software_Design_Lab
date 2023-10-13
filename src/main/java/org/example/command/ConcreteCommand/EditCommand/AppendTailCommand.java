package org.example.command.ConcreteCommand.EditCommand;

import org.example.command.Command;
import org.example.receiver.Document;

import java.util.ArrayList;
import java.util.List;

public class AppendTailCommand implements Command {
    private int row;
    private List<String> text =new ArrayList<>();
    private Document document;

    public AppendTailCommand(List<String> cmd_args, Document document){
        this.document=document;
        row = Document.doc_lines.size()+1;
        text = cmd_args;
    }
    @Override
    public boolean execute() {
        return document.insert(row, text);
    }
}
