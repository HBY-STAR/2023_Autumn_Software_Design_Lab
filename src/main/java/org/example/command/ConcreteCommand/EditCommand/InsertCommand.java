package org.example.command.ConcreteCommand.EditCommand;

import org.example.command.Command;
import org.example.receiver.Document;

import java.util.List;

public class InsertCommand implements Command {
    private int row;
    private String text;
    private Document document;

    public InsertCommand(List<String> cmd_args, Document document) {
        this.document=document;
        StringBuilder text_builder=new StringBuilder();
        String str_row = cmd_args.get(0);
        try{
            row = Integer.parseInt(str_row);
            if(row>0){
                for(int i=1;i<cmd_args.size();i++){
                    text_builder.append(cmd_args.get(i)).append(" ");
                }
                text = text_builder.toString();
            }
        }catch(NumberFormatException e)
        {
            row = Document.doc_lines.size()+1;
            for (String cmdArg : cmd_args) {
                text_builder.append(cmdArg).append(" ");
            }
            text = text_builder.toString();
        }
    }
        @Override
        public void execute(){
        if(text!=null) {
            document.insert(row, text);
        }
    }
}
