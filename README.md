<h1 align="center">E-Commerce Customer Segmentation on Spark 🛒</h1>
<p align="center">
  <b>A distributed 4-stage data pipeline for customer segmentation, built in Scala on Apache Spark running across Google Cloud Dataproc and HDFS.</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Scala-DC322F?style=flat&logo=scala&logoColor=white">
  <img src="https://img.shields.io/badge/Apache_Spark-E25A1C?style=flat&logo=apachespark&logoColor=white">
  <img src="https://img.shields.io/badge/HDFS-FFB300?style=flat&logo=apachehadoop&logoColor=white">
  <img src="https://img.shields.io/badge/GCP_Dataproc-4285F4?style=flat&logo=googlecloud&logoColor=white">
</p>

---

## What it does

A distributed Big Data pipeline that processes raw e-commerce transaction data through four sequential stages, ultimately producing customer segmentation analytics. The pipeline is built as a set of standalone Scala/Spark applications, each responsible for a single stage in the ETL → analytics flow.

---

## The four stages

| Stage | App | Purpose |
|---|---|---|
| **1. Ingestion** | `DataIngestionApp.scala` | Read raw CSV from HDFS, parse into a format suitable for distributed analysis |
| **2. ETL** | `ETLApp.scala` | Clean and transform the ingested data; prepare specific columns for analysis |
| **3. Profiling** | `DataProfilingApp.scala` | Profile data characteristics; produce basic statistical summaries |
| **4. Analytics** | `AnalyticsApp.scala` | Run customer segmentation analytics on the cleaned dataset |

Each stage writes its output back to HDFS, making the pipeline composable — you can re-run any stage independently against intermediate outputs.

---

## Tech stack

| Layer | Technology |
|---|---|
| Language | Scala |
| Compute | Apache Spark |
| Storage | HDFS (Hadoop Distributed File System) |
| Cluster | Google Cloud Dataproc |
| Dataset | E-commerce transaction data (`official_ecommerce_data.csv`) |

---

## What's interesting about this build

**Each stage is independently runnable.** Rather than one monolithic Spark job, the pipeline is split into four standalone applications. This means you can re-run profiling after a data refresh without re-running ingestion, or swap in a new analytics module without touching ETL.

**Spark shell on Dataproc.** The pipeline is designed to be loaded directly into a `spark-shell` session running on a Dataproc cluster, which keeps the development loop fast — you can iterate on Scala scripts and re-load them without rebuilding a JAR.

**HDFS as the medium.** All intermediate state lives in HDFS at `/user/ka2653_nyu_edu/output/`, so the pipeline is reproducible across cluster restarts and team members can pick up any stage's output independently.

---

## Project structure

```
/user/ka2653_nyu_edu/                  ← HDFS home directory
├── official_ecommerce_data.csv        ← raw input
└── output/                            ← stage outputs
    ├── DataIngestionApp/
    ├── ETLApp/
    ├── DataProfilingApp/
    └── AnalyticsApp/
```

---

## Run it on Dataproc

```bash
# 1. Start the Spark shell on your Dataproc cluster
spark-shell --deploy-mode client

# 2. Load and run any stage
scala> :load DataIngestionApp.scala
scala> DataIngestionApp.main(Array.empty[String])

scala> :load ETLApp.scala
scala> ETLApp.main(Array.empty[String])

scala> :load DataProfilingApp.scala
scala> DataProfilingApp.main(Array.empty[String])

scala> :load AnalyticsApp.scala
scala> AnalyticsApp.main(Array.empty[String])
```

Output appears on-screen and is also persisted to HDFS for downstream consumption.

---

## Built for

NYU — Big Data Course Final Project
