package com.cariad.astudy

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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

    private  val coldFlow = flow {
        repeat(5) { i ->
            delay(1000)
            emit(i)  // 仅在 collect 时触发
        }
    }

    suspend fun coldFlowCollect(){
        // 多次 collect 会重复执行流逻辑
        coldFlow.collect { println("A: $it") }  // 输出 0,1,2,3,4
        coldFlow.collect { println("B: $it") }  // 再次输出 0,1,2,3,4
    }




    // 定义一个高阶函数，接受两个整数和一个操作函数
    fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }



     fun main (args: Array<String>){



         CoroutineScope(Dispatchers.IO).launch {
             coldFlowCollect()
         }


         // 调用示例
         val sum = calculate(5, 3) { x, y -> x + y }  // 输出：8[3](@ref)
         val product = calculate(5, 3) { x, y -> x * y }  // 输出：15[3](@ref)
    }



}