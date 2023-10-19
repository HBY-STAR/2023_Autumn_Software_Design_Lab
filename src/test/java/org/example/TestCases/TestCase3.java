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
public class TestCase3 {
    // Testcase3 混合编辑操作的撤销与重做
    //    load test3.md
    //    append-head # 书籍推荐
    //    append-tail * 《深入理解计算机系统》
    //    undo
    //    append-tail ## 编程
    //    append-tail * 《设计模式的艺术》
    //    redo
    //    list-tree
    //    append-tail * 《云原⽣：运⽤容器、函数计算和数据构建下⼀代应⽤》
    //    append-tail * 《深入理解Java虚拟机》
    //    undo
    //    redo
    //    list-tree
    //    save
    @Test
    public void test_case3() {
        Command command;
        Invoker invoker = new Invoker();
        Document document = new Document();
        List<String> cmd_args = new ArrayList<>();

        cmd_args.add("test3.md");
        command = new LoadCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("#");
        cmd_args.add("书籍推荐");
        command = new AppendHeadCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("*");
        cmd_args.add("《深入理解计算机系统》");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        command = new UndoCommand(document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("##");
        cmd_args.add("编程");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("*");
        cmd_args.add("《设计模式的艺术》");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        command = new RedoCommand(document);
        invoker.setCommand(command);
        invoker.execCommand();

        command = new ListTreeCommand(document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("*");
        cmd_args.add("《云原生：运用容器、函数和数据构建下一代应用》");
        command = new AppendTailCommand(cmd_args, document);
        invoker.setCommand(command);
        invoker.execCommand();

        cmd_args=new ArrayList<>();
        cmd_args.add("*");
        cmd_args.add("《深入理解Java虚拟机》");
        command = new AppendTailCommand(cmd_args, document);
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
