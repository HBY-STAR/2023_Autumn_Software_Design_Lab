package org.example.command.ConcreteCommand.EditCommand;

import org.example.command.Command;
import org.example.receiver.Document;

import java.util.ArrayList;
import java.util.List;

public class AppendHeadCommand implements Command {
    private List<String> text =new ArrayList<>();
    private Document document;

    public AppendHeadCommand(List<String> cmd_args, Document document){
        this.document=document;
        text = cmd_args;
    }
    @Override
    public boolean execute(){
            return document.insert(1, text);
        }
}
