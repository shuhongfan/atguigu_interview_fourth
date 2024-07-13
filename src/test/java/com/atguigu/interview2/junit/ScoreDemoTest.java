package com.atguigu.interview2.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther zzyy
 * @create 2024-05-03 22:53
 */
class ScoreDemoTest
{
    @Test
    void scoreLevel()
    {
        ScoreDemo scoreDemo = new ScoreDemo();
        assertEquals("弱",scoreDemo.scoreLevel(52));
    }

    @Test
    void scoreLevelv2()
    {
        ScoreDemo scoreDemo = new ScoreDemo();
        assertEquals("差",scoreDemo.scoreLevel(62));
    }

    @Test
    void scoreLevelv3()
    {
        ScoreDemo scoreDemo = new ScoreDemo();
        assertEquals("中",scoreDemo.scoreLevel(80));
    }

    @Test
    void scoreLevelv4()
    {
        ScoreDemo scoreDemo = new ScoreDemo();
        assertThrows(IllegalArgumentException.class,() -> scoreDemo.scoreLevel(-7));
    }
}