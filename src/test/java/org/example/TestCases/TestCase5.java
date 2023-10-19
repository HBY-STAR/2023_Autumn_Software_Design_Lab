package org.example.TestCases;

import org.example.command.Command;
import org.example.command.ConcreteCommand.EditCommand.*;
import org.example.command.ConcreteCommand.FileCommand.LoadCommand;
import org.example.command.ConcreteCommand.FileCommand.SaveCommand;
import org.example.command.ConcreteCommand.LogCommand.HistoryCommand;
import org.example.command.ConcreteCommand.ShowCommand.ListTreeCommand;
import org.example.invoker.Invoker;
import org.example.receiver.Document;
import org.example.receiver.HistoryLog;
import org.example.receiver.StatsLog;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Test
public class TestCase5 {
    // Testcase5 混合所有功能
    //    load test5.md
    //    append-head # 旅⾏清单
    //    append-tail ## 欧洲
    //    insert 2 ## 亚洲
    //    insert 3 1. 中国
    //    insert 4 2. ⽇本
    //    save
    //    undo
    //    list-tree
    //    delete 亚洲
    //    list-tree
    //    history 2
    //    undo
    //    list-tree
    //    redo
    //    list-tree
    //    redo
    //    list-tree
    //    save
    @Test
    public void test_case5() throws IOException {
        Command command;
        Invoker invoker = new Invoker();
        Document document = new Document();
        HistoryLog historyLog = new HistoryLog();
        StatsLog statsLog = new StatsLog();
        List<String> cmd_args = new ArrayList<>();

        cmd_args.add("test5.md");
        command = new LoadCommand(cmd_args, document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("load", cmd_args);
        }


        cmd_args=new ArrayList<>();
        cmd_args.add("#");
        cmd_args.add("旅行清单");
        command = new AppendHeadCommand(cmd_args, document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("append-head", cmd_args);
        }


        cmd_args=new ArrayList<>();
        cmd_args.add("##");
        cmd_args.add("欧洲");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("append-tail", cmd_args);
        }


        cmd_args=new ArrayList<>();
        cmd_args.add("2");
        cmd_args.add("##");
        cmd_args.add("亚洲");
        command = new InsertCommand(cmd_args, document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("insert", cmd_args);
        }

        cmd_args=new ArrayList<>();
        cmd_args.add("3");
        cmd_args.add("1.");
        cmd_args.add("中国");
        command = new InsertCommand(cmd_args, document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("insert", cmd_args);
        }


        cmd_args=new ArrayList<>();
        cmd_args.add("4");
        cmd_args.add("2.");
        cmd_args.add("日本");
        command = new InsertCommand(cmd_args, document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("insert", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new SaveCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("save", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new UndoCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("undo", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new ListTreeCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("list-tree", cmd_args);
        }

        cmd_args=new ArrayList<>();
        cmd_args.add("亚洲");
        command = new DeleteCommand(cmd_args, document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("delete", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new ListTreeCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("list-tree", cmd_args);
        }

        cmd_args=new ArrayList<>();
        cmd_args.add("2");
        command = new HistoryCommand(cmd_args, historyLog);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("history", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new UndoCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("undo", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new ListTreeCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("list-tree", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new RedoCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("redo", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new ListTreeCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("list-tree", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new RedoCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("redo", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new ListTreeCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("list-tree", cmd_args);
        }

        cmd_args=new ArrayList<>();
        command = new SaveCommand(document);
        invoker.setCommand(command);
        if(invoker.execCommand()){
            historyLog.cmd_log("save", cmd_args);
        }
    }
}
