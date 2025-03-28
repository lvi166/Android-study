#include <jni.h>
#include <android/bitmap.h>
#include <android/log.h>
#include <android/native_window_jni.h>
#include <cmath>

#define LOG_TAG "navtive-lib"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

extern "C" {

/**
 *  获取像素数据
 */
void getPixel(AndroidBitmapInfo* info, void* pixels, int x, int y, uint32_t& r, uint32_t& g, uint32_t& b) {
    uint32_t* line = (uint32_t*) ((char*)pixels + y * info->stride);
    uint32_t pixel = line[x];
    r = (pixel & 0x00FF0000) >> 16;
    g = (pixel & 0x0000FF00) >> 8;
    b = (pixel & 0x000000FF);
}

/**
 *  设置像素数据
 */
void setPixel(AndroidBitmapInfo* info, void* pixels, int x, int y, uint32_t r, uint32_t g, uint32_t b) {
    uint32_t* line = (uint32_t*) ((char*)pixels + y * info->stride);
    line[x] = (0xFF << 24) | (r << 16) | (g << 8) | b;
}

/**
 *  JNI - 反色
 */
JNIEXPORT void JNICALL Java_com_example_NativeImageProcessor_invertBitmap(JNIEnv *env, jclass clazz, jobject bitmap) {
    AndroidBitmapInfo info;
    void* pixels;
    if (AndroidBitmap_getInfo(env, bitmap, &info) < 0 || AndroidBitmap_lockPixels(env, bitmap, &pixels) < 0) return;

    for (int y = 0; y < info.height; y++) {
        for (int x = 0; x < info.width; x++) {
            uint32_t r, g, b;
            getPixel(&info, pixels, x, y, r, g, b);
            setPixel(&info, pixels, x, y, 255 - r, 255 - g, 255 - b);
        }
    }

    AndroidBitmap_unlockPixels(env, bitmap);
}

/**
 *  JNI - 灰度化
 */
JNIEXPORT void JNICALL Java_com_example_NativeImageProcessor_toGrayScale(JNIEnv *env, jclass clazz, jobject bitmap) {
    AndroidBitmapInfo info;
    void* pixels;
    if (AndroidBitmap_getInfo(env, bitmap, &info) < 0 || AndroidBitmap_lockPixels(env, bitmap, &pixels) < 0) return;

    for (int y = 0; y < info.height; y++) {
        for (int x = 0; x < info.width; x++) {
            uint32_t r, g, b;
            getPixel(&info, pixels, x, y, r, g, b);
            uint32_t gray = static_cast<uint32_t>(0.299 * r + 0.587 * g + 0.114 * b);
            setPixel(&info, pixels, x, y, gray, gray, gray);
        }
    }

    AndroidBitmap_unlockPixels(env, bitmap);
}

/**
 *  JNI - 亮度调整
 */
JNIEXPORT void JNICALL Java_com_example_NativeImageProcessor_adjustBrightness(JNIEnv *env, jclass clazz, jobject bitmap, jint brightness) {
    AndroidBitmapInfo info;
    void* pixels;
    if (AndroidBitmap_getInfo(env, bitmap, &info) < 0 || AndroidBitmap_lockPixels(env, bitmap, &pixels) < 0) return;

    for (int y = 0; y < info.height; y++) {
        for (int x = 0; x < info.width; x++) {
            uint32_t r, g, b;
            getPixel(&info, pixels, x, y, r, g, b);
            r = std::min(255, std::max(0, static_cast<int>(r + brightness)));
            g = std::min(255, std::max(0, static_cast<int>(g + brightness)));
            b = std::min(255, std::max(0, static_cast<int>(b + brightness)));
            setPixel(&info, pixels, x, y, r, g, b);
        }
    }

    AndroidBitmap_unlockPixels(env, bitmap);
}

/**
 *  JNI - 对比度调整
 */
JNIEXPORT void JNICALL Java_com_example_NativeImageProcessor_adjustContrast(JNIEnv *env, jclass clazz, jobject bitmap, jfloat contrast) {
    AndroidBitmapInfo info;
    void* pixels;
    if (AndroidBitmap_getInfo(env, bitmap, &info) < 0 || AndroidBitmap_lockPixels(env, bitmap, &pixels) < 0) return;

    for (int y = 0; y < info.height; y++) {
        for (int x = 0; x < info.width; x++) {
            uint32_t r, g, b;
            getPixel(&info, pixels, x, y, r, g, b);
            r = std::min(255, std::max(0, static_cast<int>((r - 128) * contrast + 128)));
            g = std::min(255, std::max(0, static_cast<int>((g - 128) * contrast + 128)));
            b = std::min(255, std::max(0, static_cast<int>((b - 128) * contrast + 128)));
            setPixel(&info, pixels, x, y, r, g, b);
        }
    }

    AndroidBitmap_unlockPixels(env, bitmap);
}


JNIEXPORT jobject JNICALL Java_com_cariad_astudy_getBitmap(JNIEnv *env, jclass clazz, jobject bitmap) {
    return bitmap;
}

}





jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    return JNI_VERSION_1_6;

}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_cariad_astudy_JNILib_stringFromJNI(JNIEnv *env, jobject thiz) {
    // TODO: implement stringFromJNI()
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_cariad_astudy_JNILib_test(JNIEnv *env, jobject thiz) {
    // TODO: implement test()
}
extern "C"
JNIEXPORT jobject JNICALL
Java_com_cariad_astudy_JNILib_invertColor(JNIEnv *env, jobject thiz, jobject originalBitmap,
                                          jobject invertedBitmap) {
    AndroidBitmapInfo info;
    int ret;

    // 获取原始 Bitmap 的信息
    if ((ret = AndroidBitmap_getInfo(env, originalBitmap, &info)) < 0) {
        return NULL;
    }
    // 目前仅支持 ARGB_8888 格式
    if (info.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
        return NULL;
    }

    // 锁定原始 Bitmap 的像素数据
    void* origPixels;
    if ((ret = AndroidBitmap_lockPixels(env, originalBitmap, &origPixels)) < 0) {
        return NULL;
    }

    // 锁定目标 Bitmap 的像素数据
    void* invPixels;
    if ((ret = AndroidBitmap_lockPixels(env, invertedBitmap, &invPixels)) < 0) {
        AndroidBitmap_unlockPixels(env, originalBitmap);
        return NULL;
    }

    // 将像素数据转换为 32 位整数数组（每个像素 4 字节）
    uint32_t* orig = (uint32_t*) origPixels;
    uint32_t* inv = (uint32_t*) invPixels;
    int width = info.width;
    int height = info.height;
    int size = width * height;

    // 遍历每个像素，反转 RGB 分量（保留 Alpha）
    for (int i = 0; i < size; i++) {
        uint32_t pixel = orig[i];
        uint8_t a = (pixel >> 24) & 0xFF;
        uint8_t r = (pixel >> 16) & 0xFF;
        uint8_t g = (pixel >> 8) & 0xFF;
        uint8_t b = pixel & 0xFF;

        // 颜色反转：RGB = 255 - 原值
        r = 255 - r;
        g = 255 - g;
        b = 255 - b;

        // 重新组合像素值
        inv[i] = (a << 24) | (r << 16) | (g << 8) | b;
    }

    // 解锁像素数据
    AndroidBitmap_unlockPixels(env, originalBitmap);
    AndroidBitmap_unlockPixels(env, invertedBitmap);

    // 返回修改后的 Bitmap 对象
    return invertedBitmap;
}