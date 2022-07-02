package com.kara.webserver.datatypes;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Work {

  //Variables
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  private String opName;

  private int productID;

  private int workerID;

  private Date planStartDate;
  private long longplanStartDate;


  private Date planFinishDate;
  private long longPlanFinishDate;

  private Date workerStartDate = null;
  private long longWorkerStartDate=0;

  private Date workerFinishDate = null;
  private long longWorkerFinishDate=0;

  private Date finishDate = null;
  private long longFinishDate =0;

  private boolean qcCheck =false;
  private boolean workerCheck = false;
  private double progress = 0;

  private String planDescription = null;
  private String qcDescription = null;
  private String workerDescription = null;

  //Constructor
  public Work( String opName, int productID, int workerID, Date planStartDate, Date planFinishDate) {
    this.opName = opName;
    this.workerID = workerID;
    this.productID = productID;
    this.planStartDate = planStartDate;
    this.planFinishDate = planFinishDate;
    longplanStartDate = planStartDate.getTime();
    longPlanFinishDate = planFinishDate.getTime();
  }

  public Work() {

  }

  //Functions
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOpName() {
    return opName;
  }

  public void setOpName(String opName) {
    this.opName = opName;
  }


  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public Date getPlanStartDate() {
    return planStartDate;
  }

  public void setPlanStartDate(Date planStartDate) {
    longplanStartDate = planStartDate.getTime();
    this.planStartDate = planStartDate;
  }

  public Date getPlanFinishDate() {
    return planFinishDate;
  }

  public void setPlanFinishDate(Date planFinishDate) {
    longPlanFinishDate = planFinishDate.getTime();
    this.planFinishDate = planFinishDate;
  }

  public Date getWorkerStartDate() {
    return workerStartDate;
  }

  public void setWorkerStartDate(Date workerStartDate) {
    longWorkerStartDate = workerStartDate.getTime();
    this.workerStartDate = workerStartDate;
  }

  public Date getFinishDate() {
    return finishDate;
  }

  public void setFinishDate(Date finishDate) {
    longFinishDate = finishDate.getTime();
    this.finishDate = finishDate;
  }

  public boolean isQcCheck() {
    return qcCheck;
  }

  public void setQcCheck(boolean qcCheck) {
    this.qcCheck = qcCheck;
  }

  public String getPlanDescription() {
    return planDescription;
  }

  public void setPlanDescription(String planDescription) {
    this.planDescription = planDescription;
  }

  public String getQcDescription() {
    return qcDescription;
  }

  public void setQcDescription(String qcDescription) {
    this.qcDescription = qcDescription;
  }

  public String getWorkerDescription() {
    return workerDescription;
  }

  public void setWorkerDescription(String workerDescription) {
    this.workerDescription = workerDescription;
  }

  public Date getWorkerFinishDate() {
    return workerFinishDate;
  }

  public void setWorkerFinishDate(Date workerFinishDate) {
    longWorkerFinishDate = workerFinishDate.getTime();
    this.workerFinishDate = workerFinishDate;
  }

  public int getWorkerID() {
    return workerID;
  }

  public void setWorkerID(int workerID) {
    this.workerID = workerID;
  }
  public long getLongplanStartDate() {
    return longplanStartDate;
  }

  public long getLongPlanFinishDate() {
    return longPlanFinishDate;
  }

  public long getLongWorkerStartDate() {
    return longWorkerStartDate;
  }

  public long getLongWorkerFinishDate() {
    return longWorkerFinishDate;
  }

  public long getLongFinishDate() {
    return longFinishDate;
  }

  public double getProgress() {
    return progress;
  }

  public void setProgress(double progress) {
    if (progress <= 100)
      this.progress = progress;
    else
      this.progress = 0;

  }

  public boolean isWorkerCheck() {
    return workerCheck;
  }

  public void setWorkerCheck(boolean workerCheck) {
    this.workerCheck = workerCheck;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Work work = (Work) o;

    return getId() == work.getId();
  }

  @Override
  public int hashCode() {
    return getId();
  }
}