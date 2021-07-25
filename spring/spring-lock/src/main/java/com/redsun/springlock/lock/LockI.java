package com.redsun.springlock.lock;

/**
 * lock interface
 *
 * @author qiquan
 * @date 2021/07/24 16:29
 */
public interface LockI {

    /**
     * lock
     *
     * @param key
     * @return
     */
    boolean lock(String key);

    /**
     * unlock
     *
     * @param key
     */
    void unlock(String key);

}
