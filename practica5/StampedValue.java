//package unam.fc.concurrent.practica5;
/*
 * StampedValue.java
 *
 * Created on January 19, 2006, 9:49 AM
 *
 * From "Multiprocessor Synchronization and Concurrent Data Structures",
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 2006 Elsevier Inc. All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;
/**
 * stamps for atomic MRMW register
 * @author Maurice Herlihy
 */
public class StampedValue<T> {
  /**
   * Counter value used for comparison.
   */
  public long stamp;
  /**
   * Thread id of creator
   */
  public int owner;
  /**
   * Register value.
   */
  public T   value;
    public List<T> values = new ArrayList<T>();
  /**
   * Least value ever.
   */
  public static final StampedValue MIN_VALUE = new StampedValue(null);
  /**
   * Constructor.
   */
  public StampedValue(long stamp, T value) {
    this.stamp = stamp;
    this.owner = ThreadID.get();
    this.value = value;
  }
    public StampedValue(long stamp, T value, List<T> l) {
    this.stamp = stamp;
    this.owner = ThreadID.get();
    this.value = value;
    this.values = l;
  }
  /**
   * Constructor.
   */
  public StampedValue(T value) {
    this.stamp = 0;
    this.value = value;
  }
  public static StampedValue max(StampedValue x, StampedValue y) {
    if (x.stamp > y.stamp) {
      return x;
    } else if (x.owner > y.owner){
      return x;
    } else {
      return y;
    }
  }
}
