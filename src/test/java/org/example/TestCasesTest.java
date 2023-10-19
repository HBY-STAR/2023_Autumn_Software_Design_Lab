package org.example;

import org.example.command.Command;
import org.example.command.ConcreteCommand.EditCommand.AppendHeadCommand;
import org.example.command.ConcreteCommand.EditCommand.AppendTailCommand;
import org.example.command.ConcreteCommand.EditCommand.DeleteCommand;
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
public class TestCasesTest {
    public  static Document document = new Document();

//    load test1.md
//    insert ## 程序设计
//    append-head # 我的资源
//    append-tail ### 软件设计
//    append-tail #### 设计模式
//    append-tail 1. 观察者模式
//    append-tail 3. 单例模式
//    insert 6 2. 策略模式
//    delete 单例模式
//    append-tail 3. 组合模式
//    list-tree
//    append-tail ## ⼯具箱
//    append-tail ### Adobe
//    list-tree
//    save
    @Test
    public void test_case1() {
        Command command;
        Invoker invoker = new Invoker();
        Document document = new Document();
        List<String> cmd_args = new ArrayList<>();

        cmd_args.add("test1.md");
        command = new LoadCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("##");
        cmd_args.add("程序设计");
        command = new InsertCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("#");
        cmd_args.add("我的资源");
        command = new AppendHeadCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("###");
        cmd_args.add("软件设计");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("####");
        cmd_args.add("设计模式");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("1.");
        cmd_args.add("观察者模式");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("3.");
        cmd_args.add("单例模式");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("6");
        cmd_args.add("2.");
        cmd_args.add("策略模式");
        command = new InsertCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("单例模式");
        command = new DeleteCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("3.");
        cmd_args.add("组合模式");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        command = new ListTreeCommand(document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("##");
        cmd_args.add("工具箱");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("###");
        cmd_args.add("Adobe");
        command = new AppendTailCommand(cmd_args, document);
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
