package org.example.command.ConcreteCommand.EditCommand;

import org.example.command.Command;
import org.example.receiver.Document;

import java.util.List;

public class AppendHeadCommand implements Command {
    private String text;
    private Document document;

    public AppendHeadCommand(List<String> cmd_args, Document document){
        this.document=document;
        StringBuilder text_builder=new StringBuilder();
        for (String cmdArg : cmd_args) {
            text_builder.append(cmdArg).append(" ");
        }
        text = text_builder.toString();
    }
    @Override
    public void execute(){
            document.insert(1, text);
        }
}
