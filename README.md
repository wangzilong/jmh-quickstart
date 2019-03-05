# JMH - [Java Microbenchmark Harness](http://tutorials.jenkov.com/java-performance/jmh.html)

## What
JMH is short of Java Microbenchmark Harness.
JMH is a toolkit that helps you implement Java microbenchmarks correctly. 

## Why
It is hard to write a benchmark that correctly measure performance of a small part of a large application.

## How
Maven pom.xml
```
<dependency>
  <groupId>org.openjdk.jmh</groupId>
  <artifactId>jmh-core</artifactId>
  <version>1.19</version>
</dependency>
<dependency>
  <groupId>org.openjdk.jmh</groupId>
  <artifactId>jmh-generator-annprocess</artifactId>
  <version>1.19</version>
</dependency>
```

Runner Code:
```
public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
```

Test Code:
```
public class Account {

    @Param({ "100", "1000" })
    public int iterations;

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 1)// only one warm-up iterations will suffice
    @Fork(value = 4, //the value parameter controls how many times the benchmark will be executed
            warmups = 5) //the warmup parameter controls how many times a benchmark will dry run before results are collected
    public int getName() {
        // TODO
        int odd=0;
        for (int i = 0; i < iterations; i++) {
            if (i % 2 == 0)
                odd++;
        }
        return odd;
    }
}
```

## Result:
```
Result "com.zilong.Account.getName":
  10710188.292 ±(99.9%) 130758.283 ops/s [Average]
  (min, avg, max) = (9613684.835, 10710188.292, 11266707.902), stdev = 342171.661
  CI (99.9%): [10579430.009, 10840946.575] (assumes normal distribution)
```
```
Result "com.zilong.Account.getName":
  1263885.040 ±(99.9%) 22998.912 ops/s [Average]
  (min, avg, max) = (1032144.766, 1263885.040, 1310252.458), stdev = 60184.149
  CI (99.9%): [1240886.128, 1286883.952] (assumes normal distribution)
```
```
Benchmark        (iterations)   Mode  Cnt         Score        Error  Units
Account.getName           100  thrpt   80  10710188.292 ± 130758.283  ops/s
Account.getName          1000  thrpt   80   1263885.040 ±  22998.912  ops/s
```
