package org.example.TestCases;

import org.example.command.Command;
import org.example.command.ConcreteCommand.EditCommand.AppendHeadCommand;
import org.example.command.ConcreteCommand.EditCommand.AppendTailCommand;
import org.example.command.ConcreteCommand.EditCommand.InsertCommand;
import org.example.command.ConcreteCommand.FileCommand.LoadCommand;
import org.example.command.ConcreteCommand.FileCommand.SaveCommand;
import org.example.command.ConcreteCommand.ShowCommand.ListTreeCommand;
import org.example.invoker.Invoker;
import org.example.receiver.Document;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
@Test
public class TestCase4 {
    // Testcase4 切换⼯作区
    //    load test4.md
    //    append-head # 旅⾏清单
    //    append-tail ## 亚洲
    //    save
    //    append-tail 1. 中国
    //    append-tail 2. ⽇本
    //    append-tail ## 欧洲
    //    load test3.md
    //    list-tree
    //    load test4.md
    //    list-tree
    @Test
    public void test_case4() {
        Command command;
        Invoker invoker = new Invoker();
        Document document = new Document();
        List<String> cmd_args = new ArrayList<>();

        cmd_args.add("test4.md");
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

        command = new SaveCommand(document);
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
        cmd_args.add("##");
        cmd_args.add("欧洲");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("test3.md");
        command = new LoadCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        command = new ListTreeCommand(document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("test4.md");
        command = new LoadCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        command = new ListTreeCommand(document);
        invoker.setCommand(command);
        invoker.execCommand();
    }
}
