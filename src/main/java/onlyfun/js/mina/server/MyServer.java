package onlyfun.js.mina.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class MyServer {

    private static final Logger logger = LoggerFactory.getLogger(MyServer.class);

    public static void main(String[] args) {
        /**
         * 客户端对应着IoConnector
         * 服务端对应着IoAcceptor
         */
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        acceptor.setHandler(new IoHandlerAdapter() {
            @Override
            public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
                logger.error(cause.getMessage(), cause);
                session.close(true);
            }

            @Override
            public void messageReceived(IoSession session, Object message) throws Exception {
                logger.info("Received message " + message);
                session.write(message);
            }

            @Override
            public void messageSent(IoSession session, Object message) throws Exception {
                logger.info("Sent message " + message);
            }
        });

        try {
            acceptor.bind(new InetSocketAddress(10000));
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}  