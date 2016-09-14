package com.syl.netnovel.instances;

import com.syl.netnovel.myInterface.Synopsis;

/**
 * Created by j3767 on 2016/9/5.
 *
 * @Describe
 * @Called
 */
public class StoryA implements Synopsis {
    private String name;

    public StoryA(String name) {
        this.name = name;
    }

    @Override
    public void badStart() {
        System.out.println(name + "无故穿越,因为没有魔法能力,成为将军家的废物");
    }

    @Override
    public void adventure() {
        System.out.println(name + "因为意外来到恶魔岛,遇到恶魔的仆人,获得超能力");
    }

    @Override
    public void winABattle() {
        System.out.println(name + "协助陈皇子夺取帝国的权利,成为郁金香公爵,,");
    }

    @Override
    public void growfast() {
        System.out.println(name + "在恶魔仆人,圣骑士...的帮助下,迅速成长..");
    }

    @Override
    public void manyFights() {
        System.out.println(name + "建立魔法学院和魔法学会分庭抗礼");
    }

    @Override
    public void succeed() {
        System.out.println(name + "娶了女皇为妻子,成为罗兰帝国的英雄");
    }

    @Override
    public void getContent() {
        badStart();
        adventure();
        winABattle();
        growfast();
        manyFights();
        succeed();
    }
}
