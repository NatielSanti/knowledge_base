[Заглавная](README.md)

# Neuron Networks
+ [Функции активации](#Функции-активации)
+ [Keras regression](#Keras-regression)
+ [Keras classification](#Keras-classification)
+ [Keras Convolutional NN](#Keras-Convolutional-NN)

## Функции активации
![icon][activationfunc]
![icon][sigmoid]
![icon][relu]
![icon][hyper]
![icon][softmax]

[activationfunc]:img/activationfunc.JPG
[sigmoid]:img/sigmoid.JPG
[hyper]:img/hyper.JPG
[softmax]:img/softmax.JPG
[relu]:img/relu.JPG

[к оглавлению](#Neuron-Networks)

## Keras regression

Проверка данных
```python
import pandas as pd
import numpy as np

concrete_data = pd.read_csv('https://s3-api.us-geo.objectstorage.softlayer.net/cf-courses-data/CognitiveClass/DL0101EN/labs/data/concrete_data.csv')
concrete_data.head()
# Размер данных. Кол-во записей против количества столбцов
concrete_data.shape
# Оценка данных. Кол-во непустых столбцов, среднее пропорциональное, std, min, max и прочее
concrete_data.describe()
# Кол-во пустых значений в столбцах
concrete_data.isnull().sum()

```
Подготовка данных
```python
concrete_data_columns = concrete_data.columns
# делим данные на target и prediction
predictors = concrete_data[concrete_data_columns[concrete_data_columns != 'Strength']] # all columns except Strength
target = concrete_data['Strength'] # Strength column
# Нормализуем данные
predictors_norm = (predictors - predictors.mean()) / predictors.std()
n_cols = predictors_norm.shape[1] # number of predictors
```
Импортируем библиотеку Keras, создаём модель NN, обучаем модель
```python
import keras # Using TensorFlow backend
from keras.models import Sequential
from keras.layers import Dense

# define regression model
def regression_model():
    # create model
    model = Sequential()
    model.add(Dense(50, activation='relu', input_shape=(n_cols,)))
    model.add(Dense(50, activation='relu'))
    model.add(Dense(1))
    
    # compile model
    model.compile(optimizer='adam', loss='mean_squared_error')
    return model

# build the model
model = regression_model()
# fit the model
model.fit(predictors_norm, target, validation_split=0.3, epochs=100, verbose=2)
```

[к оглавлению](#Neuron-Networks)

## Keras classification

Импорт пакетов и тестовых данных из библиотеку Keras. Это числа
```python
import keras

from keras.models import Sequential
from keras.layers import Dense
from keras.utils import to_categorical
import matplotlib.pyplot as plt
# import the data
from keras.datasets import mnist

# read the data
(X_train, y_train), (X_test, y_test) = mnist.load_data()
plt.imshow(X_train[0]) #образец данных
# flatten images into one-dimensional vector

```
Подготавливаем данные
```python
num_pixels = X_train.shape[1] * X_train.shape[2] # find size of one-dimensional vector

X_train = X_train.reshape(X_train.shape[0], num_pixels).astype('float32') # flatten training images
X_test = X_test.reshape(X_test.shape[0], num_pixels).astype('float32') # flatten test images
# normalize inputs from 0-255 to 0-1
X_train = X_train / 255
X_test = X_test / 255
# one hot encode outputs
y_train = to_categorical(y_train)
y_test = to_categorical(y_test)

num_classes = y_test.shape[1]
print(num_classes)
```
Строим модель NN и обучаем её
```python
# define classification model
def classification_model():
    # create model
    model = Sequential()
    model.add(Dense(num_pixels, activation='relu', input_shape=(num_pixels,)))
    model.add(Dense(100, activation='relu'))
    model.add(Dense(num_classes, activation='softmax'))
    
    
    # compile model
    model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])
    return model

# build the model
model = classification_model()

# fit the model
model.fit(X_train, y_train, validation_data=(X_test, y_test), epochs=10, verbose=2)

# evaluate the model
scores = model.evaluate(X_test, y_test, verbose=0)
```
Оцениваем точность, затем сохраняем нашу модель в файл. После загружаем модель из файла
```python
print('Accuracy: {}% \n Error: {}'.format(scores[1], 1 - scores[1]))   
model.save('classification_model.h5')

from keras.models import load_model
pretrained_model = load_model('classification_model.h5')
```

[к оглавлению](#Neuron-Networks)

## Keras Convolutional NN

Проверка данных и импорт библиотек
```python
import keras
from keras.models import Sequential
from keras.layers import Dense
from keras.utils import to_categorical
from keras.layers.convolutional import Conv2D # to add convolutional layers
from keras.layers.convolutional import MaxPooling2D # to add pooling layers
from keras.layers import Flatten # to flatten data for fully connected layers

# import data
from keras.datasets import mnist

# load data
(X_train, y_train), (X_test, y_test) = mnist.load_data()

# reshape to be [samples][pixels][width][height]
X_train = X_train.reshape(X_train.shape[0], 28, 28, 1).astype('float32')
X_test = X_test.reshape(X_test.shape[0], 28, 28, 1).astype('float32')

# normalize data
X_train = X_train / 255 # normalize training data
X_test = X_test / 255 # normalize test data

# Next, let's convert the target variable into binary categories
y_train = to_categorical(y_train)
y_test = to_categorical(y_test)

num_classes = y_test.shape[1] # number of categories
```
Создаём модель NN и обучаем её
```python
def convolutional_model():
    
    # create model
    model = Sequential()
    model.add(Conv2D(16, (5, 5), strides=(1, 1), activation='relu', input_shape=(28, 28, 1)))
    model.add(MaxPooling2D(pool_size=(2, 2), strides=(2, 2)))
    
    model.add(Flatten())
    model.add(Dense(100, activation='relu'))
    model.add(Dense(num_classes, activation='softmax'))
    
    # compile model
    model.compile(optimizer='adam', loss='categorical_crossentropy',  metrics=['accuracy'])
    return model

# build the model
model = convolutional_model()

# fit the model
model.fit(X_train, y_train, validation_data=(X_test, y_test), epochs=10, batch_size=200, verbose=2)

# evaluate the model
scores = model.evaluate(X_test, y_test, verbose=0)
print("Accuracy: {} \n Error: {}".format(scores[1], 100-scores[1]*100))
```
Пример с двумя конволюционными и пул модулями
```puthon
def convolutional_model():
    
    # create model
    model = Sequential()
    model.add(Conv2D(16, (5, 5), activation='relu', input_shape=(28, 28, 1)))
    model.add(MaxPooling2D(pool_size=(2, 2), strides=(2, 2)))
    
    model.add(Conv2D(8, (2, 2), activation='relu'))
    model.add(MaxPooling2D(pool_size=(2, 2), strides=(2, 2)))
    
    model.add(Flatten())
    model.add(Dense(100, activation='relu'))
    model.add(Dense(num_classes, activation='softmax'))
    
    # Compile model
    model.compile(optimizer='adam', loss='categorical_crossentropy',  metrics=['accuracy'])
    return model

# build the model
model = convolutional_model()

# fit the model
model.fit(X_train, y_train, validation_data=(X_test, y_test), epochs=10, batch_size=200, verbose=2)

# evaluate the model
scores = model.evaluate(X_test, y_test, verbose=0)
print("Accuracy: {} \n Error: {}".format(scores[1], 100-scores[1]*100))
```
[к оглавлению](#Neuron-Networks)

[Заглавная](README.md)