package cn.likegirl.java.io.mina.demo.echoserver.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;

import javax.net.SocketFactory;

/**
 * Simple Socket factory to create sockets with or without SSL enabled.
 * If SSL enabled a "bougus" SSL Context is used (suitable for test purposes)
 *
 * @author waylau.com
 * @date 2015-4-6
 */
public class SslSocketFactory extends SocketFactory {
    private static boolean sslEnabled = false;

    private static javax.net.ssl.SSLSocketFactory sslFactory = null;

    private static SocketFactory factory = null;

    public SslSocketFactory() {
        super();
    }

    @Override
    public Socket createSocket(String arg1, int arg2) throws IOException,
            UnknownHostException {
        if (isSslEnabled()) {
            return getSSLFactory().createSocket(arg1, arg2);
        } else {
            return new Socket(arg1, arg2);
        }
    }

    @Override
    public Socket createSocket(String arg1, int arg2, InetAddress arg3, int arg4)
            throws IOException, UnknownHostException {
        if (isSslEnabled()) {
            return getSSLFactory().createSocket(arg1, arg2, arg3, arg4);
        } else {
            return new Socket(arg1, arg2, arg3, arg4);
        }
    }

    @Override
    public Socket createSocket(InetAddress arg1, int arg2) throws IOException {
        if (isSslEnabled()) {
            return getSSLFactory().createSocket(arg1, arg2);
        } else {
            return new Socket(arg1, arg2);
        }
    }

    @Override
    public Socket createSocket(InetAddress arg1, int arg2, InetAddress arg3,
            int arg4) throws IOException {
        if (isSslEnabled()) {
            return getSSLFactory().createSocket(arg1, arg2, arg3, arg4);
        } else {
            return new Socket(arg1, arg2, arg3, arg4);
        }
    }

    public static SocketFactory getSocketFactory() {
        if (factory == null) {
            factory = new SslSocketFactory();
        }
        return factory;
    }

    private javax.net.ssl.SSLSocketFactory getSSLFactory() {
        if (sslFactory == null) {
            try {
                sslFactory = BogusSslContextFactory.getInstance(false)
                        .getSocketFactory();
            } catch (GeneralSecurityException e) {
                throw new RuntimeException("could not create SSL socket", e);
            }
        }
        return sslFactory;
    }

    public static boolean isSslEnabled() {
        return sslEnabled;
    }

    public static void setSslEnabled(boolean newSslEnabled) {
        sslEnabled = newSslEnabled;
    }

}
