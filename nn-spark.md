[Заглавная](README.md)

# NN Spark
+ [Neuron Networks](nn.md#Neuron-Networks)
+ [Spark Parquet](#Parquet)
+ [Spark Metrics](#Metrics)
+ [Spark Multiple File](#Multiple-File)

## Parquet

```python
from pyspark import SparkContext, SparkConf
from pyspark.sql import SparkSession
import matplotlib.pyplot as plt

if __name__ == '__main__':
    sc = SparkContext.getOrCreate(SparkConf().setMaster("local[*]"))
    spark = SparkSession.builder.getOrCreate()

    df = spark.read.parquet('../resources/washing.parquet')
    df.createOrReplaceTempView('df')

    df.createOrReplaceTempView("washing")

    spark.sql("SELECT * FROM washing").show()

    result = spark.sql("select voltage,ts from washing where voltage is not null order by ts asc")
    # result_rdd = result.rdd.sample(False, 0.1).map(lambda row: (row.ts, row.voltage)).collect()
    # print(result_rdd.__sizeof__())
    # print(result_rdd[0])
    # print(result_rdd)
    # result_array_ts = result_rdd.map(lambda ts_voltage: ts_voltage[0]).collect()
    # result_array_voltage = result_rdd.map(lambda ts_voltage: ts_voltage[1]).collect()

    result_rdd = result.rdd.sample(False, 0.1).map(lambda row: (row.ts, row.voltage))
    result_array_ts = result_rdd.map(lambda ts_voltage: ts_voltage[0]).collect()
    result_array_voltage = result_rdd.map(lambda ts_voltage: ts_voltage[1]).collect()

    print(result_array_ts[:15])
    print(result_array_voltage[:15])

    plt.plot(result_array_ts, result_array_voltage)
    plt.xlabel("time")
    plt.ylabel("voltage")
    plt.show()

    result_df = spark.sql("""
    select hardness,temperature,flowrate from washing
        where hardness is not null and 
        temperature is not null and 
        flowrate is not null
    """)
    result_rdd = result_df.rdd.sample(False, 0.1).map(lambda row: (row.hardness, row.temperature, row.flowrate))
    result_array_hardness = result_rdd.map(
        lambda hardness_temperature_flowrate: hardness_temperature_flowrate[0]).collect()
    result_array_temperature = result_rdd.map(
        lambda hardness_temperature_flowrate: hardness_temperature_flowrate[1]).collect()
    result_array_flowrate = result_rdd.map(
        lambda hardness_temperature_flowrate: hardness_temperature_flowrate[2]).collect()

    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.scatter(result_array_hardness, result_array_temperature, result_array_flowrate, c='r', marker='o')
    ax.set_xlabel('hardness')
    ax.set_ylabel('temperature')
    ax.set_zlabel('flowrate')
    plt.show()

    plt.hist(result_array_hardness)
    plt.show()
```

[к оглавлению](#NN-Spark)

## Metrics

```python
from math import sqrt
from scipy.stats import kurtosis, skew


def average(lst):
    return sum(lst) / len(lst)


def stdDev(lst):
    sum1 = 0
    mean = average(lst)
    for a in lst:
        sum1 += (a - mean) * (a - mean)
    return sqrt(sum1 / len(lst))


def cov(lst1, lst2):
    sumCov = 0
    mean2 = average(lst1)
    mean3 = average(lst2)
    for i in range(0, len(lst1)):
        sumCov += (lst1[i] - mean2) * (lst2[i] - mean3)
    return sumCov / len(arr2)


def corr(lst1, lst2):
    return cov(lst1, lst2)/(stdDev(lst1)*stdDev(lst2))


if __name__ == '__main__':
    arr = [34, 1, 23, 4, 3, 3, 12, 4, 3, 1]
    arr.sort()
    print(len(arr), ' ', arr)

    print('Standart deviation ', stdDev(arr))
    print('Kurtosis ', kurtosis(arr))
    print('Skew ', skew(arr))

    arr2 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    arr3 = [7, 6, 5, 4, 5, 6, 7, 8, 9, 10]
    print('Covariance ', cov(arr2, arr3))
    print('Corr ', corr(arr2, arr3))
    print(average([1,2,4,5,34,1,32,4,34,2,1,3]))
```

[к оглавлению](#NN-Spark)

## Multiple File

```python
import os

from pyspark.python.pyspark.shell import spark
from pyspark.sql.functions import lit
from pyspark.sql.types import StructType, StructField, IntegerType

if __name__ == '__main__':
    # Создали схему
    schema = StructType([
        StructField('x', IntegerType(), True),
        StructField('y', IntegerType(), True),
        StructField('z', IntegerType(), True)])
    # Считали файлы из системы
    file_list = os.listdir('../resources/HMP_Dataset/Brush_teeth')
    # Отфильтровали только нужные нам файлы из папки
    # file_list_filtered = [s for s in file_list if '_' in s]
    # Создаёт пустой общий датафрейм
    df = None
    # Проходим по категориям (папкам)
    data_files = os.listdir('../resources/HMP_Dataset/Brush_teeth')
    # Проходим по файлам в папках
    for data_file in data_files:
        print(data_file)
        # Создаём временный датафрейм из файлов в папках
        temp_df = spark.read.option("header", "false").option("delimeter", " ") \
            .csv('../resources/HMP_Dataset/Brush_teeth/' + data_file, schema=schema)
        temp_df = temp_df.withColumn("class", lit('Brush_teeth'))
        temp_df = temp_df.withColumn("source", lit(data_file))
        # Добавляем временный датафрейм в общий датафрейм
        if df is None:
            df = temp_df
        else:
            df = df.union(temp_df)

    df.show(100)
```

[к оглавлению](#NN-Spark)

[Заглавная](README.md)