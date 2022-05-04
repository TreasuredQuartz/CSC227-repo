package Servlet;

import java.io.PrintWriter;

import javax.jms.annotation.Resource;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import javax.servlet.annotation.WebSevlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SallySpeaksServletClient")
public class SallySpeaksServletClient extends HttpServlet {
    private static final int MSG_CNT = 5;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/queue/SallySpeaksQueue")
    private Queue queue;

    @Resource(mappedName = "java:/topic/SallySpeaksTopic")
    private Topic topic;

    @Override
    protected void doGet() {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection connection = null;
        out.write("<h1>SallySpeaks: Demonstrates the adaptation of a java lab into a cloud application.</h1>");
        try {
            Destination destination;
            if (req.getParameterMap().keySet().contains("topic")) {
                destination = topic;
            } else {
                destination = queue;
            }
            out.write("<p>Sending messages to <em>" + destination + "</em></p>");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOLEDGE);
            MessageProducer messageProducer = session.createProducer(destination);
            connection.start();
            out.write("<h2>Following messages will be sent to the destination:</h2>");
            TextMessage message = session.createTextMessage();
            for (int i = 0; i < MSG_CNT; ++i) {
                message.setText("This is message");
            }
        } catch (JMSException e) {
            e.printStackTrace();
            out.write("<h2>A Problem occurred during the delivery of this message</h2>");
            out.write("</br>");
            out.write("<p><i>Go to your JBoss EAP server console or log to see the error stack trace.");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                out.close();
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        doGet(req, resp);
    }
}
