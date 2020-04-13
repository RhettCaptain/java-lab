package com.github.rhettcaptain.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class JdkStack {
    @Test
    public void testJdkStack(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        Assert.assertEquals((int)stack.peek(), 2);
        Assert.assertEquals(stack.search(2),1);

    }
}
