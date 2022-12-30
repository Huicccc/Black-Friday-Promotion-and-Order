package org.ecommerce.api.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Twitter Distributed id Generator - Snow Flake
 **/
@Component
public class SnowFlake {

  private final static long START_STMP = 1480166465631L;


  private final static long SEQUENCE_BIT = 12; //序列号占用的位数
  private final static long MACHINE_BIT = 5;   //机器标识占用的位数
  private final static long DATACENTER_BIT = 5;//数据中心占用的位数


  private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
  private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
  private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);


  private final static long MACHINE_LEFT = SEQUENCE_BIT;
  private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
  private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

  private long datacenterId;
  private long machineId;
  private long sequence = 0L;
  private long lastStmp = -1L;

  /**
   * @Component
   * Attention, SpringBoot will create Bean first (call constructor method first), then inject properties
   * We have 2 solutions:
   * 1. using @Value on property and remove set/use this property in constructor method
   * 2. using @Value on parameters in constructor method, then set value to property
   */
  public SnowFlake(@Value("${order.data-center}")long datacenterId,@Value("${order.machine-num}") long machineId) {
    if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
      throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
    }
    if (machineId > MAX_MACHINE_NUM || machineId < 0) {
      throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
    }
    this.datacenterId = datacenterId;
    this.machineId = machineId;
  }

  public synchronized long nextId() {
    long currStmp = getNewstmp();
    if (currStmp < lastStmp) {
      throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
    }

    if (currStmp == lastStmp) {
      sequence = (sequence + 1) & MAX_SEQUENCE;
      if (sequence == 0L) {
        currStmp = getNextMill();
      }
    } else {
      sequence = 0L;
    }

    lastStmp = currStmp;

    return (currStmp - START_STMP) << TIMESTMP_LEFT
        | datacenterId << DATACENTER_LEFT
        | machineId << MACHINE_LEFT
        | sequence;
  }

  private long getNextMill() {
    long mill = getNewstmp();
    while (mill <= lastStmp) {
      mill = getNewstmp();
    }
    return mill;
  }

  private long getNewstmp() {
    return System.currentTimeMillis();
  }

  public static void main(String[] args) {
    SnowFlake snowFlake = new SnowFlake(2, 1);

    long start = System.currentTimeMillis();
    for (int i = 0; i < 1000000; i++) {
      System.out.println(snowFlake.nextId());
    }

    System.out.println("Total time：" + (System.currentTimeMillis() - start));
  }
}


