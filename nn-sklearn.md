[Заглавная](README.md)

# Sklearn
+ [Neuron Networks](nn.md#Neuron-Networks)
+ [Simple Linear Regression](#Simple-Linear-Regression)
+ [Nonlinear Regression](#Nonlinear-Regression)
+ [Multiple Linear Regression](#Multiple-Linear-Regression)
+ [Polynomial Linear Regression](#Polynomial-Linear-Regression)
+ [Decision Tree Classification](#Decision-Tree-Classification)
+ [KNearest Classification](#KNearest-Classification)
+ [Logistic Regression Classification](#Logistic-Regression-Classification)
+ [SVM Classification](#SVM-Classification)
+ [DBScan Clustering](#DBScan-Clustering)
+ [Hierarchical Clustering](#Hierarchical-Clustering)
+ [KMeans Clustering](#KMeans-Clustering)
+ [Collaborative Recomendation System](#Collaborative-Recomendation-System)
+ [Content Based Recommendation System](#Content-Based-Recommendation-System)
+ [Sklearn Final Project](#Sklearn-Final-Project)

[к оглавлению](#Sklearn)

## Simple Linear Regression

```python
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn import linear_model
from sklearn.metrics import r2_score

if __name__ == '__main__':
    df = pd.read_csv("../resources/FuelConsumptionCo2.csv")

    # Lets select some features to explore more.
    cdf = df[['ENGINESIZE', 'CYLINDERS', 'FUELCONSUMPTION_COMB', 'CO2EMISSIONS']]

    # We can plot each of these fearues
    viz = cdf[['CYLINDERS', 'ENGINESIZE', 'CO2EMISSIONS', 'FUELCONSUMPTION_COMB']]
    viz.hist()
    plt.show()
    plt.scatter(cdf.ENGINESIZE, cdf.CO2EMISSIONS, color='blue')
    plt.xlabel("Engine size")
    plt.ylabel("Emission")
    plt.show()

    # Creating train and test dataset
    msk = np.random.rand(len(df)) < 0.8
    train = cdf[msk]
    test = cdf[~msk]

    # Using sklearn package to model data
    regr = linear_model.LinearRegression()
    train_x = np.asanyarray(train[['ENGINESIZE']])
    train_y = np.asanyarray(train[['CO2EMISSIONS']])
    regr.fit(train_x, train_y)

    # The coefficients
    print('Coefficients: ', regr.coef_)
    print('Intercept: ', regr.intercept_)

    # Plot outputs
    plt.scatter(train.ENGINESIZE, train.CO2EMISSIONS, color='blue')
    plt.plot(train_x, regr.coef_[0][0] * train_x + regr.intercept_[0], '-r')
    plt.xlabel("Engine size")
    plt.ylabel("Emission")
    plt.show()

    test_x = np.asanyarray(test[['ENGINESIZE']])
    test_y = np.asanyarray(test[['CO2EMISSIONS']])
    test_y_ = regr.predict(test_x)

    print("Mean absolute error: %.2f" % np.mean(np.absolute(test_y_ - test_y)))
    print("Residual sum of squares (MSE): %.2f" % np.mean((test_y_ - test_y) ** 2))
    print("R2-score: %.2f" % r2_score(test_y, test_y_))
```

[к оглавлению](#Sklearn)

## Nonlinear Regression

```python
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy.optimize import curve_fit
from sklearn.metrics import r2_score


def sigmoid(x, Beta_1, Beta_2):
    y = 1 / (1 + np.exp(-Beta_1 * (x - Beta_2)))
    return y


if __name__ == '__main__':
    # Non - Linear
    df = pd.read_csv("../resources/china_gdp.csv")

    # Plotting the Dataset
    plt.figure(figsize=(8, 5))
    x_data, y_data = (df["Year"].values, df["Value"].values)
    plt.plot(x_data, y_data, 'ro')
    plt.ylabel('GDP')
    plt.xlabel('Year')
    plt.show()

    # Choosing a model
    X = np.arange(-5.0, 5.0, 0.1)
    Y = 1.0 / (1.0 + np.exp(-X))
    plt.plot(X, Y)
    plt.ylabel('Dependent Variable')
    plt.xlabel('Independent Variable')
    plt.show()

    beta_1 = 0.10
    beta_2 = 1990.0

    # logistic function
    Y_pred = sigmoid(x_data, beta_1, beta_2)

    # plot initial prediction against datapoints
    plt.plot(x_data, Y_pred * 15000000000000.)
    plt.plot(x_data, y_data, 'ro')
    xdata = x_data / max(x_data)
    ydata = y_data / max(y_data)
    plt.show()

    popt, pcov = curve_fit(sigmoid, xdata, ydata)
    # print the final parameters
    print(" beta_1 = %f, beta_2 = %f" % (popt[0], popt[1]))
    x = np.linspace(1960, 2015, 55)
    x = x / max(x)
    plt.figure(figsize=(8, 5))
    y = sigmoid(x, *popt)
    plt.plot(xdata, ydata, 'ro', label='data')
    plt.plot(x, y, linewidth=3.0, label='fit')
    plt.legend(loc='best')
    plt.ylabel('GDP')
    plt.xlabel('Year')
    plt.show()

    # TODO Split all data to test and train data before train and doing metrics
    print("Mean absolute error: %.2f" % np.mean(np.absolute(ydata - y)))
    print("Residual sum of squares (MSE): %.2f" % np.mean((ydata - y) ** 2))
    print("R2-score: %.2f" % r2_score(ydata, y))

```

[к оглавлению](#Sklearn)

## Multiple Linear Regression

```python
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn import linear_model

if __name__ == '__main__':
    df = pd.read_csv("../resources/FuelConsumptionCo2.csv")

    # Lets select some features to explore more.
    cdf = df[['ENGINESIZE', 'CYLINDERS', 'FUELCONSUMPTION_CITY', 'FUELCONSUMPTION_HWY', 'FUELCONSUMPTION_COMB',
              'CO2EMISSIONS']]
    cdf.head(9)

    # We can plot each of these fearues
    viz = cdf[['CYLINDERS', 'ENGINESIZE', 'CO2EMISSIONS', 'FUELCONSUMPTION_COMB']]
    viz.hist()
    plt.show()

    plt.scatter(cdf.ENGINESIZE, cdf.CO2EMISSIONS, color='blue')
    plt.xlabel("Engine size")
    plt.ylabel("Emission")
    plt.show()

    # Creating train and test dataset
    msk = np.random.rand(len(df)) < 0.8
    train = cdf[msk]
    test = cdf[~msk]

    # Using sklearn package to model data
    regr = linear_model.LinearRegression()
    train_x = np.asanyarray(train[['ENGINESIZE', 'CYLINDERS', 'FUELCONSUMPTION_COMB']])
    train_y = np.asanyarray(train[['CO2EMISSIONS']])
    regr.fit(train_x, train_y)

    # The coefficients
    print('Coefficients: ', regr.coef_)
    print('Intercept: ', regr.intercept_)

    y_hat = regr.predict(test[['ENGINESIZE', 'CYLINDERS', 'FUELCONSUMPTION_COMB']])
    x = np.asanyarray(test[['ENGINESIZE', 'CYLINDERS', 'FUELCONSUMPTION_COMB']])
    y = np.asanyarray(test[['CO2EMISSIONS']])
    print("Residual sum of squares: %.2f"
          % np.mean((y_hat - y) ** 2))

    # Explained variance score: 1 is perfect prediction
    print('Variance score: %.2f' % regr.score(x, y))

```

[к оглавлению](#Sklearn)

## Polynomial Linear Regression

```python
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn import linear_model
from sklearn.metrics import r2_score
from sklearn.preprocessing import PolynomialFeatures

if __name__ == '__main__':
    df = pd.read_csv("../resources/FuelConsumptionCo2.csv")

    # Lets select some features to explore more.
    cdf = df[['ENGINESIZE', 'CYLINDERS', 'FUELCONSUMPTION_COMB', 'CO2EMISSIONS']]
    # cdf.head(9)

    # We can plot each of these fearues
    viz = cdf[['CYLINDERS', 'ENGINESIZE', 'CO2EMISSIONS', 'FUELCONSUMPTION_COMB']]
    viz.hist()
    plt.show()

    plt.scatter(cdf.ENGINESIZE, cdf.CO2EMISSIONS, color='blue')
    plt.xlabel("Engine size")
    plt.ylabel("Emission")
    plt.show()

    # Creating train and test dataset
    msk = np.random.rand(len(df)) < 0.8
    train = cdf[msk]
    test = cdf[~msk]

    # Using sklearn package to model data
    train_x = np.asanyarray(train[['ENGINESIZE']])
    train_y = np.asanyarray(train[['CO2EMISSIONS']])

    test_x = np.asanyarray(test[['ENGINESIZE']])
    test_y = np.asanyarray(test[['CO2EMISSIONS']])

    poly = PolynomialFeatures(degree=2)
    train_x_poly = poly.fit_transform(train_x)

    # The coefficients
    clf = linear_model.LinearRegression()
    train_y_ = clf.fit(train_x_poly, train_y)
    # The coefficients
    print('Coefficients: ', clf.coef_)
    print('Intercept: ', clf.intercept_)

    # Plot outputs
    plt.scatter(train.ENGINESIZE, train.CO2EMISSIONS, color='blue')
    XX = np.arange(0.0, 10.0, 0.1)
    yy = clf.intercept_[0] + clf.coef_[0][1] * XX + clf.coef_[0][2] * np.power(XX, 2)
    plt.plot(XX, yy, '-r')
    plt.xlabel("Engine size")
    plt.ylabel("Emission")
    plt.show()

    # Metrics
    test_x_poly = poly.fit_transform(test_x)
    test_y_ = clf.predict(test_x_poly)

    print("Mean absolute error: %.2f" % np.mean(np.absolute(test_y_ - test_y)))
    print("Residual sum of squares (MSE): %.2f" % np.mean((test_y_ - test_y) ** 2))
    print("R2-score: %.2f" % r2_score(test_y, test_y_))

```

[к оглавлению](#Sklearn)

## Decision Tree Classification

```python
import pandas as pd
from sklearn import metrics
from sklearn import preprocessing
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier

if __name__ == '__main__':
    # read data
    my_data = pd.read_csv("../resources/drug200.csv")

    # Extract feature data
    X = my_data[['Age', 'Sex', 'BP', 'Cholesterol', 'Na_to_K']].values
    y = my_data["Drug"]

    # transform labels to numeric data
    le_sex = preprocessing.LabelEncoder()
    le_sex.fit(['F', 'M'])  # F = 0; M = 1;
    X[:, 1] = le_sex.transform(X[:, 1])

    le_BP = preprocessing.LabelEncoder()
    le_BP.fit(['LOW', 'NORMAL', 'HIGH'])  # HIGH = 0; LOW = 1; NORMAL = 2;
    X[:, 2] = le_BP.transform(X[:, 2])

    le_Chol = preprocessing.LabelEncoder()
    le_Chol.fit(['NORMAL', 'HIGH'])  # HIGH = 0; NORMAL = 1;
    X[:, 3] = le_Chol.transform(X[:, 3])

    # Split data to train and test sets
    X_trainset, X_testset, y_trainset, y_testset = train_test_split(X, y, test_size=0.3, random_state=3)

    print('Shape of X training set {}'.format(X_trainset.shape), '&',
          ' Size of Y training set {}'.format(y_trainset.shape))
    # same thing
    # print('Train set:', X_trainset.shape, y_trainset.shape)
    print('Shape of X training set {}'.format(X_testset.shape), '&',
          ' Size of Y training set {}'.format(y_testset.shape))

    # Building a model
    drugTree = DecisionTreeClassifier(criterion="entropy", max_depth=4)
    drugTree.fit(X_trainset, y_trainset)

    # Trying to predict
    predTree = drugTree.predict(X_testset)

    # Metrics
    print("DecisionTrees's Accuracy: ", metrics.accuracy_score(y_testset, predTree))

```

[к оглавлению](#Sklearn)

## KNearest Classification

```python
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn import metrics
from sklearn import preprocessing
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier

if __name__ == '__main__':
    df = pd.read_csv("../resources/teleCust1000t.csv")

    # show data
    print(df['custcat'].value_counts())
    df.hist(column='income', bins=50)
    plt.show()

    # features and labels
    X = df[['region', 'tenure', 'age', 'marital', 'address', 'income', 'ed', 'employ', 'retire', 'gender',
            'reside']].values
    y = df['custcat'].values

    # normalize
    X = preprocessing.StandardScaler().fit(X).transform(X.astype(float))

    # Split data to train and test sets
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=4)
    print('Train set:', X_train.shape, y_train.shape)
    print('Test set:', X_test.shape, y_test.shape)

    # Train Model and Predict in different K (closest points)
    Ks = 10
    mean_acc = np.zeros((Ks - 1))
    std_acc = np.zeros((Ks - 1))

    for n in range(1, Ks):
        neigh = KNeighborsClassifier(n_neighbors=n).fit(X_train, y_train)
        yhat = neigh.predict(X_test)
        mean_acc[n - 1] = metrics.accuracy_score(y_test, yhat)
        std_acc[n - 1] = np.std(yhat == y_test) / np.sqrt(yhat.shape[0])

    plt.plot(range(1, Ks), mean_acc, 'g')
    plt.fill_between(range(1, Ks), mean_acc - 1 * std_acc, mean_acc + 1 * std_acc, alpha=0.10)
    plt.fill_between(range(1, Ks), mean_acc - 3 * std_acc, mean_acc + 3 * std_acc, alpha=0.10, color="green")
    plt.legend(('Accuracy ', '+/- 1xstd', '+/- 3xstd'))
    plt.ylabel('Accuracy ')
    plt.xlabel('Number of Neighbors (K)')
    plt.tight_layout()
    plt.show()

    print("The best accuracy was with", mean_acc.max(), "with k=", mean_acc.argmax() + 1)

```

[к оглавлению](#Sklearn)

## Logistic Regression Classification

```python
import numpy as np
import pandas as pd
from sklearn import preprocessing
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import confusion_matrix, classification_report
from sklearn.metrics import log_loss
from sklearn.model_selection import train_test_split

if __name__ == '__main__':
    churn_df = pd.read_csv("../resources/ChurnData.csv")

    # Data pre-processing and selection
    churn_df = churn_df[
        ['tenure', 'age', 'address', 'income', 'ed', 'employ', 'equip', 'callcard', 'wireless', 'churn']]
    churn_df['churn'] = churn_df['churn'].astype('int')
    X = np.asarray(churn_df[['tenure', 'age', 'address', 'income', 'ed', 'employ', 'equip']])
    y = np.asarray(churn_df['churn'])

    # Normalize data
    X = preprocessing.StandardScaler().fit(X).transform(X)
    print(X[0])

    # prepare test and train sets
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=4)
    print('Train set:', X_train.shape, y_train.shape)
    print('Test set:', X_test.shape, y_test.shape)

    # Modeling and predicting
    LR = LogisticRegression(C=0.01, solver='lbfgs').fit(X_train, y_train)  # ‘newton-cg’, ‘lbfgs’, ‘liblinear’,
    # ‘sag’, ‘saga’
    yhat = LR.predict(X_test)
    yhat_prob = LR.predict_proba(X_test)

    # Evaluation #1
    # print(jaccard_similarity_score(y_test, yhat))

    # Evaluation #2
    # Compute confusion matrix
    cnf_matrix = confusion_matrix(y_test, yhat, labels=[1, 0])
    print(cnf_matrix)

    # Plot non-normalized confusion matrix
    print(classification_report(y_test, yhat))

    # log loss
    print(log_loss(y_test, yhat_prob))

```

[к оглавлению](#Sklearn)

## SVM Classification

```python
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn import svm
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.metrics import f1_score
from sklearn.model_selection import train_test_split

if __name__ == '__main__':
    cell_df = pd.read_csv("../resources/cell_samples.csv")

    # show data
    ax = cell_df[cell_df['Class'] == 4][0:50].plot(kind='scatter', x='Clump', y='UnifSize',
                                                   color='DarkBlue', label='malignant')
    cell_df[cell_df['Class'] == 2][0:50].plot(kind='scatter', x='Clump', y='UnifSize',
                                              color='Yellow', label='benign', ax=ax)
    plt.show()

    # Data preProcessing
    cell_df = cell_df[pd.to_numeric(cell_df['BareNuc'], errors='coerce').notnull()]
    cell_df['BareNuc'] = cell_df['BareNuc'].astype('int')
    feature_df = cell_df[
        ['Clump', 'UnifSize', 'UnifShape', 'MargAdh', 'SingEpiSize', 'BareNuc', 'BlandChrom', 'NormNucl', 'Mit']]
    X = np.asarray(feature_df)
    cell_df['Class'] = cell_df['Class'].astype('int')
    y = np.asarray(cell_df['Class'])

    # Train/Test dataset
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=4)
    print('Train set:', X_train.shape, y_train.shape)
    print('Test set:', X_test.shape, y_test.shape)

    # Modeling
    clf = svm.SVC(kernel='rbf')  # Linear, Polynomial, Radial basis function (RBF), Sigmoid
    clf.fit(X_train, y_train)

    # Predict
    yhat = clf.predict(X_test)

    # Evaluation
    cnf_matrix = confusion_matrix(y_test, yhat, labels=[2, 4])
    print(cnf_matrix)
    print(classification_report(y_test, yhat))
    print(f1_score(y_test, yhat, average='weighted'))




```

[к оглавлению](#Sklearn)

## DBScan Clustering

```python
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import sklearn.utils
import sklearn.utils
from mpl_toolkits.basemap import Basemap
from pylab import rcParams
from sklearn.cluster import DBSCAN
from sklearn.preprocessing import StandardScaler

# Density-Based Spatial Clustering of Applications with Noise
if __name__ == '__main__':
    # load data
    pdf = pd.read_csv("../resources/weather-stations20140101-20141231.csv")
    # Cleaning
    pdf = pdf[pd.notnull(pdf["Tm"])]
    pdf = pdf.reset_index(drop=True)

    # Clustering of stations based on their location i.e. Lat & Lon
    sklearn.utils.check_random_state(1000)
    Clus_dataSet = pdf[['xm', 'ym']]
    Clus_dataSet = np.nan_to_num(Clus_dataSet)
    Clus_dataSet = StandardScaler().fit_transform(Clus_dataSet)

    # Compute DBSCAN
    db = DBSCAN(eps=0.15, min_samples=10).fit(Clus_dataSet)
    core_samples_mask = np.zeros_like(db.labels_, dtype=bool)
    core_samples_mask[db.core_sample_indices_] = True
    labels = db.labels_
    pdf["Clus_Db"] = labels

    realClusterNum = len(set(labels)) - (1 if -1 in labels else 0)
    clusterNum = len(set(labels))

    rcParams['figure.figsize'] = (14, 10)

    llon = -140
    ulon = -50
    llat = 40
    ulat = 65

    pdf = pdf[(pdf['Long'] > llon) & (pdf['Long'] < ulon) & (pdf['Lat'] > llat) & (pdf['Lat'] < ulat)]

    my_map = Basemap(projection='merc',
                     resolution='l', area_thresh=1000.0,
                     llcrnrlon=llon, llcrnrlat=llat,  # min longitude (llcrnrlon) and latitude (llcrnrlat)
                     urcrnrlon=ulon, urcrnrlat=ulat)  # max longitude (urcrnrlon) and latitude (urcrnrlat)

    my_map.drawcoastlines()
    my_map.drawcountries()
    # my_map.drawmapboundary()
    my_map.fillcontinents(color='white', alpha=0.3)
    my_map.shadedrelief()

    # To create a color map
    colors = plt.get_cmap('jet')(np.linspace(0.0, 1.0, clusterNum))

    # Visualization1
    for clust_number in set(labels):
        c = (([0.4, 0.4, 0.4]) if clust_number == -1 else colors[np.int(clust_number)])
        clust_set = pdf[pdf.Clus_Db == clust_number]
        my_map.scatter(clust_set.xm, clust_set.ym, color=c, marker='o', s=20, alpha=0.85)
        if clust_number != -1:
            cenx = np.mean(clust_set.xm)
            ceny = np.mean(clust_set.ym)
            plt.text(cenx, ceny, str(clust_number), fontsize=25, color='red', )
            print("Cluster " + str(clust_number) + ', Avg Temp: ' + str(np.mean(clust_set.Tm)))

```

[к оглавлению](#Sklearn)

## Hierarchical Clustering

```python
import pandas as pd
import pylab
from scipy.cluster import hierarchy
from sklearn.cluster import AgglomerativeClustering
from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics.pairwise import euclidean_distances
import matplotlib.cm as cm
import matplotlib.pyplot as plt
import numpy as np

if __name__ == '__main__':
    # load data
    pdf = pd.read_csv("../resources/cars_clus.csv")
    print("Shape of dataset: ", pdf.shape)

    # clean data
    pdf[['sales', 'resale', 'type', 'price', 'engine_s',
         'horsepow', 'wheelbas', 'width', 'length', 'curb_wgt', 'fuel_cap',
         'mpg', 'lnsales']] = pdf[['sales', 'resale', 'type', 'price', 'engine_s',
                                   'horsepow', 'wheelbas', 'width', 'length', 'curb_wgt', 'fuel_cap',
                                   'mpg', 'lnsales']].apply(pd.to_numeric, errors='coerce')
    pdf = pdf.dropna()
    pdf = pdf.reset_index(drop=True)
    print("Shape of dataset after cleaning: ", pdf.shape)

    # feature selection and normalization
    x = pdf[['engine_s', 'horsepow', 'wheelbas', 'width', 'length', 'curb_wgt', 'fuel_cap', 'mpg']].values
    feature_mtx = MinMaxScaler().fit_transform(x)

    dist_matrix = euclidean_distances(feature_mtx, feature_mtx)
    print(dist_matrix)

    Z_using_dist_matrix = hierarchy.linkage(dist_matrix, 'complete')
    fig = pylab.figure(figsize=(18, 50))


    def llf(id):
        return '[%s %s %s]' % (pdf['manufact'][id], pdf['model'][id], int(float(pdf['type'][id])))


    dendro = hierarchy.dendrogram(Z_using_dist_matrix, leaf_label_func=llf, leaf_rotation=0, leaf_font_size=12,
                                  orientation='right')

    agglom = AgglomerativeClustering(n_clusters=6, linkage='complete')
    agglom.fit(dist_matrix)
    pdf['cluster_'] = agglom.labels_
    n_clusters = max(agglom.labels_) + 1
    colors = cm.rainbow(np.linspace(0, 1, n_clusters))
    cluster_labels = list(range(0, n_clusters))

    # Create a figure of size 6 inches by 4 inches.
    plt.figure(figsize=(16, 14))

    for color, label in zip(colors, cluster_labels):
        subset = pdf[pdf.cluster_ == label]
        for i in subset.index:
            plt.text(subset.horsepow[i], subset.mpg[i], str(subset['model'][i]), rotation=25)
        plt.scatter(subset.horsepow, subset.mpg, s=subset.price * 10, c=color, label='cluster' + str(label), alpha=0.5)
    #    plt.scatter(subset.horsepow, subset.mpg)
    plt.legend()
    plt.title('Clusters')
    plt.xlabel('horsepow')
    plt.ylabel('mpg')
    pdf.groupby(['cluster_', 'type'])['cluster_'].count()
    agg_cars = pdf.groupby(['cluster_', 'type'])['horsepow', 'engine_s', 'mpg', 'price'].mean()
    plt.show()

```

[к оглавлению](#Sklearn)

## KMeans Clustering

```python
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from sklearn.preprocessing import StandardScaler
from mpl_toolkits.mplot3d import Axes3D


if __name__ == '__main__':
    # load data
    cust_df = pd.read_csv("../resources/Cust_Segmentation.csv")
    # drop Address column, bcz it not necessary in this case of ML
    df = cust_df.drop('Address', axis=1)
    # Normalizing data
    X = df.values[:, 1:]
    X = np.nan_to_num(X)
    Clus_dataSet = StandardScaler().fit_transform(X)
    # modeling
    clusterNum = 3
    k_means = KMeans(init="k-means++", n_clusters=clusterNum, n_init=12)
    k_means.fit(X)
    labels = k_means.labels_
    # Insights
    df["Clus_km"] = labels
    print(df)

    df.groupby('Clus_km').mean()
    area = np.pi * (X[:, 1]) ** 2
    plt.scatter(X[:, 0], X[:, 3], s=area, c=labels.astype(np.float), alpha=0.5)
    plt.xlabel('Age', fontsize=18)
    plt.ylabel('Income', fontsize=16)
    plt.show()

    fig = plt.figure(1, figsize=(8, 6))
    plt.clf()
    ax = Axes3D(fig, rect=[0, 0, .95, 1], elev=48, azim=134)
    plt.cla()
    ax.set_xlabel('Education')
    ax.set_ylabel('Age')
    ax.set_zlabel('Income')
    ax.scatter(X[:, 1], X[:, 0], X[:, 3], c=labels.astype(np.float))
    plt.show()
```

[к оглавлению](#Sklearn)

## Collaborative Recomendation System

```python
import pandas as pd
from math import sqrt
import numpy as np
import matplotlib.pyplot as plt

# Dataset not added to repository, bcz it too big.
# Link: https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-ML0101EN-SkillsNetwork/labs/Module%205/data/moviedataset.zip
if __name__ == '__main__':
    movies_df = pd.read_csv('../resources/moviedataset/ml-latest/movies.csv')
    ratings_df = pd.read_csv('../resources/moviedataset/ml-latest/ratings.csv')
    # Using regular expressions to find a year stored between parentheses
    # We specify the parantheses so we don't conflict with movies that have years in their titles
    movies_df['year'] = movies_df.title.str.extract('(\(\d\d\d\d\))', expand=False)
    # Removing the parentheses
    movies_df['year'] = movies_df.year.str.extract('(\d\d\d\d)', expand=False)
    # Removing the years from the 'title' column
    movies_df['title'] = movies_df.title.str.replace('(\(\d\d\d\d\))', '')
    # Applying the strip function to get rid of any ending whitespace characters that may have appeared
    movies_df['title'] = movies_df['title'].apply(lambda x: x.strip())
    # Dropping the genres column
    movies_df = movies_df.drop('genres', 1)
    # Drop removes a specified row or column from a dataframe
    ratings_df = ratings_df.drop('timestamp', 1)
    userInput = [
        {'title': 'Breakfast Club, The', 'rating': 5},
        {'title': 'Toy Story', 'rating': 3.5},
        {'title': 'Jumanji', 'rating': 2},
        {'title': "Pulp Fiction", 'rating': 5},
        {'title': 'Akira', 'rating': 4.5}
    ]
    inputMovies = pd.DataFrame(userInput)
    # Filtering out the movies by title
    inputId = movies_df[movies_df['title'].isin(inputMovies['title'].tolist())]
    # Then merging it so we can get the movieId. It's implicitly merging it by title.
    inputMovies = pd.merge(inputId, inputMovies)
    # Dropping information we won't use from the input dataframe
    inputMovies = inputMovies.drop('year', 1)
    # Filtering out users that have watched movies that the input has watched and storing it
    userSubset = ratings_df[ratings_df['movieId'].isin(inputMovies['movieId'].tolist())]
    # Groupby creates several sub dataframes where they all have the same value in the column specified as the parameter
    userSubsetGroup = userSubset.groupby(['userId'])
    # Sorting it so users with movie most in common with the input will have priority
    userSubsetGroup = sorted(userSubsetGroup, key=lambda x: len(x[1]), reverse=True)
    userSubsetGroup = userSubsetGroup[0:100]
    # Store the Pearson Correlation in a dictionary, where the key is the user Id and the value is the coefficient
    pearsonCorrelationDict = {}

    # For every user group in our subset
    for name, group in userSubsetGroup:
        # Let's start by sorting the input and current user group so the values aren't mixed up later on
        group = group.sort_values(by='movieId')
        inputMovies = inputMovies.sort_values(by='movieId')
        # Get the N for the formula
        nRatings = len(group)
        # Get the review scores for the movies that they both have in common
        temp_df = inputMovies[inputMovies['movieId'].isin(group['movieId'].tolist())]
        # And then store them in a temporary buffer variable in a list format to facilitate future calculations
        tempRatingList = temp_df['rating'].tolist()
        # Let's also put the current user group reviews in a list format
        tempGroupList = group['rating'].tolist()
        # Now let's calculate the pearson correlation between two users, so called, x and y
        Sxx = sum([i ** 2 for i in tempRatingList]) - pow(sum(tempRatingList), 2) / float(nRatings)
        Syy = sum([i ** 2 for i in tempGroupList]) - pow(sum(tempGroupList), 2) / float(nRatings)
        Sxy = sum(i * j for i, j in zip(tempRatingList, tempGroupList)) - sum(tempRatingList) * sum(
            tempGroupList) / float(nRatings)

        # If the denominator is different than zero, then divide, else, 0 correlation.
        if Sxx != 0 and Syy != 0:
            pearsonCorrelationDict[name] = Sxy / sqrt(Sxx * Syy)
        else:
            pearsonCorrelationDict[name] = 0

    pearsonDF = pd.DataFrame.from_dict(pearsonCorrelationDict, orient='index')
    pearsonDF.columns = ['similarityIndex']
    pearsonDF['userId'] = pearsonDF.index
    pearsonDF.index = range(len(pearsonDF))
    topUsers = pearsonDF.sort_values(by='similarityIndex', ascending=False)[0:50]
    topUsersRating = topUsers.merge(ratings_df, left_on='userId', right_on='userId', how='inner')
    # Multiplies the similarity by the user's ratings
    topUsersRating['weightedRating'] = topUsersRating['similarityIndex'] * topUsersRating['rating']
    # Applies a sum to the topUsers after grouping it up by userId
    tempTopUsersRating = topUsersRating.groupby('movieId').sum()[['similarityIndex', 'weightedRating']]
    tempTopUsersRating.columns = ['sum_similarityIndex', 'sum_weightedRating']
    # Creates an empty dataframe
    recommendation_df = pd.DataFrame()
    # Now we take the weighted average
    recommendation_df['weighted average recommendation score'] = tempTopUsersRating['sum_weightedRating'] / \
                                                                 tempTopUsersRating['sum_similarityIndex']
    recommendation_df['movieId'] = tempTopUsersRating.index
    recommendation_df = recommendation_df.sort_values(by='weighted average recommendation score', ascending=False)
    result = movies_df.loc[movies_df['movieId'].isin(recommendation_df.head(10)['movieId'].tolist())]
    print(result)
```

[к оглавлению](#Sklearn)

## Content Based Recommendation System

```python
import pandas as pd
from math import sqrt
import numpy as np
import matplotlib.pyplot as plt

# Dataset not added to repository, bcz it too big.
# Link: https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBMDeveloperSkillsNetwork-ML0101EN-SkillsNetwork/labs/Module%205/data/moviedataset.zip
if __name__ == '__main__':
    movies_df = pd.read_csv('../resources/moviedataset/ml-latest/movies.csv')
    ratings_df = pd.read_csv('../resources/moviedataset/ml-latest/ratings.csv')
    # Prepare data
    movies_df['year'] = movies_df.title.str.extract('(\(\d\d\d\d\))', expand=False)
    movies_df['year'] = movies_df.year.str.extract('(\d\d\d\d)', expand=False)
    movies_df['title'] = movies_df.title.str.replace('(\(\d\d\d\d\))', '')
    movies_df['title'] = movies_df['title'].apply(lambda x: x.strip())
    movies_df['genres'] = movies_df.genres.str.split('|')

    # Copying the movie dataframe into a new one since we won't need to use the genre information in our first case.
    moviesWithGenres_df = movies_df.copy()
    # For every row in the dataframe, iterate through the list of genres and place a 1 into the corresponding column
    for index, row in movies_df.iterrows():
        for genre in row['genres']:
            moviesWithGenres_df.at[index, genre] = 1
    # Filling in the NaN values with 0 to show that a movie doesn't have that column's genre
    moviesWithGenres_df = moviesWithGenres_df.fillna(0)

    # Drop removes a specified row or column from a dataframe
    ratings_df = ratings_df.drop('timestamp', 1)
    userInput = [
        {'title': 'Breakfast Club, The', 'rating': 5},
        {'title': 'Toy Story', 'rating': 3.5},
        {'title': 'Jumanji', 'rating': 2},
        {'title': "Pulp Fiction", 'rating': 5},
        {'title': 'Akira', 'rating': 4.5}
    ]
    inputMovies = pd.DataFrame(userInput)
    # Filtering out the movies by title
    inputId = movies_df[movies_df['title'].isin(inputMovies['title'].tolist())]
    # Then merging it so we can get the movieId. It's implicitly merging it by title.
    inputMovies = pd.merge(inputId, inputMovies)
    # Dropping information we won't use from the input dataframe
    inputMovies = inputMovies.drop('genres', 1).drop('year', 1)
    # Filtering out the movies from the input
    userMovies = moviesWithGenres_df[moviesWithGenres_df['movieId'].isin(inputMovies['movieId'].tolist())]
    # Resetting the index to avoid future issues
    userMovies = userMovies.reset_index(drop=True)
    # Dropping unnecessary issues due to save memory and to avoid issues
    userGenreTable = userMovies.drop('movieId', 1).drop('title', 1).drop('genres', 1).drop('year', 1)
    # Dot produt to get weights
    userProfile = userGenreTable.transpose().dot(inputMovies['rating'])
    # Now let's get the genres of every movie in our original dataframe
    genreTable = moviesWithGenres_df.set_index(moviesWithGenres_df['movieId'])
    # And drop the unnecessary information
    genreTable = genreTable.drop('movieId', 1).drop('title', 1).drop('genres', 1).drop('year', 1)
    # Multiply the genres by the weights and then take the weighted average
    recommendationTable_df = ((genreTable * userProfile).sum(axis=1)) / (userProfile.sum())
    # Sort our recommendations in descending order
    recommendationTable_df = recommendationTable_df.sort_values(ascending=False)
    # The final recommendation table
    result = movies_df.loc[movies_df['movieId'].isin(recommendationTable_df.head(20).keys())]
    print(result)
```

[к оглавлению](#Sklearn)

## Sklearn Final Project

```python
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import seaborn as sns
from sklearn import preprocessing
from sklearn import svm
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
# Final Course Project
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import jaccard_score
from sklearn.metrics import f1_score
from sklearn.metrics import log_loss


def plot(data, name):
    bins = np.linspace(data[name].min(), data[name].max(), 10)
    g = sns.FacetGrid(data, col="Gender", hue="loan_status", palette="Set1", col_wrap=2)
    g.map(plt.hist, name, bins=bins, ec="k")
    g.axes[-1].legend()
    plt.show()


if __name__ == '__main__':
    df = pd.read_csv("../resources/loan_train.csv")
    df['due_date'] = pd.to_datetime(df['due_date'])
    df['effective_date'] = pd.to_datetime(df['effective_date'])
    df['dayofweek'] = df['effective_date'].dt.dayofweek
    df['weekend'] = df['dayofweek'].apply(lambda x: 1 if (x > 3) else 0)

    # plot(df, 'Principal')
    # plot(df, 'age')
    # plot(df, 'dayofweek')
    print(df['loan_status'].value_counts())

    df.groupby(['Gender'])['loan_status'].value_counts(normalize=True)
    df['Gender'].replace(to_replace=['male', 'female'], value=[0, 1], inplace=True)
    df.groupby(['education'])['loan_status'].value_counts(normalize=True)

    Feature = df[['Principal', 'terms', 'age', 'Gender', 'weekend']]
    Feature = pd.concat([Feature, pd.get_dummies(df['education'])], axis=1)
    Feature.drop(['Master or Above'], axis=1, inplace=True)

    X = preprocessing.StandardScaler().fit(Feature).transform(Feature)
    y = df['loan_status'].values
    print(X[5])

    # Split data to train and test sets
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=4)
    print('Train set:', X_train.shape, y_train.shape)
    print('Test set:', X_test.shape, y_test.shape)

    # K Nearest Neighbor(KNN)
    Ks = 12
    mean_acc = np.zeros((Ks - 1))
    jac_acc = np.zeros((Ks - 1))

    for n in range(1, Ks):
        neigh = KNeighborsClassifier(n_neighbors=n).fit(X_train, y_train)
        yhat = neigh.predict(X_test)
        mean_acc[n - 1] = f1_score(y_test, yhat, average='weighted')
        jac_acc[n - 1] = jaccard_score(y_test, yhat, average='weighted')

    plt.plot(range(1, Ks), mean_acc, 'g')
    plt.plot(range(1, Ks), jac_acc, 'r')
    plt.ylabel('Accuracy ')
    plt.xlabel('Number of Neighbors (K)')
    plt.tight_layout()
    plt.show()
    print("Best K value 7")

    # Decision Tree
    max_depth = 10
    best_accuracy = 0
    best_metric_index = 0
    for n in range(1, max_depth):
        drugTree = DecisionTreeClassifier(criterion="entropy", max_depth=n)
        drugTree.fit(X_train, y_train)
        predTree = drugTree.predict(X_test)
        metric = f1_score(y_test, predTree, average='weighted')
        if metric >= best_accuracy:
            best_accuracy = metric
            best_metric_index = n

    print("DecisionTrees's Best Accuracy: ", best_accuracy, " with depth:", best_metric_index)

    # Support Vector Machine
    clf = svm.SVC(kernel='rbf')
    clf.fit(X_train, y_train)
    yhat = clf.predict(X_test)
    print(f1_score(y_test, yhat, average='weighted'))

    # Logistic Regression
    LR = LogisticRegression(C=0.01, solver='lbfgs').fit(X_train, y_train)
    yhat = LR.predict(X_test)
    yhat_prob = LR.predict_proba(X_test)
    print(f1_score(y_test, yhat, average='weighted'))

```

[к оглавлению](#Sklearn)

[Заглавная](README.md)