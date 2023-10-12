package org.example.command.ConcreteCommand.EditCommand;

import org.example.command.Command;
import org.example.receiver.Document;

import java.util.List;

public class AppendTailCommand implements Command {
    private int row;
    private String text;
    private Document document;

    public AppendTailCommand(List<String> cmd_args, Document document){
        this.document=document;
        row = Document.doc_lines.size()+1;
        StringBuilder text_builder=new StringBuilder();
        for (String cmdArg : cmd_args) {
            text_builder.append(cmdArg).append(" ");
        }
        text = text_builder.toString();
    }
    @Override
    public void execute() {
        document.insert(row, text);
    }
}
