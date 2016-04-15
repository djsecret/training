package com.damon.mockito.impl;

import com.damon.mockito.Query;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by dongjun.wei on 16/3/10.
 */
public class QueryImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testVerify() {
        Query query = mock(Query.class);
        query.getContent("tom");
        //默认情况下，使用times(1)，下面两行是等价的
        verify(query).getContent("tom");
        verify(query, times(1)).getContent("tom");

        //never()代表没有调用过，下面两行是等价的
        verify(query, never()).getContent("alice");
        verify(query, times(0)).getContent("alice");


        query.getContent("tom");
        verify(query, times(2)).getContent("tom");
        verify(query, atLeastOnce()).getContent("tom");
        verify(query, atLeast(2));
    }

    @Test
    public void testStub() {
        Query query = mock(Query.class);
        when(query.getContent("alice")).thenReturn("ok");
        when(query.getContent("bob")).thenThrow(new RuntimeException("error"));

        //return ok
        assertEquals("ok", query.getContent("alice"));

        //throw exception
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("error");
        query.getContent("bob");

        //return null
        assertNull(query.getContent("tom"));
    }

    @Test
    public void testArgumentMatcher() {
        Query query = mock(Query.class);
        when(query.getContent(anyString())).thenReturn("ok");
        when(query.query(anyString(), anyMap())).thenReturn("get");

        assertEquals("ok", query.getContent("balabala"));

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "alice");
        assertEquals("get", query.query("http://www.balabala.com", params));
    }

    @Test
    public void testDoInsteadOfWhen() {
        Query query = mock(Query.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String url = (String) invocation.getArguments()[0];
                Map<String, String> params = (Map<String, String>) invocation.getArguments()[1];

                if ("url1".equals(url) && params.containsKey("name")) {
                    return "Success";
                }

                return null;
            }
        }).when(query).query(anyString(),anyMap());

        doThrow(new RuntimeException("error")).when(query).getContent(anyString());

        HashMap<String, String> params = new HashMap<String,String>();
        params.put("name", "alice");
        assertEquals("Success",query.query("url1", params));
        assertNull(query.query("url", params));

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("error");
        query.getContent("bob");
    }

}