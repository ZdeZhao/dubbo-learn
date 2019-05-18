package org.apache.dubbo.samples.annotation;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.util.ErrorHandler;
import org.springframework.util.SocketUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by ZDZ on 2019/5/18.
 */
public class EmbeddedZooKeeper implements SmartLifecycle {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedZooKeeper.class);

    /**
     *Zookeeper clinet port. This will be determined dynamically upon startup,
     */
    private final int clientPort;

    /**
     *whether to auto-start. Default is true
     */
    private boolean autoStartup = true;

    /**
     * Lifecycle phase. Default is 0
     */
    private int phase = 0;

    /**
     * Thread for running the Zookeeper server
     */
    private volatile Thread zkServerThread;

    /**
     * Zookeeper server.
     */
    private volatile ZooKeeperServerMain zkServer;

    /**
     * {@link ErrorHandler} to be invoked if an Exception is thrown from zookeeper server thread;
     */
    private ErrorHandler errorHandler;

    private boolean daemon = true;

    /**
     * Construct an EmbeddedZookeeper with a random port.
     */
    public EmbeddedZooKeeper(){
        clientPort = SocketUtils.findAvailableTcpPort();
    }

    /**
     * Construct an EmbeddedZookeeper with the provided prot.
     * @param clientPort port for ZooKeeper server to bind to
     * @param daemon
     */
    public EmbeddedZooKeeper(int clientPort, boolean daemon){
        this.clientPort = clientPort;
        this.daemon = daemon;
    }

    /**
     * Returns the port that clients should use to connect to this embedded server.
     * @return
     */
    public int getClientPort(){
        return this.clientPort;
    }

    /**
     * specify whether to start automatically. Default is true.
     * @param autoStartup whether to start automatically
     */
    public void setAutoStartup(boolean autoStartup){
        this.autoStartup = autoStartup;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isAutoStartup() {
        return this.autoStartup;
    }

    public void setPhase(int phase){
        this.phase = phase;
    }

    @Override
    public void stop(Runnable runnable) {
        stop();
        runnable.run();
    }

    @Override
    public synchronized void start() {
        if (zkServerThread == null){
            zkServerThread = new Thread(new ServerRunnable(), "ZooKeeper Server Starter");
            zkServerThread.setDaemon(daemon);
            zkServerThread.start();
        }
    }

    @Override
    public synchronized void stop() {
        if (zkServerThread != null){
            try{
                Method shutdown = ZooKeeperServerMain.class.getDeclaredMethod("shutdown");
                shutdown.setAccessible(true);
                shutdown.invoke(zkServer);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            try{
                zkServerThread.join(5000);
                zkServerThread = null;
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                logger.warn("Interrupted while waiting for embedded ZooKeeper to exit");
                zkServerThread = null;
            }
        }
    }

    @Override
    public boolean isRunning() {
        return (zkServerThread != null);
    }

    @Override
    public int getPhase() {
        return this.phase;
    }

    public void setErrorHandler(ErrorHandler errorHandler){
        this.errorHandler = errorHandler;
    }

    private class ServerRunnable implements Runnable{

        @Override
        public void run() {
            try{
                Properties properties = new Properties();
                File file = new File(System.getProperty("java.io.tmpdir")
                    + File.separator + UUID.randomUUID());
                file.deleteOnExit();
                properties.setProperty("dataDir", file.getAbsolutePath());
                properties.setProperty("clientPort", String.valueOf(clientPort));

                QuorumPeerConfig quorumPeerConfig = new QuorumPeerConfig();
                quorumPeerConfig.parseProperties(properties);

                zkServer = new ZooKeeperServerMain();
                ServerConfig configuration = new ServerConfig();
                configuration.readFrom(quorumPeerConfig);
                zkServer.runFromConfig(configuration);
            }catch (Exception e){
                if (errorHandler != null){
                    errorHandler.handleError(e);
                }else{
                    logger.error("Exception running embedded ZooKeeper", e);
                }
            }
        }
    }
}
