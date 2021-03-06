package org.nem.ncc.web.servlet;

import org.nem.ncc.NccConfiguration;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class NccDefaultServlet extends HttpServlet {
	private static final long serialVersionUID = -1L;

	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		final String home = NccConfiguration.getHomeUrl();
		resp.sendRedirect(home);
		resp.flushBuffer();
	}
}