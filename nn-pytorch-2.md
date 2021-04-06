[Заглавная](README.md)

# NN Pytorch 2
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

[derivative_f1]:img/derivative_f1.JPG

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

[Заглавная](README.md)