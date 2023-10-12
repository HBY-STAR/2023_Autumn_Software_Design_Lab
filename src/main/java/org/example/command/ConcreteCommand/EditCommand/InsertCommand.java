package org.example.command.ConcreteCommand.EditCommand;

import org.example.command.Command;
import org.example.receiver.Document;

import java.util.ArrayList;
import java.util.List;

public class InsertCommand implements Command {
    private int row;
    private List<String> text =new ArrayList<>();
    private Document document;

    public InsertCommand(List<String> cmd_args, Document document) {
        this.document=document;
        String str_row = cmd_args.get(0);
        try{
            row = Integer.parseInt(str_row);
            if(row>0){
                for(int i=1;i<cmd_args.size();i++){
                    text.add(cmd_args.get(i));
                }
            }
        }catch(NumberFormatException e)
        {
            row = Document.doc_lines.size()+1;
            text = cmd_args;
        }
    }
        @Override
        public void execute(){
        if(text!=null) {
            document.insert(row, text);
        }
    }
}
