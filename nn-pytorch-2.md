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

[mult_lr_1_1]:img/pytorch_mult_lr_1_1.JPG
[mult_lr_1_2]:img/pytorch_mult_lr_1_2.JPG

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

[Заглавная](README.md)