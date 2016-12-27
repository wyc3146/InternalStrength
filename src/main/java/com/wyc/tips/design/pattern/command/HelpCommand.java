package com.wyc.tips.design.pattern.command;/**
 * Created by wyc on 2016/12/27.
 */

/**
 * @author wangyongcan
 * @Date 2016/12/27 18:43
 */
public class HelpCommand implements Command {
    private HelpHandler helpHandler;

    public HelpCommand(HelpHandler helpHandler) {
        this.helpHandler = helpHandler;
    }

    @Override
    public void execute() {
        helpHandler.display();
    }
}
