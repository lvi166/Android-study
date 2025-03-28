#include <jni.h>
#include <android/bitmap.h>
#include <cmath>

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

}
