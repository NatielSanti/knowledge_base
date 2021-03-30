[Заглавная](README.md)

# Neuron Networks
+ [Функции активации](#Функции-активации)
+ [Keras regression](#Keras-regression)
+ [Keras classification](#Keras-classification)
+ [Keras Convolutional NN](#Keras-Convolutional-NN)
+ [PyTorch Tensors Operations](#PyTorch-Tensors-Operations)

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
```python
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

## PyTorch Tensors Operations

Импорт и объявление функции для начертания графиков.
```python
import numpy as np 
import pandas as pd

import matplotlib.pyplot as plt
%matplotlib inline  

# Plot vecotrs, please keep the parameters in the same length
# @param: Vectors = [{"vector": vector variable, "name": name of vector, "color": color of the vector on diagram}]
    
def plotVec(vectors):
    ax = plt.axes()
    
    # For loop to draw the vectors
    for vec in vectors:
        ax.arrow(0, 0, *vec["vector"], head_width = 0.05,color = vec["color"], head_length = 0.1)
        plt.text(*(vec["vector"] + 0.1), vec["name"])
    
    plt.ylim(-2,2)
    plt.xlim(-2,2)

u = torch.tensor([1, 0])
v = torch.tensor([0, 1])
w = u + v

plotVec([
    {"vector": u.numpy(), "name": 'u', "color": 'r'},
    {"vector": v.numpy(), "name": 'v', "color": 'b'},
    {"vector": w.numpy(), "name": 'w', "color": 'g'}
]) # Покажет график с тремя векторами.
```
Создание и получение типа тенсора
```python
floats_to_tensor = torch.tensor([0.0, 1.0, 2.0, 3.0, 4.0])
print("The dtype of tensor object after converting it to tensor: ", floats_to_tensor.dtype)
print("The type of tensor object after converting it to tensor: ", floats_to_tensor.type())
# The dtype of tensor object after converting it to tensor:  torch.float32
# The type of tensor object after converting it to tensor:  torch.FloatTensor

list_floats=[0.0, 1.0, 2.0, 3.0, 4.0]
floats_int_tensor=torch.tensor(list_floats,dtype=torch.int64)
print("The dtype of tensor object is: ", floats_int_tensor.dtype)
print("The type of tensor object is: ", floats_int_tensor.type())
# The dtype of tensor object is:  torch.int64
# The type of tensor object is:  torch.LongTensor

old_int_tensor = torch.tensor([0, 1, 2, 3, 4])
new_float_tensor = old_int_tensor.type(torch.FloatTensor) # torch.FloatTensor([0, 1, 2, 3, 4])
print("The type of the new_float_tensor:", new_float_tensor.type())
# The type of the new_float_tensor: torch.FloatTensor

print("The size of the new_float_tensor: ", new_float_tensor.size())
print("The dimension of the new_float_tensor: ",new_float_tensor.ndimension())
# The size of the new_float_tensor:  torch.Size([5])
# The dimension of the new_float_tensor:  1

twoD_float_tensor = new_float_tensor.view(5, 1) # new_float_tensor.view(-1, 1)
# Original Size:  tensor([0., 1., 2., 3., 4.])
# Size after view method tensor([[0.],
#         [1.],
#         [2.],
#         [3.],
#         [4.]])
```
Преобразование между panda, numpy и обычным массивом.
```python
numpy_array = np.array([0.0, 1.0, 2.0, 3.0, 4.0])
new_tensor = torch.from_numpy(numpy_array)
print("The dtype of new tensor: ", new_tensor.dtype)
print("The type of new tensor: ", new_tensor.type())
# The dtype of new tensor:  torch.float64
# The type of new tensor:  torch.DoubleTensor

back_to_numpy = new_tensor.numpy()
print("The numpy array from tensor: ", back_to_numpy)
print("The dtype of numpy array: ", back_to_numpy.dtype)
# The numpy array from tensor:  [0. 1. 2. 3. 4.]
# The dtype of numpy array:  float64

numpy_array[:] = 0
# The new tensor points to numpy_array :  tensor([0., 0., 0., 0., 0.], dtype=torch.float64)
# and back to numpy array points to the tensor:  [0. 0. 0. 0. 0.]

pandas_series=pd.Series([0.1, 2, 0.3, 10.1])
new_tensor=torch.from_numpy(pandas_series.values)
print("The new tensor from numpy array: ", new_tensor)
print("The dtype of new tensor: ", new_tensor.dtype)
print("The type of new tensor: ", new_tensor.type())
# The new tensor from numpy array:  tensor([ 0.1000,  2.0000,  0.3000, 10.1000], dtype=torch.float64)
# The dtype of new tensor:  torch.float64
# The type of new tensor:  torch.DoubleTensor

torch_to_list=this_tensor.tolist()
print('tensor:', this_tensor," list:",torch_to_list)
# tensor: tensor([0, 1, 2, 3]) list: [0, 1, 2, 3]
```
Разделение Tensors
```python
tensor_sample = torch.tensor([20, 1, 2, 3, 4])
subset_tensor_sample = tensor_sample[1:4]
# Original tensor sample:  tensor([100,   1,   2,   3,   0])
# The subset of tensor sample: tensor([1, 2, 3])

tensor_sample[3:5] = torch.tensor([300.0, 400.0])
# Inital value on index 3 and index 4: tensor([3, 0])
# Modified tensor: tensor([100,   1,   2, 300, 400])

selected_indexes = [3, 4]
subset_tensor_sample = tensor_sample[selected_indexes]
# The inital tensor_sample tensor([100,   1,   2, 300, 400])
# The subset of tensor_sample with the values on index 3 and 4:  tensor([300, 400])

selected_indexes = [1, 3]
tensor_sample[selected_indexes] = 100000
# The inital tensor_sample tensor([100,   1,   2, 300, 400])
# Modified tensor with one value:  tensor([   100, 100000,      2, 100000,    400])
```
Функции
```python
math_tensor = torch.tensor([1.0, -1.0, 1, -1])
mean = math_tensor.mean()
# The mean of math_tensor:  tensor(0.)

standard_deviation = math_tensor.std()
# The standard deviation of math_tensor:  tensor(1.1547)

max_min_tensor = torch.tensor([1, 1, 3, 5, 5])
max_val = max_min_tensor.max()
# Maximum number in the tensor:  tensor(5)

min_val = max_min_tensor.min()
# Minimum number in the tensor:  tensor(1)

pi_tensor = torch.tensor([0, np.pi/2, np.pi])
sin = torch.sin(pi_tensor)
# The sin result of pi_tensor:  tensor([ 0.0000e+00,  1.0000e+00, -8.7423e-08])
```
Создание Tensor с помощью linspace
```python
len_5_tensor = torch.linspace(-2, 2, steps = 5)
# First Try on linspace tensor([-2., -1.,  0.,  1.,  2.])
pi_tensor = torch.linspace(0, 2*np.pi, 100)
```

[к оглавлению](#Neuron-Networks)

[Заглавная](README.md)