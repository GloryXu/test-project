package com.redsun.springlock.lock;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * lock bean
 *
 * @author qiquan
 * @date 2021/07/24 16:26
 */
@Service
public class LockBean implements LockI {
    Set<String> lockSet = new HashSet<>();

    @Override
    public boolean lock(String key) {
        if (lockSet.contains(key)) {
            return false;
        }

        lockSet.add(key);
        return true;
    }

    @Override
    public void unlock(String key) {
        lockSet.remove(key);
    }
}
