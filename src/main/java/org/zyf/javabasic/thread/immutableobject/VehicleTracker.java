package org.zyf.javabasic.thread.immutableobject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yanfengzhang
 * @description 更新车辆位置信息
 * @date 2021/1/16  16:47
 */
public class VehicleTracker {
    private Map<String, Location> localMap = new ConcurrentHashMap();

    public void updateLocation(String vehicleId, Location location) {
        localMap.put(vehicleId, location);
    }
}
