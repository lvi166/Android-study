package com.cariad.astudy.javas;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.ParcelUuid;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import org.bytedeco.librealsense.device;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BleCore {


    private final static UUID SERVICE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private final static UUID CHARACTERISTIC_UUID = UUID.fromString("0000FFF1-0000-1000-8000-00805F9B34FB");


    // 获取BluetoothManager实例
    private BluetoothManager bluetoothManager = null;
    private BluetoothAdapter bluetoothAdapter = null;


    private Context context;
    // 使用BluetoothLeScanner进行高效扫描
    private BluetoothLeScanner bleScanner;
    private ScanSettings scanSettings;
    private List<ScanFilter> scanFilters;

    // 连接目标设备
    private BluetoothGatt bluetoothGatt;
    private final String targetDeviceAddress = "XX:XX:XX:XX:XX:XX";


    private void init(Context context) {
        this.context = context;
        // 检查BLE支持
        if (bluetoothAdapter == null || !context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(context, "设备不支持BLE", Toast.LENGTH_SHORT).show();

        }

        bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        bleScanner = bluetoothAdapter.getBluetoothLeScanner();

    }

    private void startScan() {


        // 配置扫描参数
        scanSettings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE)
                .setNumOfMatches(ScanSettings.MATCH_NUM_MAX_ADVERTISEMENT)
                .build();

        // 设置过滤条件（示例过滤特定服务UUID）
        scanFilters = new ArrayList<>();
        scanFilters.add(new ScanFilter.Builder()
                .setServiceUuid(ParcelUuid.fromString("0000FFF0-0000-1000-8000-00805F9B34FB"))
                .build());

        // 启动扫描
        bleScanner.startScan(scanFilters, scanSettings, scanCallback);


    }

    // 扫描结果回调
    private ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice device = result.getDevice();
            String deviceName = device.getName();
            String macAddress = device.getAddress();
            // 处理发现的设备
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.e("BLE", "扫描失败，错误码：" + errorCode);
        }
    };


    public void connectToDevice() {
        if (!checkLocationPermissions()) {

            return;
        }


        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(targetDeviceAddress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            bluetoothGatt = device.connectGatt(context, false, gattCallback,
                    BluetoothDevice.TRANSPORT_LE);


            bluetoothGatt.setPreferredPhy(BluetoothDevice.PHY_LE_1M_MASK,
                    BluetoothDevice.PHY_LE_1M_MASK, BluetoothDevice.PHY_OPTION_NO_PREFERRED);
        } else {
            bluetoothGatt = device.connectGatt(context, false, gattCallback);
        }


    }

    // GATT回调实现
    private final BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                gatt.discoverServices(); // 连接成功后立即发现服务
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                // 处理断开连接
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                List<BluetoothGattService> services = gatt.getServices();
                // 遍历服务并查找目标特征
                for (BluetoothGattService service : services) {
                    if (service.getUuid().equals(SERVICE_UUID)) {
                        BluetoothGattCharacteristic characteristic =
                                service.getCharacteristic(CHARACTERISTIC_UUID);

                        gatt.setCharacteristicNotification(characteristic, true);
                        // 配置描述符
                        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(
                                UUID.fromString("00002902-0000-1000-8000-00805F9B34FB"));
                        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                        gatt.writeDescriptor(descriptor);
                    }
                }
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt,
                                            BluetoothGattCharacteristic characteristic) {
            // 接收通知数据
            byte[] data = characteristic.getValue();
            processReceivedData(data);
        }
    };



    // 写入特征值
    public void writeCharacteristic(byte[] data) {
        BluetoothGattCharacteristic characteristic =
                bluetoothGatt.getService(SERVICE_UUID)
                        .getCharacteristic(CHARACTERISTIC_UUID);

        characteristic.setValue(data);
        characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
        bluetoothGatt.writeCharacteristic(characteristic);
    }

    // 读取特征值
    public void readCharacteristic() {
        BluetoothGattCharacteristic characteristic =
                bluetoothGatt.getService(SERVICE_UUID)
                        .getCharacteristic(CHARACTERISTIC_UUID);
        bluetoothGatt.readCharacteristic(characteristic);
    }


    // 请求运行时权限
    private static final int REQUEST_CODE_BLE_PERMISSIONS = 1001;



    private void checkBlePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestBlePermissions();
            }
        }
    }


    /**
     * 检查蓝牙扫描权限
     * @return
     */
    private boolean checkLocationPermissions() {

        if (context.checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) !=
                PackageManager.PERMISSION_GRANTED) {
            requestBlePermissions();

            Toast.makeText(context, "请授于蓝牙权限", Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }


    private void requestBlePermissions() {



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            context.requestPermissions(new String[]{
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, REQUEST_CODE_BLE_PERMISSIONS);
        } else {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, REQUEST_CODE_BLE_PERMISSIONS);
        }
    }


}
