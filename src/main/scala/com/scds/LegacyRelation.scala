package com.scds

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.sources.{BaseRelation, TableScan}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

class LegacyRelation(location: String, userSchema: StructType)
  (@transient val sqlContext: SQLContext)
  extends BaseRelation with TableScan
    with Serializable {

  override def schema: StructType = {
    if (this.userSchema != null) {
      return this.userSchema
    }
    else {
      return StructType(Seq(StructField("name", StringType, true),
        StructField("age", IntegerType, true)))
    }
  }

  override def buildScan(): RDD[Row] = {
    val rdd = sqlContext
      .sparkContext
      .wholeTextFiles(location)
      .map(x => x._2)

    val rows = rdd.map(file => {
      val lines = file.split("\n")
      Row.fromSeq(Seq(lines(0), Integer.parseInt(lines(1))))
    })

    rows
  }
}

