package com.kh.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	private static int count;
	
    public SessionListener() {
    }
    
    @Override
    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("������ ������! => " + (++count));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("������ �Ҹ��! => " + (--count));
    }
    
    @Override
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	System.out.println("attributeAdded() : " + se.getName() + ", " + se.getValue() + " �߰���!!" );
    }
    
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	System.out.println("attributeRemoved() : " + se.getName() + ", " + se.getValue() + " ������!!" );
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	System.out.println("attributeReplaced() : " + se.getName() + ", " + se.getValue() + " ������!!" );
    }
}
