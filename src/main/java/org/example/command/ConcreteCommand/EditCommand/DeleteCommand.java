package org.example.command.ConcreteCommand.EditCommand;

import org.example.command.Command;
import org.example.receiver.Document;

import java.util.List;
import java.util.Scanner;

public class DeleteCommand implements Command {
    private Document document;
    private int row;
    private String text;

    boolean use_row;

    public DeleteCommand(List<String> cmd_args, Document document){
        this.document=document;
        StringBuilder text_builder=new StringBuilder();
        String str_row = cmd_args.get(0);
        try{
            row = Integer.parseInt(str_row);
            use_row = true;
        }catch(NumberFormatException e)
        {
            for (String cmdArg : cmd_args) {
                text_builder.append(cmdArg).append(" ");
            }
            text = text_builder.toString();
            use_row =false;
        }
    }
    @Override
    public void execute(){
        if(use_row){
            document.delete_row(row);
        }else{
            document.delete_text(text);
        }
    }
}
