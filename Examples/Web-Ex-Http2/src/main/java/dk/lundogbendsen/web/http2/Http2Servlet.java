package dk.lundogbendsen.web.http2;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.PushBuilder;

@WebServlet(value = { "/http2" })
public class Http2Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PushBuilder pushBuilder = req.newPushBuilder();
		//push content to client. See browsers debug panel.
		if (pushBuilder!=null) {
			pushBuilder
			.path("images/game.png")
			.addHeader("content-type", "image/png")
			.push();			
		}
		
		try (PrintWriter respWriter = resp.getWriter();) {
			respWriter.write("<html><body><p>This image has been pushed: </p><img src='images/game.png'></body></html>");
		}
	}
}