package com.wyc.tips.design.pattern.command;/**
 * Created by wyc on 2016/12/27.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyongcan
 * @Date 2016/12/27 20:07
 */
public class FBSettingWindow {
    private String title;
    private List<FunctionButton> functionButtons = new ArrayList<FunctionButton>();

    public FBSettingWindow(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void addFunctionButton(FunctionButton fb) {
        functionButtons.add(fb);
    }

    public void removeFunctionButton(FunctionButton fb) {
        functionButtons.remove(fb);
    }

    //显示窗口及功能键
    public void display() {
        System.out.println("显示窗口：" + this.title);
        System.out.println("显示功能键：");
        for (Object obj : functionButtons) {
            System.out.println(((FunctionButton)obj).getName());
        }
        System.out.println("------------------------------");
    }

}
