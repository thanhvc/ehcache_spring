/*
 * Copyright (C) 2003-2015 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.danielwind.effcaching.recipe3.ehcache;

import net.danielwind.effcaching.recipe3.domain.Employee;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 12, 2015  
 */
public final class CacheDelegate {

  private static final Logger log = Logger.getLogger(CacheDelegate.class);
  private static final String EHCACHE_CONFIG = "src/main/resources/ehcache.xml";
  private static final String CACHE_NAME = "employeeCache";
  
  private CacheManager manager;
  private Ehcache cache;
  
  /**
   * Default Constructor
   */
  public CacheDelegate() {
    log.info("--- Initializing Cache Delegate Class... ---");
    manager = new CacheManager(EHCACHE_CONFIG);
    cache = manager.getCache(CACHE_NAME);
  }
  
  /**
   * Method that removes a certain object (by key) from cache.
   * Please note that this method will not log until the object
   * gets evicted from data store.
   * @param key The cache object key
   */
  public void removeElementFromCache(Object key) {
    if(!cache.remove(key)){
      throw new RuntimeException("Could Not remove key-value from Cache");
    }
  }
  
  /**
   * Method that adds a new Car object in Cache
   * @param car Domain object instance
   */
  public void addElementToCache(Employee emp) {
    cache.put(new Element(emp.getId(), emp));
  }
  
  /**
   * Method that removes all Elements in Cache
   */
  public void removeAllElementsInCache() {
    cache.removeAll();
  }
  
  /**
   * Simulates an exception during a cache method
   * execution. Invalid key.
   */
  public void generateException() {
    cache.getCacheExceptionHandler().onException(cache, 100, new CacheException());
  }
}
