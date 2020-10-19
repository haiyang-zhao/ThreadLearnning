package ch01.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 类说明：连接池的实现
 */
public class DBPool {

    private final static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    // 在mills内无法获取到连接，将会返回null
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills == 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                Connection connection = null;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    //如果中途被唤醒，重新计算超时时间
                    remaining = future - System.currentTimeMillis();

                }
                if (!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }
}
