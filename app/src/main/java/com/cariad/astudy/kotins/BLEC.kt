package com.cariad.astudy.kotins

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.content.Context

class BLEC {

    private var bluetoothAdapter: BluetoothAdapter? = null

    private var bluetoothGatt: BluetoothGatt? = null



    fun initBLE(context: Context){

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    }





}