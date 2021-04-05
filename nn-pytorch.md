[Заглавная](README.md)

# NN Pytorch
+ [Neuron Networks](nn.md#Neuron-Networks)
+ [PyTorch 1D Tensors Operations](#PyTorch-1D-Tensors-Operations)
+ [PyTorch Differentiation](#PyTorch-Differentiation)
+ [PyTorch Simple Dataset](#PyTorch-Simple-Dataset)
+ [PyTorch Получение данных из архива и предобработка изображений](#PyTorch-Получение-данных-из-архива-и-предобработка-изображений)
+ [PyTorch Linear 1D Regression](#PyTorch-Linear-1D-Regression)
+ [PyTorch Train 1 param Regression](#PyTorch-Train-1-param-Regression)
+ [PyTorch Train 2 param Regression](#PyTorch-Train-2-param-Regression)
+ [PyTorch Stochastic Gradient Descent](#PyTorch-Stochastic-Gradient-Descent)
+ [PyTorch Mini Batch Gradient Descent](#PyTorch-Mini-Batch-Gradient-Descent)
+ [PyTorch Way 2 param Mini Batch GD](#PyTorch-Way-2-param-Mini-Batch-GD)

[derivative_f1]:img/derivative_f1.JPG
[derivative_f2]:img/derivative_f2.JPG
[boot_1]:img/boot_1.JPG
[pytorch_lr_1]:img/pytorch_lr_1.JPG
[pytorch_lr_2]:img/pytorch_lr_2.JPG
[pytorch_lr_3]:img/pytorch_lr_3.JPG
[pytorch_lr_2_1]:img/pytorch_lr_2_1.JPG
[pytorch_lr_2_2]:img/pytorch_lr_2_2.JPG
[pytorch_lr_2_3]:img/pytorch_lr_2_3.JPG
[pytorch_lr_3_1]:img/pytorch_lr_3_1.JPG
[pytorch_lr_3_2]:img/pytorch_lr_3_2.JPG
[pytorch_lr_4_1]:img/pytorch_lr_4_1.JPG
[pytorch_lr_5_1]:img/pytorch_lr_5_1.JPG

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

## PyTorch Linear 1D Regression

```python
import torch
from torch.nn import Linear

torch.manual_seed(1)

lr = Linear(in_features=1, out_features=1, bias=True)
print("Parameters w and b: ", list(lr.parameters())) # Parameters w and b: tensor([[0.5153]] and tensor([-0.4414]
print("Python dictionary: ",lr.state_dict()) # OrderedDict([('weight', tensor([[0.5153]])), ('bias', tensor([-0.4414]))])
print("keys: ",lr.state_dict().keys()) # odict_keys(['weight', 'bias'])
print("values: ",lr.state_dict().values()) # odict_values([tensor([[0.5153]]), tensor([-0.4414])])
print("weight:",lr.weight) # tensor([[0.5153]], requires_grad=True)
print("bias:",lr.bias) # tensor([-0.4414], requires_grad=True)

x = torch.tensor([[1.0], [2.0]])
yhat = lr(x)
print("The prediction: ", yhat) # tensor([[0.0739], [0.5891]], grad_fn=<AddmmBackward>)
```
Custom Linear Regression Class
```python
from torch import nn

class LR(nn.Module):
    
    # Constructor
    def __init__(self, input_size, output_size):
        
        # Inherit from parent
        super(LR, self).__init__()
        self.linear = nn.Linear(input_size, output_size)
    
    # Prediction function
    def forward(self, x):
        out = self.linear(x)
        return out


x=torch.tensor([[1.0],[2.0],[3.0]])
lr=LR(1,1)
yhat=lr(x)
print(yhat) # tensor([[ 0.3030],[ 0.0973],[-0.1084]], grad_fn=<AddmmBackward>)
```

[к оглавлению](#NN-Pytorch)

## PyTorch Train 1 param Regression

Plot class
```python
import numpy as np
import matplotlib.pyplot as plt

class plot_diagram():
    
    # Constructor
    def __init__(self, X, Y, w, stop, go = False):
        start = w.data
        self.error = []
        self.parameter = []
        self.X = X.numpy()
        self.Y = Y.numpy()
        self.parameter_values = torch.arange(start, stop)
        self.Loss_function = [criterion(forward(X), Y) for w.data in self.parameter_values] 
        w.data = start
        
    # Executor
    def __call__(self, Yhat, w, error, n):
        self.error.append(error)
        self.parameter.append(w.data)
        plt.subplot(212)
        plt.plot(self.X, Yhat.detach().numpy())
        plt.plot(self.X, self.Y,'ro')
        plt.xlabel("A")
        plt.ylim(-20, 20)
        plt.subplot(211)
        plt.title("Data Space (top) Estimated Line (bottom) Iteration " + str(n))
        plt.plot(self.parameter_values.numpy(), self.Loss_function)   
        plt.plot(self.parameter, self.error, 'ro')
        plt.xlabel("B")
        plt.figure()
    
    # Destructor
    def __del__(self):
        plt.close('all')
```
Make Data
```python
import torch

X = torch.arange(-3, 3, 0.1).view(-1, 1)
f = -3 * X
Y = f + 0.1 * torch.randn(X.size()) # add noise

plt.plot(X.numpy(), Y.numpy(), 'rx', label = 'Y')
plt.plot(X.numpy(), f.numpy(), label = 'f')
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
plt.show()
```
![icon][pytorch_lr_1]

Create the model and Cost F
```python
def forward(x):
    return w * x

def criterion(yhat, y):
    return torch.mean((yhat - y) ** 2)

lr = 0.1
LOSS = []
w = torch.tensor(-10.0, requires_grad = True)
gradient_plot = plot_diagram(X, Y, w, stop = 5)
```
Train the model
```python
def train_model(iter):
    for epoch in range (iter):
        
        # make the prediction as we learned in the last lab
        Yhat = forward(X)
        
        # calculate the iteration
        loss = criterion(Yhat,Y)
        
        # plot the diagram for us to have a better idea
        gradient_plot(Yhat, w, loss.item(), epoch)
        
        # store the loss into list
        LOSS.append(loss.item())
        
        # backward pass: compute gradient of the loss with respect to all the learnable parameters
        loss.backward()
        
        # updata parameters
        w.data = w.data - lr * w.grad.data
        
        # zero the gradients before running the backward pass
        w.grad.data.zero_()

train_model(4)
```
![icon][pytorch_lr_2]

Plot the Loss
```python
plt.plot(LOSS)
plt.tight_layout()
plt.xlabel("Epoch/Iterations")
plt.ylabel("Cost")
```
![icon][pytorch_lr_3]

[к оглавлению](#NN-Pytorch)

## PyTorch Train 2 param Regression

Plot class
```python
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits import mplot3d

# The class for plot the diagram

class plot_error_surfaces(object):
    
    # Constructor
    def __init__(self, w_range, b_range, X, Y, n_samples = 30, go = True):
        W = np.linspace(-w_range, w_range, n_samples)
        B = np.linspace(-b_range, b_range, n_samples)
        w, b = np.meshgrid(W, B)    
        Z = np.zeros((30,30))
        count1 = 0
        self.y = Y.numpy()
        self.x = X.numpy()
        for w1, b1 in zip(w, b):
            count2 = 0
            for w2, b2 in zip(w1, b1):
                Z[count1, count2] = np.mean((self.y - w2 * self.x + b2) ** 2)
                count2 += 1
            count1 += 1
        self.Z = Z
        self.w = w
        self.b = b
        self.W = []
        self.B = []
        self.LOSS = []
        self.n = 0
        if go == True:
            plt.figure()
            plt.figure(figsize = (7.5, 5))
            plt.axes(projection='3d').plot_surface(self.w, self.b, self.Z, rstride = 1, cstride = 1,cmap = 'viridis', edgecolor = 'none')
            plt.title('Cost/Total Loss Surface')
            plt.xlabel('w')
            plt.ylabel('b')
            plt.show()
            plt.figure()
            plt.title('Cost/Total Loss Surface Contour')
            plt.xlabel('w')
            plt.ylabel('b')
            plt.contour(self.w, self.b, self.Z)
            plt.show()
    
    # Setter
    def set_para_loss(self, W, B, loss):
        self.n = self.n + 1
        self.W.append(W)
        self.B.append(B)
        self.LOSS.append(loss)
    
    # Plot diagram
    def final_plot(self): 
        ax = plt.axes(projection = '3d')
        ax.plot_wireframe(self.w, self.b, self.Z)
        ax.scatter(self.W,self.B, self.LOSS, c = 'r', marker = 'x', s = 200, alpha = 1)
        plt.figure()
        plt.contour(self.w,self.b, self.Z)
        plt.scatter(self.W, self.B, c = 'r', marker = 'x')
        plt.xlabel('w')
        plt.ylabel('b')
        plt.show()
    
    # Plot diagram
    def plot_ps(self):
        plt.subplot(121)
        plt.ylim
        plt.plot(self.x, self.y, 'ro', label="training points")
        plt.plot(self.x, self.W[-1] * self.x + self.B[-1], label = "estimated line")
        plt.xlabel('x')
        plt.ylabel('y')
        plt.ylim((-10, 15))
        plt.title('Data Space Iteration: ' + str(self.n))

        plt.subplot(122)
        plt.contour(self.w, self.b, self.Z)
        plt.scatter(self.W, self.B, c = 'r', marker = 'x')
        plt.title('Total Loss Surface Contour Iteration' + str(self.n))
        plt.xlabel('w')
        plt.ylabel('b')
        plt.show()
```
Make some data
```python
import torch

X = torch.arange(-3, 3, 0.1).view(-1, 1)
f = 1 * X - 1
Y = f + 0.1 * torch.randn(X.size()) # Add noise

plt.plot(X.numpy(), Y.numpy(), 'rx', label = 'y')
plt.plot(X.numpy(), f.numpy(), label = 'f')
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
```
Create the Model and Cost Function (Total Loss)
```python
def forward(x):
    return w * x + b

def criterion(yhat,y):
    return torch.mean((yhat-y)**2)

get_surface = plot_error_surfaces(15, 15, X, Y, 30)
```
![icon][pytorch_lr_2_1]

Train the Model
```python
w = torch.tensor(-15.0, requires_grad = True)
b = torch.tensor(-10.0, requires_grad = True)

lr = 0.1
LOSS = []

def train_model(iter):
    
    # Loop
    for epoch in range(iter):
        
        # make a prediction
        Yhat = forward(X)
        
        # calculate the loss 
        loss = criterion(Yhat, Y)

        # Section for plotting
        get_surface.set_para_loss(w.data.tolist(), b.data.tolist(), loss.tolist())
        if epoch % 3 == 0:
            get_surface.plot_ps()
            
        # store the loss in the list LOSS
        LOSS.append(loss)
        
        # backward pass: compute gradient of the loss with respect to all the learnable parameters
        loss.backward()
        
        # update parameters slope and bias
        w.data = w.data - lr * w.grad.data
        b.data = b.data - lr * b.grad.data
        
        # zero the gradients before running the backward pass
        w.grad.data.zero_()
        b.grad.data.zero_()


train_model(15)
get_surface.final_plot()
plt.plot(LOSS)
plt.tight_layout()
plt.xlabel("Epoch/Iterations")
plt.ylabel("Cost")
```
![icon][pytorch_lr_2_2]
![icon][pytorch_lr_2_3]

[к оглавлению](#NN-Pytorch)

## PyTorch Stochastic Gradient Descent

Preparation
```python
class plot_error_surfaces(object):
    ## PyTorch Train 2 param Regression
```
Make some data
```python
## PyTorch Train 2 param Regression
```
Create the Model and Cost Function (Total Loss)
```python
## PyTorch Train 2 param Regression
```

Train the Model: Batch Gradient Descent
```python
w = torch.tensor(-15.0, requires_grad = True)
b = torch.tensor(-10.0, requires_grad = True)
lr = 0.1
LOSS_BGD = []

# The function for training the model

def train_model(iter):
    
    # Loop
    for epoch in range(iter):
        
        # make a prediction
        Yhat = forward(X)
        
        # calculate the loss 
        loss = criterion(Yhat, Y)

        # Section for plotting
        get_surface.set_para_loss(w.data.tolist(), b.data.tolist(), loss.tolist())
        get_surface.plot_ps()
            
        # store the loss in the list LOSS_BGD
        LOSS_BGD.append(loss)
        
        # backward pass: compute gradient of the loss with respect to all the learnable parameters
        loss.backward()
        
        # update parameters slope and bias
        w.data = w.data - lr * w.grad.data
        b.data = b.data - lr * b.grad.data
        
        # zero the gradients before running the backward pass
        w.grad.data.zero_()
        b.grad.data.zero_()


train_model(10)
```
Train the Model: Stochastic Gradient Descent
```python
get_surface = plot_error_surfaces(15, 13, X, Y, 30, go = False)

# The function for training the model

LOSS_SGD = []
w = torch.tensor(-15.0, requires_grad = True)
b = torch.tensor(-10.0, requires_grad = True)

def train_model_SGD(iter):
    
    # Loop
    for epoch in range(iter):
        
        # SGD is an approximation of out true total loss/cost, in this line of code we calculate our true loss/cost and store it
        Yhat = forward(X)

        # store the loss 
        LOSS_SGD.append(criterion(Yhat, Y).tolist())
        
        for x, y in zip(X, Y):
            
            # make a pridiction
            yhat = forward(x)
        
            # calculate the loss 
            loss = criterion(yhat, y)

            # Section for plotting
            get_surface.set_para_loss(w.data.tolist(), b.data.tolist(), loss.tolist())
        
            # backward pass: compute gradient of the loss with respect to all the learnable parameters
            loss.backward()
        
            # update parameters slope and bias
            w.data = w.data - lr * w.grad.data
            b.data = b.data - lr * b.grad.data

            # zero the gradients before running the backward pass
            w.grad.data.zero_()
            b.grad.data.zero_()
            
        #plot surface and data space after each epoch    
        get_surface.plot_ps()


train_model_SGD(10)
```
![icon][pytorch_lr_3_1]

Compare the loss of both batch gradient descent as SGD.
```python
plt.plot(LOSS_BGD,label = "Batch Gradient Descent")
plt.plot(LOSS_SGD,label = "Stochastic Gradient Descent")
plt.xlabel('epoch')
plt.ylabel('Cost/ total loss')
plt.legend()
plt.show()
```
![icon][pytorch_lr_3_2]

SGD with Dataset DataLoader
```python
from torch.utils.data import Dataset, DataLoader

# Dataset Class

class Data(Dataset):
    
    # Constructor
    def __init__(self):
        self.x = torch.arange(-3, 3, 0.1).view(-1, 1)
        self.y = 1 * self.x - 1
        self.len = self.x.shape[0]
        
    # Getter
    def __getitem__(self,index):    
        return self.x[index], self.y[index]
    
    # Return the length
    def __len__(self):
        return self.len


dataset = Data()
print("The length of dataset: ", len(dataset)) # The length of dataset:  60
x, y = dataset[0]
print("(", x, ", ", y, ")") # ( tensor([-3.]) ,  tensor([-4.]) )
x, y = dataset[0:3]
print("The first 3 x: ", x) # The first 3 x:  tensor([[-3.0000], [-2.9000], [-2.8000]])
print("The first 3 y: ", y) # The first 3 y:  tensor([[-4.0000], [-3.9000], [-3.8000]])

get_surface = plot_error_surfaces(15, 13, X, Y, 30, go = False)
trainloader = DataLoader(dataset = dataset, batch_size = 1)

# The function for training the model

w = torch.tensor(-15.0,requires_grad=True)
b = torch.tensor(-10.0,requires_grad=True)
LOSS_Loader = []

def train_model_DataLoader(epochs):
    
    # Loop
    for epoch in range(epochs):
        
        # SGD is an approximation of out true total loss/cost, in this line of code we calculate our true loss/cost and store it
        Yhat = forward(X)
        
        # store the loss 
        LOSS_Loader.append(criterion(Yhat, Y).tolist())
        
        for x, y in trainloader:
            
            # make a prediction
            yhat = forward(x)
            
            # calculate the loss
            loss = criterion(yhat, y)
            
            # Section for plotting
            get_surface.set_para_loss(w.data.tolist(), b.data.tolist(), loss.tolist())
            
            # Backward pass: compute gradient of the loss with respect to all the learnable parameters
            loss.backward()
            
            # Updata parameters slope
            w.data = w.data - lr * w.grad.data
            b.data = b.data - lr* b.grad.data
            
            # Clear gradients 
            w.grad.data.zero_()
            b.grad.data.zero_()
            
        #plot surface and data space after each epoch    
        get_surface.plot_ps()


train_model_DataLoader(10)
```
![icon][pytorch_lr_3_1]

Compare the loss
```python
plt.plot(LOSS_BGD,label="Batch Gradient Descent")
plt.plot(LOSS_Loader,label="Stochastic Gradient Descent with DataLoader")
plt.xlabel('epoch')
plt.ylabel('Cost/ total loss')
plt.legend()
plt.show()
```
![icon][pytorch_lr_3_2]

[к оглавлению](#NN-Pytorch)

## PyTorch Mini Batch Gradient Descent

Preparation
```python
class plot_error_surfaces(object):
    ## PyTorch Train 2 param Regression
```
Make Some Data
```python
## PyTorch Train 2 param Regression
```
Create the Model and Cost Function (Total Loss)
```python
## PyTorch Train 2 param Regression
```
Mini Batch Gradient Descent
```python
get_surface = plot_error_surfaces(15, 13, X, Y, 30, go = False)

dataset = Data()
trainloader = DataLoader(dataset = dataset, batch_size = 5)

# Define train_model_Mini5 function

w = torch.tensor(-15.0, requires_grad = True)
b = torch.tensor(-10.0, requires_grad = True)
LOSS_MINI5 = []
lr = 0.1

def train_model_Mini5(epochs):
    for epoch in range(epochs):
        Yhat = forward(X)
        get_surface.set_para_loss(w.data.tolist(), b.data.tolist(), criterion(Yhat, Y).tolist())
        get_surface.plot_ps()
        LOSS_MINI5.append(criterion(forward(X), Y).tolist())
        for x, y in trainloader:
            yhat = forward(x)
            loss = criterion(yhat, y)
            get_surface.set_para_loss(w.data.tolist(), b.data.tolist(), loss.tolist())
            loss.backward()
            w.data = w.data - lr * w.grad.data
            b.data = b.data - lr * b.grad.data
            w.grad.data.zero_()
            b.grad.data.zero_()


train_model_Mini5(10)
```
Plot the LOSS for each method.
```python
plt.plot(LOSS_BGD,label = "Batch Gradient Descent")
plt.plot(LOSS_SGD,label = "Stochastic Gradient Descent")
plt.plot(LOSS_MINI5,label = "Mini-Batch Gradient Descent, Batch size: 5")
plt.plot(LOSS_MINI10,label = "Mini-Batch Gradient Descent, Batch size: 10")
plt.legend()
```
![icon][pytorch_lr_4_1]

[к оглавлению](#NN-Pytorch)

## PyTorch Way 2 param Mini Batch GD

Preparation
```python
class plot_error_surfaces(object):
    ## PyTorch Train 2 param Regression
```
Make Some Data
```python
## PyTorch Train 2 param Regression
```
Create a linear regression class
```python
# Create a linear regression model class

from torch import nn, optim

class linear_regression(nn.Module):
    
    # Constructor
    def __init__(self, input_size, output_size):
        super(linear_regression, self).__init__()
        self.linear = nn.Linear(input_size, output_size)
        
    # Prediction
    def forward(self, x):
        yhat = self.linear(x)
        return yhat


criterion = nn.MSELoss()

model = linear_regression(1,1)
optimizer = optim.SGD(model.parameters(), lr = 0.01)

list(model.parameters())

optimizer.state_dict()

trainloader = DataLoader(dataset = dataset, batch_size = 1)

model.state_dict()['linear.weight'][0] = -15
model.state_dict()['linear.bias'][0] = -10

get_surface = plot_error_surfaces(15, 13, dataset.x, dataset.y, 30, go = False)
```
Train the Model via Batch Gradient Descent
```python
# Train Model

def train_model_BGD(iter):
    for epoch in range(iter):
        for x,y in trainloader:
            yhat = model(x)
            loss = criterion(yhat, y)
            get_surface.set_para_loss(model, loss.tolist())          
            optimizer.zero_grad()
            loss.backward()

            optimizer.step()
        get_surface.plot_ps()


train_model_BGD(10)

model.state_dict() # OrderedDict([('linear.weight', tensor([[0.9932]])), ('linear.bias', tensor([-1.0174]))])
```
![icon][pytorch_lr_5_1]

[к оглавлению](#NN-Pytorch)

[Заглавная](README.md)