package org.littleshoot.proxy.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * A helper class that logs messages for ProxyConnections. All it does is make
 * sure that the Channel and current state are always included in the log
 * messages (if available).
 * </p>
 * 
 * <p>
 * Note that this depends on us using a LocationAwareLogger so that we can
 * report the line numbers of the caller rather than this helper class.
 * </p>
 */
class ProxyConnectionLogger {
    private final ProxyConnection connection;
    private final Logger logger;
    private final String fqcn = this.getClass().getCanonicalName();

    public ProxyConnectionLogger(ProxyConnection connection) {
        this.connection = connection;
        this.logger = LoggerFactory.getLogger(connection
                .getClass());
    }

    protected void error(String message, Object... params) {
        if (logger.isErrorEnabled()) {
            logger.error(message, params);
        }
    }

    protected void error(String message, Throwable t) {
        if (logger.isErrorEnabled()) {
            logger.error(message, t);
        }
    }

    protected void warn(String message, Object... params) {
        if (logger.isWarnEnabled()) {
            logger.warn(message, params);
        }
    }

    protected void warn(String message, Throwable t) {
        if (logger.isWarnEnabled()) {
            logger.warn(message, t);
        }
    }

    protected void info(String message, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.info(message, params);
        }
    }

    protected void info(String message, Throwable t) {
        if (logger.isInfoEnabled()) {
            logger.info(message, t);
        }
    }

    protected void debug(String message, Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(message, params);
        }
    }

    protected void debug(String message, Throwable t) {
        if (logger.isDebugEnabled()) {
            logger.debug(message, t);
        }
    }

    protected void log(int level, String message, Object... params) {
        if (logger.isTraceEnabled()) {
            logger.trace(message, params);
        }
    }
    
    protected void log(int level, String message, Throwable t) {
        if (logger.isTraceEnabled()) {
            logger.trace(message, t);
        }
    }
    
    private String fullMessage(String message) {
        String stateMessage = connection.getCurrentState().toString();
        if (connection.isTunneling()) {
            stateMessage += " {tunneling}";
        }
        String messagePrefix = "(" + stateMessage + ")";
        if (connection.channel != null) {
            messagePrefix = messagePrefix + " " + connection.channel;
        }
        return messagePrefix + ": " + message;
    }
}