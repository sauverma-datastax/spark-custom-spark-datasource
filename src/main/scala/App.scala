import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object app {
  def main(args: Array[String]) {
    val config = new SparkConf().setAppName("testing provider").setMaster("local[1]")
    val spark = SparkSession.builder.config(config).getOrCreate()

    val df = spark
      .read
      .format("com.scds")
      .load("src/main/resources")

    println("Custom schema")
    df.printSchema()

    println("Access via DF api")
    df.show()

    println("Access via SQL api")
    df.createOrReplaceTempView("users")
    spark.sql("select name from users").show()
  }
}
