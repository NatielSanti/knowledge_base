[Заглавная](README.md)

# NN Pytorch
+ [Neuron Networks](nn.md#Neuron-Networks)
+ [PyTorch 1D Tensors Operations](nn-pytorch-1.md#PyTorch-1D-Tensors-Operations)
+ [PyTorch Differentiation](nn-pytorch-1.md#PyTorch-Differentiation)
+ [PyTorch Simple Dataset](nn-pytorch-1.md#PyTorch-Simple-Dataset)
+ [PyTorch Получение данных из архива и предобработка изображений](nn-pytorch-1.md#PyTorch-Получение-данных-из-архива-и-предобработка-изображений)
+ [PyTorch Linear 1D Regression](nn-pytorch-1.md#PyTorch-Linear-1D-Regression)
+ [PyTorch Train 1 param Regression](nn-pytorch-1.md#PyTorch-Train-1-param-Regression)
+ [PyTorch Train 2 param Regression](nn-pytorch-1.md#PyTorch-Train-2-param-Regression)
+ [PyTorch Stochastic Gradient Descent](nn-pytorch-1.md#PyTorch-Stochastic-Gradient-Descent)
+ [PyTorch Mini Batch Gradient Descent](nn-pytorch-1.md#PyTorch-Mini-Batch-Gradient-Descent)
+ [PyTorch Way 2 param Mini Batch GD](nn-pytorch-1.md#PyTorch-Way-2-param-Mini-Batch-GD)
+ [PyTorch Choose learning rate and using validation data set](nn-pytorch-1.md#PyTorch-Choose-learning-rate-and-using-validation-data-set)
+ [PyTorch Multiple Linear Regression](nn-pytorch-2.md#PyTorch-Multiple-Linear-Regression)
+ [PyTorch Multiple Linear Regression Outputs](nn-pytorch-2.md#PyTorch-Multiple-Linear-Regression-Outputs)
+ [PyTorch Logistic Regression](nn-pytorch-2.md#PyTorch-Logistic-Regression)
+ [PyTorch Softmax 1D](nn-pytorch-2.md#PyTorch-Softmax-1D)
+ [PyTorch Softmax Number Classification](nn-pytorch-2.md#PyTorch-Softmax-Number-Classification)

[mult_lr_1_1]:img/pytorch/pytorch_mult_lr_1_1.JPG
[mult_lr_1_2]:img/pytorch/pytorch_mult_lr_1_2.JPG
[lr_7_1]:img/pytorch/pytorch_lr_7_1.JPG
[lr_7_2]:img/pytorch/pytorch_lr_7_2.JPG
[lr_7_3]:img/pytorch/pytorch_lr_7_3.JPG
[lr_7_4]:img/pytorch/pytorch_lr_7_4.JPG
[lr_7_5]:img/pytorch/pytorch_lr_7_5.JPG
[lr_7_6]:img/pytorch/pytorch_lr_7_6.JPG
[lr_7_7]:img/pytorch/pytorch_lr_7_7.JPG
[lr_7_8]:img/pytorch/pytorch_lr_7_8.JPG

[к оглавлению](#NN-Pytorch)

## PyTorch Multiple Linear Regression

Preparation
```python
from torch import nn
import torch
torch.manual_seed(1)

w = torch.tensor([[2.0], [3.0]], requires_grad=True)
b = torch.tensor([[1.0]], requires_grad=True)

def forward(x):
    yhat = torch.mm(x, w) + b
    return yhat

x = torch.tensor([[1.0, 2.0]])
yhat = forward(x)
print("The result: ", yhat) # The result:  tensor([[9.]], grad_fn=<AddBackward0>)

X = torch.tensor([[1.0, 1.0], [1.0, 2.0], [1.0, 3.0]])
yhat = forward(X)
print("The result: ", yhat) #  The result:  tensor([[ 6.], [ 9.],[12.]], grad_fn=<AddBackward0>)

model = nn.Linear(2, 1)
yhat = model(x)
print("The result: ", yhat) # The result:  tensor([[-0.3969]], grad_fn=<AddmmBackward>)

yhat = model(X)
print("The result: ", yhat) # The result:  tensor([[-0.0848], [-0.3969], [-0.7090]], grad_fn=<AddmmBackward>)
```
Build Custom Modules
```python
class linear_regression(nn.Module):
    
    # Constructor
    def __init__(self, input_size, output_size):
        super(linear_regression, self).__init__()
        self.linear = nn.Linear(input_size, output_size)
    
    # Prediction function
    def forward(self, x):
        yhat = self.linear(x)
        return yhat


model = linear_regression(2, 1)
print("The parameters: ", list(model.parameters())) # Parameter containing: tensor([[ 0.3319, -0.6657]], requires_grad=True), Parameter containing:tensor([0.4241], requires_grad=True)
print("The parameters: ", model.state_dict()) # The parameters:  OrderedDict([('linear.weight', tensor([[ 0.3319, -0.6657]])), ('linear.bias', tensor([0.4241]))])

yhat = model(x)
print("The result: ", yhat) # The result:  tensor([[-0.5754]], grad_fn=<AddmmBackward>)

yhat = model(X)
print("The result: ", yhat) # The result:  tensor([[ 0.0903],[-0.5754],[-1.2411]], grad_fn=<AddmmBackward>)
```
[к оглавлению](#NN-Pytorch)

## PyTorch Multiple Linear Regression Outputs

Preparation
```python
import torch
import numpy as np
import matplotlib.pyplot as plt
from torch import nn,optim
from mpl_toolkits.mplot3d import Axes3D
from torch.utils.data import Dataset, DataLoader
import torchvision.transforms as transforms

torch.manual_seed(1)

# The function for plotting 2D

def Plot_2D_Plane(model, dataset, n=0):
    w1 = model.state_dict()['linear.weight'].numpy()[0][0]
    w2 = model.state_dict()['linear.weight'].numpy()[0][1]
    b = model.state_dict()['linear.bias'].numpy()

    # Data
    x1 = data_set.x[:, 0].view(-1, 1).numpy()
    x2 = data_set.x[:, 1].view(-1, 1).numpy()
    y = data_set.y.numpy()

    # Make plane
    X, Y = np.meshgrid(np.arange(x1.min(), x1.max(), 0.05), np.arange(x2.min(), x2.max(), 0.05))
    yhat = w1 * X + w2 * Y + b

    # Plotting
    fig = plt.figure()
    ax = fig.gca(projection='3d')

    ax.plot(x1[:, 0], x2[:, 0], y[:, 0],'ro', label='y') # Scatter plot
    
    ax.plot_surface(X, Y, yhat) # Plane plot
    
    ax.set_xlabel('x1 ')
    ax.set_ylabel('x2 ')
    ax.set_zlabel('y')
    plt.title('estimated plane iteration:' + str(n))
    ax.legend()

    plt.show()
```
Make Some Data
```python
# Create a 2D dataset

class Data(Dataset):
    def __init__(self):
            self.x=torch.zeros(20,2)
            self.x[:,0]=torch.arange(-1,1,0.1)
            self.x[:,1]=torch.arange(-1,1,0.1)
            self.w=torch.tensor([ [1.0,-1.0],[1.0,3.0]])
            self.b=torch.tensor([[1.0,-1.0]])
            self.f=torch.mm(self.x,self.w)+self.b
            
            self.y=self.f+0.001*torch.randn((self.x.shape[0],1))
            self.len=self.x.shape[0]

    def __getitem__(self,index):

        return self.x[index],self.y[index]
    
    def __len__(self):
        return self.len


data_set = Data2D() 
```
Create the Model, Optimizer, and Total Loss Function (Cost)
```python
# Create a customized linear

class linear_regression(nn.Module):
    def __init__(self,input_size,output_size):
        super(linear_regression,self).__init__()
        self.linear=nn.Linear(input_size,output_size)
    def forward(self,x):
        yhat=self.linear(x)
        return yhat


model=linear_regression(2,2)
print("The parameters: ", list(model.parameters())) 

optimizer = optim.SGD(model.parameters(), lr=0.1)
criterion = nn.MSELoss()
train_loader = DataLoader(dataset=data_set, batch_size=5)
```
Train the Model via Mini-Batch Gradient Descent
```python
# Train the model

LOSS = []
print("Before Training: ")
Plot_2D_Plane(model, data_set)   
epochs = 100
   
def train_model(epochs):    
    for epoch in range(epochs):
        for x,y in train_loader:
            yhat = model(x)
            loss = criterion(yhat, y)
            LOSS.append(loss.item())
            optimizer.zero_grad()
            loss.backward()
            optimizer.step()  


train_model(epochs)
print("After Training: ")
Plot_2D_Plane(model, data_set, epochs)  
```
![icon][mult_lr_1_1]

Plot
```python
plt.plot(LOSS)
plt.xlabel("Iterations ")
plt.ylabel("Cost/total loss ")
```
![icon][mult_lr_1_2]

[к оглавлению](#NN-Pytorch)

## PyTorch Logistic Regression

Preparation
```python
import torch.nn as nn
import torch
import matplotlib.pyplot as plt 

torch.manual_seed(2)

z = torch.arange(-100, 100, 0.1).view(-1, 1)
sig = nn.Sigmoid()
yhat = sig(z)

plt.plot(z.numpy(), yhat.numpy())
plt.xlabel('z')
plt.ylabel('yhat')

yhat = torch.sigmoid(z)
plt.plot(z.numpy(), yhat.numpy()) # Нарисует обычный сигмоид
```

Build a Logistic Regression with nn.Sequential
```python
x = torch.tensor([[1.0]])
X = torch.tensor([[1.0], [100]])

model = nn.Sequential(nn.Linear(1, 1), nn.Sigmoid())

print("list(model.parameters()):\n ", list(model.parameters()))
print("\nmodel.state_dict():\n ", model.state_dict())
# list(model.parameters()): [Parameter containing:
# tensor([[0.2294]], requires_grad=True), Parameter containing:
# tensor([-0.2380], requires_grad=True)]

# model.state_dict():
#   OrderedDict([('0.weight', tensor([[0.2294]])), ('0.bias', tensor([-0.2380]))])

yhat = model(x)
print("The prediction: ", yhat) # The prediction:  tensor([[0.4979]], grad_fn=<SigmoidBackward>)

yhat = model(X)
print("The prediction: ", yhat) # tensor([[0.4979], [1.0000]], grad_fn=<SigmoidBackward>)

x = torch.tensor([[1.0, 1.0]])
X = torch.tensor([[1.0, 1.0], [1.0, 2.0], [1.0, 3.0]])

model = nn.Sequential(nn.Linear(2, 1), nn.Sigmoid())
print("list(model.parameters()):\n ", list(model.parameters()))
print("\nmodel.state_dict():\n ", model.state_dict())
# list(model.parameters()):[Parameter containing:
# tensor([[ 0.1939, -0.0361]], requires_grad=True), Parameter containing:
# tensor([0.3021], requires_grad=True)]

# model.state_dict():OrderedDict([('0.weight', tensor([[ 0.1939, -0.0361]])), ('0.bias', tensor([0.3021]))])

yhat = model(x)
print("The prediction: ", yhat) # The prediction:  tensor([[0.6130]], grad_fn=<SigmoidBackward>)
yhat = model(X)
print("The prediction: ", yhat) # The prediction:  tensor([[0.6130],[0.6044],[0.5957]], grad_fn=<SigmoidBackward>)
```
Build Custom Modules
```python
class logistic_regression(nn.Module):
    # Constructor
    def __init__(self, n_inputs):
        super(logistic_regression, self).__init__()
        self.linear = nn.Linear(n_inputs, 1)
    # Prediction
    def forward(self, x):
        yhat = torch.sigmoid(self.linear(x))
        return yhat


x = torch.tensor([[1.0]])
X = torch.tensor([[-100], [0], [100.0]])

model = logistic_regression(1)
print("list(model.parameters()):\n ", list(model.parameters()))
print("\nmodel.state_dict():\n ", model.state_dict())
# list(model.parameters()): [Parameter containing:
# tensor([[0.2381]], requires_grad=True), Parameter containing:
# tensor([-0.1149], requires_grad=True)]

yhat = model(x)
print("The prediction result: \n", yhat) # The prediction result: tensor([[0.5307]], grad_fn=<SigmoidBackward>)
yhat = model(X)
print("The prediction result: \n", yhat) # The prediction result: tensor([[4.0805e-11],[4.7130e-01],[1.0000e+00]], grad_fn=<SigmoidBackward>)

model = logistic_regression(2)
x = torch.tensor([[1.0, 2.0]])
X = torch.tensor([[100, -100], [0.0, 0.0], [-100, 100]])

yhat = model(x)
print("The prediction result: \n", yhat) # The prediction result: tensor([[0.2943]], grad_fn=<SigmoidBackward>)
yhat = model(X)
print("The prediction result: \n", yhat) # The prediction result: tensor([[7.7529e-33],[3.4841e-01],[1.0000e+00]], grad_fn=<SigmoidBackward>)
```

[к оглавлению](#NN-Pytorch)

## PyTorch Softmax 1D

Preparation
```python
import torch.nn as nn
import torch
import matplotlib.pyplot as plt 
import numpy as np
from torch.utils.data import Dataset, DataLoader

def plot_data(data_set, model = None, n = 1, color = False):
    X = data_set[:][0]
    Y = data_set[:][1]
    plt.plot(X[Y == 0, 0].numpy(), Y[Y == 0].numpy(), 'bo', label = 'y = 0')
    plt.plot(X[Y == 1, 0].numpy(), 0 * Y[Y == 1].numpy(), 'ro', label = 'y = 1')
    plt.plot(X[Y == 2, 0].numpy(), 0 * Y[Y == 2].numpy(), 'go', label = 'y = 2')
    plt.ylim((-0.1, 3))
    plt.legend()
    if model != None:
        w = list(model.parameters())[0][0].detach()
        b = list(model.parameters())[1][0].detach()
        y_label = ['yhat=0', 'yhat=1', 'yhat=2']
        y_color = ['b', 'r', 'g']
        Y = []
        for w, b, y_l, y_c in zip(model.state_dict()['0.weight'], model.state_dict()['0.bias'], y_label, y_color):
            Y.append((w * X + b).numpy())
            plt.plot(X.numpy(), (w * X + b).numpy(), y_c, label = y_l)
        if color == True:
            x = X.numpy()
            x = x.reshape(-1)
            top = np.ones(x.shape)
            y0 = Y[0].reshape(-1)
            y1 = Y[1].reshape(-1)
            y2 = Y[2].reshape(-1)
            plt.fill_between(x, y0, where = y1 > y1, interpolate = True, color = 'blue')
            plt.fill_between(x, y0, where = y1 > y2, interpolate = True, color = 'blue')
            plt.fill_between(x, y1, where = y1 > y0, interpolate = True, color = 'red')
            plt.fill_between(x, y1, where = ((y1 > y2) * (y1 > y0)),interpolate = True, color = 'red')
            plt.fill_between(x, y2, where = (y2 > y0) * (y0 > 0),interpolate = True, color = 'green')
            plt.fill_between(x, y2, where = (y2 > y1), interpolate = True, color = 'green')
    plt.legend()
    plt.show()


torch.manual_seed(0)
```
Make Some Data
```python
class Data(Dataset):
    
    # Constructor
    def __init__(self):
        self.x = torch.arange(-2, 2, 0.1).view(-1, 1)
        self.y = torch.zeros(self.x.shape[0])
        self.y[(self.x > -1.0)[:, 0] * (self.x < 1.0)[:, 0]] = 1
        self.y[(self.x >= 1.0)[:, 0]] = 2
        self.y = self.y.type(torch.LongTensor)
        self.len = self.x.shape[0]
        
    # Getter
    def __getitem__(self,index):      
        return self.x[index], self.y[index]
    
    # Get Length
    def __len__(self):
        return self.len


data_set = Data()
data_set.x
plot_data(data_set)
```
![icon][lr_7_1]
![icon][lr_7_2]

Build a Softmax Classifier and build the Model
```python
model = nn.Sequential(nn.Linear(1, 3))
model.state_dict()

criterion = nn.CrossEntropyLoss()
optimizer = torch.optim.SGD(model.parameters(), lr = 0.01)
trainloader = DataLoader(dataset = data_set, batch_size = 5)

LOSS = []
def train_model(epochs):
    for epoch in range(epochs):
        if epoch % 50 == 0:
            pass
            plot_data(data_set, model)
        for x, y in trainloader:
            optimizer.zero_grad()
            yhat = model(x)
            loss = criterion(yhat, y)
            LOSS.append(loss)
            loss.backward()
            optimizer.step()
train_model(300)
```
Analyze Results
```python
z =  model(data_set.x)
_, yhat = z.max(1)
print("The prediction:", yhat) # The prediction: tensor(
#       [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
#       1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2])
correct = (data_set.y == yhat).sum().item()
accuracy = correct / len(data_set)
print("The accuracy: ", accuracy) # The accuracy:  0.975

Softmax_fn=nn.Softmax(dim=-1)
Probability =Softmax_fn(z)
for i in range(3):
    print("probability of class {} isg given by  {}".format(i, Probability[0,i]) )
# probability of class 0 isg given by  0.9267547726631165
# probability of class 1 isg given by  0.07310982048511505
# probability of class 2 isg given by  0.00013548212882597
```

[к оглавлению](#NN-Pytorch)

## PyTorch Softmax Number Classification

Preparation
```python
import torch 
import torch.nn as nn
import torchvision.transforms as transforms
import torchvision.datasets as dsets
import matplotlib.pylab as plt
import numpy as np


def PlotParameters(model): 
    W = model.state_dict()['linear.weight'].data
    w_min = W.min().item()
    w_max = W.max().item()
    fig, axes = plt.subplots(2, 5)
    fig.subplots_adjust(hspace=0.01, wspace=0.1)
    for i, ax in enumerate(axes.flat):
        if i < 10:
            
            # Set the label for the sub-plot.
            ax.set_xlabel("class: {0}".format(i))

            # Plot the image.
            ax.imshow(W[i, :].view(28, 28), vmin=w_min, vmax=w_max, cmap='seismic')

            ax.set_xticks([])
            ax.set_yticks([])

        # Ensure the plot is shown correctly with multiple plots
        # in a single Notebook cell.
    plt.show()


def show_data(data_sample):
    plt.imshow(data_sample[0].numpy().reshape(28, 28), cmap='gray')
    plt.title('y = ' + str(data_sample[1].item()))
```
Make Some Data
```python
train_dataset = dsets.MNIST(root='./data', train=True, download=True, transform=transforms.ToTensor())
print("Print the training dataset:\n ", train_dataset)

validation_dataset = dsets.MNIST(root='./data', download=True, transform=transforms.ToTensor())
print("Print the validating dataset:\n ", validation_dataset)
# Print the validating dataset: Dataset MNIST Number of datapoints: 60000 Root location: ./data Split: Train StandardTransform Transform: ToTensor()

show_data(train_dataset[3])
show_data(train_dataset[2])
```
![icon][lr_7_3]
![icon][lr_7_4]
![icon][lr_7_5]

Build a Softmax Classifer
```python
class SoftMax(nn.Module):
    
    # Constructor
    def __init__(self, input_size, output_size):
        super(SoftMax, self).__init__()
        self.linear = nn.Linear(input_size, output_size)
        
    # Prediction
    def forward(self, x):
        z = self.linear(x)
        return z


train_dataset[0][0].shape # torch.Size([1, 28, 28])
input_dim = 28 * 28
output_dim = 10
```
Define the Softmax Classifier, Criterion Function, Optimizer, and Train the Model
```python
model = SoftMax(input_dim, output_dim)
print("Print the model:\n ", model) # Print the model: SoftMax((linear): Linear(in_features=784, out_features=10, bias=True))

print('W: ',list(model.parameters())[0].size()) # W:  torch.Size([10, 784])
print('b: ',list(model.parameters())[1].size()) # b:  torch.Size([10])

PlotParameters(model)
```
![icon][lr_7_6]

```python
learning_rate = 0.1
optimizer = torch.optim.SGD(model.parameters(), lr=learning_rate)
criterion = nn.CrossEntropyLoss()
train_loader = torch.utils.data.DataLoader(dataset=train_dataset, batch_size=100)
validation_loader = torch.utils.data.DataLoader(dataset=validation_dataset, batch_size=5000)

n_epochs = 10
loss_list = []
accuracy_list = []
N_test = len(validation_dataset)

def train_model(n_epochs):
    for epoch in range(n_epochs):
        for x, y in train_loader:
            optimizer.zero_grad()
            z = model(x.view(-1, 28 * 28))
            loss = criterion(z, y)
            loss.backward()
            optimizer.step()
            
        correct = 0
        # perform a prediction on the validationdata  
        for x_test, y_test in validation_loader:
            z = model(x_test.view(-1, 28 * 28))
            _, yhat = torch.max(z.data, 1)
            correct += (yhat == y_test).sum().item()
        accuracy = correct / N_test
        loss_list.append(loss.data)
        accuracy_list.append(accuracy)

train_model(n_epochs)
```
Analyze Results
```python
fig, ax1 = plt.subplots()
color = 'tab:red'
ax1.plot(loss_list,color=color)
ax1.set_xlabel('epoch',color=color)
ax1.set_ylabel('total loss',color=color)
ax1.tick_params(axis='y', color=color)
    
ax2 = ax1.twinx()  
color = 'tab:blue'
ax2.set_ylabel('accuracy', color=color)  
ax2.plot( accuracy_list, color=color)
ax2.tick_params(axis='y', color=color)
fig.tight_layout()
```
![icon][lr_7_7]

View the results of the parameters for each class after the training. You can see that they look like the corresponding numbers.
```python
PlotParameters(model)
```
![icon][lr_7_8]

Plot the parameters
```python
Softmax_fn=nn.Softmax(dim=-1)
count = 0
for x, y in validation_dataset:
    z = model(x.reshape(-1, 28 * 28))
    _, yhat = torch.max(z, 1)
    if yhat != y:
        show_data((x, y))
        plt.show()
        print("yhat:", yhat)
        print("probability of class ", torch.max(Softmax_fn(z)).item())
        count += 1
    if count >= 5:
        break   

Softmax_fn=nn.Softmax(dim=-1)
count = 0
for x, y in validation_dataset:
    z = model(x.reshape(-1, 28 * 28))
    _, yhat = torch.max(z, 1)
    if yhat == y:
        show_data((x, y))
        plt.show()
        print("yhat:", yhat)
        print("probability of class ", torch.max(Softmax_fn(z)).item())
        count += 1
    if count >= 5:
        break   
```

[к оглавлению](#NN-Pytorch)

[Заглавная](README.md)