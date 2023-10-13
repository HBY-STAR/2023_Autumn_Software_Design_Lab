package org.example;

import org.example.command.Command;
import org.example.command.ConcreteCommand.EditCommand.*;
import org.example.command.ConcreteCommand.LogCommand.HistoryCommand;
import org.example.command.ConcreteCommand.ShowCommand.HelpCommand;
import org.example.command.ConcreteCommand.FileCommand.LoadCommand;
import org.example.command.ConcreteCommand.FileCommand.SaveCommand;
import org.example.command.ConcreteCommand.ShowCommand.ListCommand;
import org.example.command.ConcreteCommand.StatsCommand.StatsCommand;
import org.example.invoker.Invoker;
import org.example.receiver.Document;
import org.example.receiver.HistoryLog;
import org.example.receiver.StatsLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shell {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello! Input help to get some information");
        System.out.print(">>>");

        //初始化命令，调用者，接收者
        Command command = null;
        Invoker invoker = new Invoker();
        Document document = new Document();
        //日志及统计模块
        HistoryLog historyLog = new HistoryLog();
        StatsLog statsLog = new StatsLog();

        //start log
        historyLog.ses_start_log();
        statsLog.ses_start_log();


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

                if(cmd.equals("quit")){
                    //quit log
                    if(StatsLog.current_file!=null){
                        statsLog.file_end_log();
                    }
                    statsLog.ses_end_log();
                    System.out.println("Bye~");
                    System.exit(0);
                }
                command = getCommand(cmd, cmd_args,document,historyLog,statsLog);
                invoker.setCommand(command);
                boolean opt_success = invoker.execCommand();

                if(opt_success){
                    //cmd log
                    historyLog.cmd_log(cmd,cmd_args);
                    if(cmd.equals("load")){
                        if(StatsLog.current_file!=null){
                            statsLog.file_end_log();
                        }
                        statsLog.file_start_log(Document.file_path);
                    }
                }

                System.out.print(">>>");
            } else {
              System.out.println("输入为空!");
              System.out.print(">>>");
            }
        }
        while (scanner.hasNextLine());
    }


    private static Command getCommand(String cmd, List<String> cmd_args,Document document,HistoryLog historyLog,StatsLog statsLog) {
        Command command;
        switch (cmd) {
            case "load" -> {
                if(cmd_args.size()>0){
                    command = new LoadCommand(cmd_args, document);
                }else {
                    command = new NothingCommand();
                    System.out.println(cmd +"参数不能为空");
                }
            }
            case "save" -> command = new SaveCommand(document);
            case "insert" -> {
                if(cmd_args.size()>0){
                    command = new InsertCommand(cmd_args, document);
                }else {
                    command = new NothingCommand();
                    System.out.println(cmd +"参数不能为空");
                }
            }
            case "append-head" -> {
                if(cmd_args.size()>0){
                    command = new AppendHeadCommand(cmd_args, document);
                }else {
                    command = new NothingCommand();
                    System.out.println(cmd +"参数不能为空");
                }
            }
            case "append-tail" -> {
                if(cmd_args.size()>0){
                    command = new AppendTailCommand(cmd_args, document);
                }else {
                    command = new NothingCommand();
                    System.out.println(cmd +"参数不能为空");
                }
            }
            case "delete" -> {
                if(cmd_args.size()>0){
                    command = new DeleteCommand(cmd_args, document);
                }else {
                    command = new NothingCommand();
                    System.out.println(cmd +"参数不能为空");
                }
            }
            case "list" -> command = new ListCommand(document);
            case "history" -> {
                command = new HistoryCommand(cmd_args,historyLog);
            }
            case "stats" -> {
                command = new StatsCommand(cmd_args,statsLog);
            }
            default -> {
                System.out.println(cmd +" 不是有效命令");
                command = new HelpCommand();
            }
        }
        return command;
    }
}