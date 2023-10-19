package org.example.TestCases;

import org.example.command.Command;
import org.example.command.ConcreteCommand.EditCommand.*;
import org.example.command.ConcreteCommand.FileCommand.LoadCommand;
import org.example.command.ConcreteCommand.FileCommand.SaveCommand;
import org.example.command.ConcreteCommand.ShowCommand.ListTreeCommand;
import org.example.invoker.Invoker;
import org.example.receiver.Document;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test
public class TestCase2 {
    // Testcase2 撤销与重做操作
    //    load test2.md
    //    append-head # 旅⾏清单
    //    append-tail ## 亚洲
    //    append-tail 1. 中国
    //    append-tail 2. ⽇本
    //    delete 亚洲
    //    undo
    //    redo
    //    list-tree
    //    save
    @Test
    public void test_case2() {
        Command command;
        Invoker invoker = new Invoker();
        Document document = new Document();
        List<String> cmd_args = new ArrayList<>();

        cmd_args.add("test2.md");
        command = new LoadCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("#");
        cmd_args.add("旅行清单");
        command = new AppendHeadCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("##");
        cmd_args.add("亚洲");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("1.");
        cmd_args.add("中国");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("2.");
        cmd_args.add("日本");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("亚洲");
        command = new DeleteCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        command = new UndoCommand(document);
        invoker.setCommand(command);
        invoker.execCommand();

        command = new RedoCommand(document);
        invoker.setCommand(command);
        invoker.execCommand();

        command = new ListTreeCommand(document);
        invoker.setCommand(command);
        invoker.execCommand();

        command = new SaveCommand(document);
        invoker.setCommand(command);
        invoker.execCommand();
    }
}
