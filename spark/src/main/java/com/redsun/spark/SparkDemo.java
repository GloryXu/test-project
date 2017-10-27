package com.redsun.spark;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.File;
import java.util.Arrays;

/**
 * Created by DELL on 2017/7/9.
 */
public class SparkDemo {
    private static JavaSparkContext javaSparkContext = null;

    public static void init(String appName, String master) {
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
        javaSparkContext = new JavaSparkContext(conf);
    }

    @SuppressWarnings("serial")
    private static void wordCount(String filePath, String fileDir) {
        FileUtils.deleteQuietly(new File(fileDir));

        JavaRDD<String> file = javaSparkContext.textFile(filePath);

        JavaRDD<String> words = file.flatMap((FlatMapFunction<String, String>) s -> Arrays.asList(s.split(" ")).iterator());

        JavaPairRDD<String, Integer> pairs = words.mapToPair((PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1));

        JavaPairRDD<String, Integer> counts = pairs.reduceByKey((Function2<Integer, Integer, Integer>) (a, b) -> a + b);

        counts.saveAsTextFile(fileDir);
    }

    @SuppressWarnings("serial")
    private static void errorCount(String filePath) {
        JavaRDD<String> file = javaSparkContext.textFile(filePath);

        JavaRDD<String> errors = file.filter((Function<String, Boolean>) s -> s.contains("ERROR"));

        errors.count();

        errors.filter((Function<String, Boolean>) s -> s.contains("ORACLE")).count();

        errors.filter((Function<String, Boolean>) s -> s.contains("ORACLE")).collect();
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Please provide the input file full path and output dir as argument");
            System.exit(0);
        }

        SparkDemo.init("redsun.spark", "local");

        SparkDemo.wordCount(args[0], args[1]);

        SparkDemo.errorCount(args[0]);
    }
}
