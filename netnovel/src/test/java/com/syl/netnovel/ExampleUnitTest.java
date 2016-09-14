package com.syl.netnovel;

import com.syl.netnovel.achieve.WriteNovel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        WriteNovel novel = new WriteNovel("张小凡");
        novel.getNovelDetail();
    }
}