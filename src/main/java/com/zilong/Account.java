package com.zilong;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
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
