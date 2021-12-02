package com.kh.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		String remoteAddr = sre.getServletRequest().getRemoteAddr();

		System.out.println(remoteAddr + "∑Œ ∫Œ≈Õ ø‰√ª¿Ã ¿¸º€µ !!");
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		String remoteAddr = sre.getServletRequest().getRemoteAddr();

		System.out.println(remoteAddr + "∑Œ ∫Œ≈Õ ø‰√ª¿Ã º“∏Íµ !!");
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("attributeAdded() : " + srae.getName() + ", " + srae.getValue() + " √ﬂ∞°µ !!");
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("attributeRemoved() : " + srae.getName() + ", " + srae.getValue() + " ªË¡¶µ !!");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("attributeReplaced() : " + srae.getName() + ", " + srae.getValue() + " ºˆ¡§µ !!");
	}

}
