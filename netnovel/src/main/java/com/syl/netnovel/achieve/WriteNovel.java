package com.syl.netnovel.achieve;

import com.syl.netnovel.instances.StoryA;
import com.syl.netnovel.instances.StoryB;
import com.syl.netnovel.myInterface.Synopsis;

/**
 * Created by j3767 on 2016/9/5.
 *
 * @Describe
 * @Called
 */
public class WriteNovel {
    private Synopsis mSynopsis;
    private String mainActorName;

    public WriteNovel(String mainActorName) {
        this.mainActorName = mainActorName;
        switch (mainActorName){
            case "张小凡":
                default:
                mSynopsis = new StoryB(mainActorName);
                break;
            case "杜伟":
                mSynopsis = new StoryA(mainActorName);
                break;
        }
    }
    public String getNovelDetail(){
        mSynopsis.getContent();
        return null;
    }
}
