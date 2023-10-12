package org.example.command.ConcreteCommand.FileCommand;

import org.example.command.Command;
import org.example.receiver.Document;
import java.util.List;

public class LoadCommand implements Command {
    private String file_path;
    private Document document;
    public LoadCommand(List<String> cmd_args,Document document){
        if(cmd_args.size()>1){
            System.out.println("仅支持打开单个文件");
        } else{
            this.file_path=cmd_args.get(0);
            this.document=document;
        }

    }
    @Override
    public void execute(){
        document.load(file_path);
    }
}
