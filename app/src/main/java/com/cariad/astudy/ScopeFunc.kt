package com.cariad.astudy

import android.util.Log

import com.cariad.astudy.bean.Person
import kotlin.random.Random

/**
 * @author: liyap
 * @date: 2025/3/10
 * @description: $
 */
class ScopeFunc {

//   https://juejin.cn/post/6863853301210906638
//    在作用域函数的 lambda 表达式里，上下文对象可以不使用其实际名称而是使用一个更简短的引用（this 或 it）来访问。
//    作用域函数引用上下文对象有两种方式：
//
//    作为 lambda 表达式的接收者（this）: run、with、apply
//    作为 lambda 表达式的参数（it）: let、also
//    返回上下文对象：apply、also
//    返回 lambda 表达式结果：let、run、with


    companion object{
        private const val TAG = "ScopeFunc"
    }

    fun test(){
        letFun()
        runFun()
        applyFun()
        withFun()
        alsoFun()
    }

    fun letFun(){
        var person : Person? = Person();

        person?.let{
            Log.d(TAG, "letFun: ${it.name}")
        }



    }

    inline fun <T,R>T.let(block:(T)->R):R{

        return block(this)
    }

    fun runFun(){
        val result = listOf(1, 2, 3).run {
            plus(4)
            sum()  // 返回 10
        }

        Log.d(TAG, "runFun: $result")
    }

    fun applyFun(){
        val person : Person = Person().apply {
            name = "asa"
            age = 12
        };


        Log.d(TAG, "applyFun: ${person.name}")

    }


    fun withFun(){

    }

    fun alsoFun(){
        fun getRandomInt(): Int {
            return Random.nextInt(100).also {
                Log.d(TAG, "getRandomInt() generated value $it")
            }
        }

        val i = getRandomInt()

    }




}