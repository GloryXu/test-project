package com.redsun.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by DELL on 2017/7/21.
 */
public class WordCountDemo {

    public static void main(String[] args) {
        JavaSparkContext javaSparkContext = init();
        JavaRDD<String> textFile = javaSparkContext.textFile("../../../testlog");
        JavaPairRDD<String, Integer> counts = textFile
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b);
        counts.saveAsTextFile("../../../output/textOutput");
    }

    private static JavaSparkContext init() {
        SparkConf conf = new SparkConf().setAppName("WordCountDemo").setMaster("test");
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        return javaSparkContext;
    }
}
