import tensorflow as tf
import numpy as np

# 创建一个简单的模型，将输入图像反色
def create_model():
    model = tf.keras.Sequential([
        tf.keras.layers.InputLayer(input_shape=(None, None, 3)),  # 输入形状可变
        tf.keras.layers.Lambda(lambda x: 1.0 - x)  # 反色操作
    ])
    return model

# 创建并转换模型
model = create_model()
converter = tf.lite.TFLiteConverter.from_keras_model(model)
tflite_model = converter.convert()

# 保存模型
with open("invert_color.tflite", "wb") as f:
    f.write(tflite_model)
