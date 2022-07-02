package com.slickmorty.asankar.data;


import java.sql.Date;

public class Work {

  //Variables
  private int id;

  private OP op = null;
  private String opName;

  private Product product = null;
  private int productID;

  private int workerID;

  private Date planStartDate;
  private long longplanStartDate;

  private Date planFinishDate;
  private long longPlanFinishDate;

  private Date workerStartDate;
  private long longWorkerStartDate;

  private Date workerFinishDate;
  private long longWorkerFinishDate;

  private Date finishDate ;
  private long longFinishDate ;



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

  //Functions

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

  public void setPlanStartDate(Date planStartDate) {
    longplanStartDate = planStartDate.getTime();
    this.planStartDate = planStartDate;
  }
  public void setPlanFinishDate(Date planFinishDate) {
    longPlanFinishDate = planFinishDate.getTime();
    this.planFinishDate = planFinishDate;
  }
  public void setWorkerStartDate(Date workerStartDate) {
    longWorkerStartDate = workerStartDate.getTime();
    this.workerStartDate = workerStartDate;
  }
  public void setFinishDate(Date finishDate) {
    longFinishDate = finishDate.getTime();
    this.finishDate = finishDate;
  }
  public void setWorkerFinishDate(Date workerFinishDate) {
    longWorkerFinishDate = workerFinishDate.getTime();
    this.workerFinishDate = workerFinishDate;
  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public OP getOp() {
    return op;
  }

  public void setOp(OP op) {
    this.op = op;
  }

  public String getOpName() {
    return opName;
  }

  public void setOpName(String opName) {
    this.opName = opName;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
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

  public Date getPlanFinishDate() {
    return planFinishDate;
  }

  public Date getWorkerStartDate() {
    return workerStartDate;
  }

  public Date getFinishDate() {
    return finishDate;
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

  public int getWorkerID() {
    return workerID;
  }

  public void setWorkerID(int workerID) {
    this.workerID = workerID;
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
