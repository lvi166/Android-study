package com.cariad.astudy

import java.util.concurrent.locks.Lock

/**
 * @author: liyap
 * @date: 2025/3/10
 * @description: $
 */
class HigherFun {


    fun <T> lock(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        }
        finally {
            lock.unlock()
        }
    }


}