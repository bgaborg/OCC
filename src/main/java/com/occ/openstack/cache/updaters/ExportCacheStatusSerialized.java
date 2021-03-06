package com.occ.openstack.cache.updaters;

import com.occ.helper.SerializeHelper;
import com.occ.infinispan.InfinispanCacheWrapper;
import org.apache.log4j.Logger;
import org.infinispan.Cache;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * ConsoleUpdater
 */
public class ExportCacheStatusSerialized extends CacheUpdater {
    private static final Logger logger = Logger.getLogger(ExportCacheStatusSerialized.class);
    public static final String FILENAME_PREFIX = "serialized-cache-status-";
    public static final String FILE_EXTENSION = ".dat";

    @Override
    public void run() {

        try {
            Cache<String, Object> cache = InfinispanCacheWrapper.getCache();
            Map<String, Object> stringObjectMap = new HashMap<>();
            for (String key : cache.keySet()) {
                stringObjectMap.put(key, cache.get(key));
            }

            //create a temporary file
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            SerializeHelper<Map<String, Object>> mapSerializeHelper = new SerializeHelper<>();
            mapSerializeHelper.writeObject(FILENAME_PREFIX + timeLog + FILE_EXTENSION, stringObjectMap);

            logger.info(this.getClass().getName() + " refreshed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
