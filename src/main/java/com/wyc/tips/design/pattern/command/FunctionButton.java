package com.wyc.tips.design.pattern.command;/**
 * Created by wyc on 2016/12/27.
 */

/**
 * @author wangyongcan
 * @Date 2016/12/27 18:44
 */
public class FunctionButton {
    private String name;
    private Command command;
    public FunctionButton(String name,Command command) {
        this.name = name;
        this.command = command;
    }

    public FunctionButton(String name) {
        this.name = name;
    }

    public void onclick() {
        System.out.println("点击功能键：" + name);
        command.execute();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
