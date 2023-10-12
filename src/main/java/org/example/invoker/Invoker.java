package org.example.invoker;

import org.example.command.Command;

public class Invoker {
    private Command cmd;
    public void setCommand(Command cmd){
        this.cmd=cmd;
    }
    public void execCommand() {
        cmd.execute();
    }
}
