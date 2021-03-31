[Заглавная](README.md)

# NN Pytorch
+ [Neuron Networks](nn.md#Neuron-Networks)
+ [PyTorch 1D Tensors Operations](#PyTorch-1D-Tensors-Operations)
+ [PyTorch Differentiation](#PyTorch-Differentiation)
+ [PyTorch Simple Dataset](#PyTorch-Simple-Dataset)
+ [PyTorch Получение данных из архива и предобработка изображений](#PyTorch-Получение-данных-из-архива-и-предобработка-изображений)

[derivative_f1]:img/derivative_f1.JPG
[derivative_f2]:img/derivative_f2.JPG
[boot_1]:img/boot_1.JPG

## PyTorch 1D Tensors Operations

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

[к оглавлению](#NN-Pytorch)

## PyTorch Differentiation

```python
import torch 
import matplotlib.pylab as plt
```
Derivatives
```python
x = torch.tensor(2.0, requires_grad = True)
print("The tensor x: ", x) # The tensor x:  tensor(2., requires_grad=True)
y = x ** 2
print("The result of y = x^2: ", y) # The result of y = x^2:  tensor(4., grad_fn=<PowBackward0>)
y.backward()
print("The dervative at x = 2: ", x.grad) # The dervative at x = 2:  tensor(4.)

print('data:',x.data) # data: tensor(2.)
print('grad_fn:',x.grad_fn) # grad_fn: None
print('grad:',x.grad) # grad: tensor(4.)
print("is_leaf:",x.is_leaf) # is_leaf: True
print("requires_grad:",x.requires_grad) # requires_grad: True

print('data:',y.data) # data: tensor(4.)
print('grad_fn:',y.grad_fn) # grad_fn: <PowBackward0 object at 0x7fa5cb396a90>
print('grad:',y.grad) # grad: None
print("is_leaf:",y.is_leaf) # is_leaf: False
print("requires_grad:",y.requires_grad) # requires_grad: True

x = torch.tensor(2.0, requires_grad = True)
y = x ** 2 + 2 * x + 1
print("The result of y = x^2 + 2x + 1: ", y) # The result of y = x^2 + 2x + 1:  tensor(9., grad_fn=<AddBackward0>)
y.backward()
print("The dervative at x = 2: ", x.grad) # The dervative at x = 2:  tensor(6.)
```
Можно самому описать функцию и её производные
```python
class SQ(torch.autograd.Function):


    @staticmethod
    def forward(ctx,i):
        """
        In the forward pass we receive a Tensor containing the input and return
        a Tensor containing the output. ctx is a context object that can be used
        to stash information for backward computation. You can cache arbitrary
        objects for use in the backward pass using the ctx.save_for_backward method.
        """
        result=i**2
        ctx.save_for_backward(i)
        return result

    @staticmethod
    def backward(ctx, grad_output):
        """
        In the backward pass we receive a Tensor containing the gradient of the loss
        with respect to the output, and we need to compute the gradient of the loss
        with respect to the input.
        """
        i, = ctx.saved_tensors
        grad_output = 2*i
        return grad_output


x=torch.tensor(2.0,requires_grad=True )
sq=SQ.apply

y=sq(x)
print(y.grad_fn) # <torch.autograd.function.SQBackward object at 0x7fa5cb432c88>
y.backward()
x.grad # tensor(4.)

```
Еще примеры
```python
u = torch.tensor(1.0,requires_grad=True)
v = torch.tensor(2.0,requires_grad=True)
f = u * v + u ** 2
print("The result of v * u + u^2: ", f) # The result of v * u + u^2:  tensor(3., grad_fn=<AddBackward0>)
f.backward()
print("The partial derivative with respect to u: ", u.grad) # The partial derivative with respect to u:  tensor(4.)
print("The partial derivative with respect to u: ", v.grad) # The partial derivative with respect to u:  tensor(1.)
```
Начертание графика функции и графика производных от X.
```python
x = torch.linspace(-10, 10, 10, requires_grad = True)
Y = x ** 2
y = torch.sum(x ** 2)
y.backward()

plt.plot(x.detach().numpy(), Y.detach().numpy(), label = 'function')
plt.plot(x.detach().numpy(), x.grad.detach().numpy(), label = 'derivative')
plt.xlabel('x')
plt.legend()
plt.show()
```
![icon][derivative_f1]
Начертание графика функции и графика производных от X.
```python
x = torch.linspace(-10, 10, 1000, requires_grad = True)
Y = torch.relu(x)
y = Y.sum()
y.backward()
plt.plot(x.detach().numpy(), Y.detach().numpy(), label = 'function')
plt.plot(x.detach().numpy(), x.grad.detach().numpy(), label = 'derivative')
plt.xlabel('x')
plt.legend()
plt.show()
```
![icon][derivative_f2]

[к оглавлению](#NN-Pytorch)

## PyTorch Simple Dataset

```python
import torch
from torch.utils.data import Dataset
torch.manual_seed(1)
```
Класс датасета
```python
class toy_set(Dataset):
    
    # Constructor with defult values 
    def __init__(self, length = 100, transform = None):
        self.len = length
        self.x = 2 * torch.ones(length, 2)
        self.y = torch.ones(length, 1)
        self.transform = transform
     
    # Getter
    def __getitem__(self, index):
        sample = self.x[index], self.y[index]
        if self.transform:
            sample = self.transform(sample)     
        return sample
    
    # Get Length
    def __len__(self):
        return self.len


our_dataset = toy_set()
print("Our toy_set object: ", our_dataset) # Our toy_set object:  <__main__.toy_set object at 0x7f5f7d2782e8>
print("Value on index 0 of our toy_set object: ", our_dataset[0]) # Value on index 0 of our toy_set object:  (tensor([2., 2.]), tensor([1.]))
print("Our toy_set length: ", len(our_dataset)) # Our toy_set length:  100

for i in range(3):
    x, y=our_dataset[i]
    print("index: ", i, '; x:', x, '; y:', y) # index:  0 ; x: tensor([2., 2.]) ; y: tensor([1.]) ...
```
Создаём класс, трансформирующий данные (transform)
```python
class add_mult(object):
    
    # Constructor
    def __init__(self, addx = 1, muly = 2):
        self.addx = addx
        self.muly = muly
    
    # Executor
    def __call__(self, sample):
        x = sample[0]
        y = sample[1]
        x = x + self.addx
        y = y * self.muly
        sample = x, y
        return sample


a_m = add_mult()
data_set = toy_set()

for i in range(10):
    x, y = data_set[i]
    print('Index: ', i, 'Original x: ', x, 'Original y: ', y)
    x_, y_ = a_m(data_set[i])
    print('Index: ', i, 'Transformed x_:', x_, 'Transformed y_:', y_)
# Index:  0 Original x:  tensor([2., 2.]) Original y:  tensor([1.])
# Index:  0 Transformed x_: tensor([3., 3.]) Transformed y_: tensor([2.])
# ...

cust_data_set = toy_set(transform = a_m)
for i in range(10):
    x, y = data_set[i]
    print('Index: ', i, 'Original x: ', x, 'Original y: ', y)
    x_, y_ = cust_data_set[i]
    print('Index: ', i, 'Transformed x_:', x_, 'Transformed y_:', y_)
# То же самое что выше
```
Создаём класс, позволяющий применять несколько transform (compose)
```python
from torchvision import transforms

class mult(object):
    
    # Constructor
    def __init__(self, mult = 100):
        self.mult = mult
        
    # Executor
    def __call__(self, sample):
        x = sample[0]
        y = sample[1]
        x = x * self.mult
        y = y * self.mult
        sample = x, y
        return sample


data_transform = transforms.Compose([add_mult(), mult()])
data_transform(data_set[0]) # (tensor([300., 300.]), tensor([200.]))

x,y=data_set[0]
x_,y_=data_transform(data_set[0])
print( 'Original x: ', x, 'Original y: ', y) # Original x:  tensor([2., 2.]) Original y:  tensor([1.])
print( 'Transformed x_:', x_, 'Transformed y_:', y_) # Transformed x_: tensor([300., 300.]) Transformed y_: tensor([200.])

compose_data_set = toy_set(transform = data_transform)
for i in range(3):
    x, y = data_set[i]
    print('Index: ', i, 'Original x: ', x, 'Original y: ', y)
    x_, y_ = cust_data_set[i]
    print('Index: ', i, 'Transformed x_:', x_, 'Transformed y_:', y_)
    x_co, y_co = compose_data_set[i]
    print('Index: ', i, 'Compose Transformed x_co: ', x_co ,'Compose Transformed y_co: ',y_co)
# Index:  0 Original x:  tensor([2., 2.]) Original y:  tensor([1.])
# Index:  0 Transformed x_: tensor([3., 3.]) Transformed y_: tensor([2.])
# Index:  0 Compose Transformed x_co:  tensor([300., 300.]) Compose Transformed y_co:  tensor([200.])
# ...
```

[к оглавлению](#NN-Pytorch)

## PyTorch Получение данных из архива и предобработка изображений

```python
import torch 
import numpy as np
from torch.utils.data import Dataset, DataLoader
from matplotlib.pyplot import imshow
import matplotlib.pylab as plt
from PIL import Image
import pandas as pd
import os
```
Загружаем изображения и разархивируем их.
```python
! wget https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-DL0110EN-SkillsNetwork/labs/Week1/data/img.tar.gz -P /resources/data
!tar -xf /resources/data/img.tar.gz 
```
Загружаем csv файл с лейблами и адресами изображений.
```python
!wget https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-DL0110EN-SkillsNetwork/labs/Week1/data/index.csv 
```
Функция для отображения образца и его подписи.
```python
def show_data(data_sample, shape = (28, 28)):
    plt.imshow(data_sample[0].numpy().reshape(shape), cmap='gray')
    plt.title('y = ' + data_sample[1])
```
Считываем данные с csv файла
```python
directory=""
csv_file ='index.csv'
csv_path=os.path.join(directory,csv_file)
data_name = pd.read_csv(csv_path)
data_name.head()
print('File name:', data_name.iloc[0, 1]) # File name: img/fashion0.png
print('y:', data_name.iloc[0, 0]) # y: Ankle boot
```
Загружаем и показываем изображение
```python
image_name =data_name.iloc[19, 1] # 'img/fashion20.png'
image_path=os.path.join(directory,image_name) # 'img/fashion20.png'
image = Image.open(image_path)
plt.imshow(image,cmap='gray', vmin=0, vmax=255)
plt.title(data_name.iloc[19, 0])
plt.show()
```
Создаём класс датасета
```python
class Dataset(Dataset):

    # Constructor
    def __init__(self, csv_file, data_dir, transform=None):
        
        # Image directory
        self.data_dir=data_dir
        
        # The transform is goint to be used on image
        self.transform = transform
        data_dircsv_file=os.path.join(self.data_dir,csv_file)
        # Load the CSV file contians image info
        self.data_name= pd.read_csv(data_dircsv_file)
        
        # Number of images in dataset
        self.len=self.data_name.shape[0] 
    
    # Get the length
    def __len__(self):
        return self.len
    
    # Getter
    def __getitem__(self, idx):
        
        # Image file path
        img_name=os.path.join(self.data_dir,self.data_name.iloc[idx, 1])
        # Open image file
        image = Image.open(img_name)
        
        # The class label for the image
        y = self.data_name.iloc[idx, 0]
        
        # If there is any transform method, apply it onto the image
        if self.transform:
            image = self.transform(image)

        return image, y


dataset = Dataset(csv_file=csv_file, data_dir=directory)
image=dataset[0][0]
y=dataset[0][1]

plt.imshow(image,cmap='gray', vmin=0, vmax=255)
plt.title(y)
plt.show()
```
![icon][boot_1]

Torchvision Transforms
```python
import torchvision.transforms as transforms

croptensor_data_transform = transforms.Compose([transforms.CenterCrop(20), transforms.ToTensor()])
dataset = Dataset(csv_file=csv_file , data_dir=directory,transform=croptensor_data_transform )
print("The shape of the first element tensor: ", dataset[0][0].shape) # The shape of the first element tensor:  torch.Size([1, 20, 20])
show_data(dataset[0],shape = (20, 20))
show_data(dataset[1],shape = (20, 20))

# Перевернуть изображение и превратить в тенсор
fliptensor_data_transform = transforms.Compose([transforms.RandomVerticalFlip(p=1),transforms.RandomHorizontalFlip(p=1),transforms.ToTensor()])
dataset = Dataset(csv_file=csv_file , data_dir=directory,transform=fliptensor_data_transform )
show_data(dataset[1])
```

[к оглавлению](#NN-Pytorch)

[Заглавная](README.md)