package com.syl.netnovel.instances;

import com.syl.netnovel.myInterface.Synopsis;

/**
 * Created by j3767 on 2016/9/5.
 *
 * @Describe
 * @Called
 */
public class StoryB implements Synopsis {
    private String name;

    public StoryB(String name) {
        this.name = name;
    }

    @Override
    public void badStart() {
        System.out.println(name+"全村被屠,投入青云大竹峰");
    }

    @Override
    public void adventure() {
        System.out.println(name+"在追逐小狗的路上捡到烧火棍");
    }

    @Override
    public void winABattle() {
        System.out.println(name+"在七脉会武中侥幸进了前四,与陆雪琪进入万蝠古窟历练");
    }

    @Override
    public void growfast() {
        System.out.println(name+"在魔教十年,深得鬼王的其中和真传 ");
    }

    @Override
    public void manyFights() {
        System.out.println(name+"经历无数战斗,先是为鬼王卖命,后又与鬼王大战");
    }

    @Override
    public void succeed() {
        System.out.println("天地不仁,以万物为刍狗"+name+"成为最有资格拥有天书的人");
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
