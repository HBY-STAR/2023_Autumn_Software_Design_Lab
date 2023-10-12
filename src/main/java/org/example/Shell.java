package org.example;

import org.example.command.Command;
import org.example.command.ConcreteCommand.EditCommand.DeleteCommand;
import org.example.command.ConcreteCommand.EditCommand.InsertCommand;
import org.example.command.ConcreteCommand.EditCommand.NothingCommand;
import org.example.command.ConcreteCommand.ShowCommand.HelpCommand;
import org.example.command.ConcreteCommand.FileCommand.LoadCommand;
import org.example.command.ConcreteCommand.FileCommand.SaveCommand;
import org.example.command.ConcreteCommand.ShowCommand.ListCommand;
import org.example.invoker.Invoker;
import org.example.receiver.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shell {
    public static void main(String[] args) {
        System.out.println("Hello! Input help to get some information");
        System.out.print(">>>");

        //初始化命令，调用者，接收者
        Document document = new Document();
        Invoker invoker = new Invoker();
        Command command = null;

        Scanner scanner = new Scanner(System.in);

        do{
            //命令及参数
            String cmd;
            List<String> cmd_args =new ArrayList<>();

            //获取命名和参数并执行对应的操作
            Scanner cmd_scanner =new Scanner(scanner.nextLine());
            if(cmd_scanner.hasNext()){
                cmd = cmd_scanner.next();
                while (cmd_scanner.hasNext()){
                    cmd_args.add(cmd_scanner.next());
                }
                switch (cmd) {
                    case "quit" -> {
                        System.out.println("Bye~");
                        System.exit(0);
                    }
                    case "load" -> {
                        if(cmd_args.size()>0){
                            command = new LoadCommand(cmd_args,document);
                        }else {
                            command = new NothingCommand();
                            System.out.println(cmd+"参数不能为空");
                        }
                    }
                    case "save" -> {
                        command = new SaveCommand(document);
                    }
                    case "insert" -> {
                        if(cmd_args.size()>0){
                            command = new InsertCommand(cmd_args,document);
                        }else {
                            command = new NothingCommand();
                            System.out.println(cmd+"参数不能为空");
                        }
                    }
                    case "delete" -> {
                        if(cmd_args.size()>0){
                            command = new DeleteCommand(cmd_args,document);
                        }else {
                            command = new NothingCommand();
                            System.out.println(cmd+"参数不能为空");
                        }
                    }
                    case "list" -> {
                        command = new ListCommand(document);
                    }
                    default -> {
                        command = new HelpCommand();
                    }
                }
                invoker.setCommand(command);
                invoker.execCommand();
                System.out.print(">>>");
            } else {
              System.out.println("输入为空!");
              System.out.print(">>>");
            }
        }
        while (scanner.hasNextLine());
    }
}