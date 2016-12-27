package com.wyc.tips.design.pattern.command;/**
 * Created by wyc on 2016/12/27.
 */

/**
 * @author wangyongcan
 * @Date 2016/12/27 18:41
 */
public class MinimizeCommand implements Command {
    private WindowHandler windowHandler;
    public MinimizeCommand(WindowHandler windowHandler) {
        this.windowHandler = windowHandler;
    }

    @Override
    public void execute() {
        windowHandler.minimize();
    }
}
