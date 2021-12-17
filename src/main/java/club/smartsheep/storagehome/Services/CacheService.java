package club.smartsheep.storagehome.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    @Autowired
    private CacheManager cacheManager;

    public void Clear(String name) {
        cacheManager.getCache(name).clear();
    }

    public void ClearAll() {
        cacheManager
            .getCacheNames()
            .stream()
            .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
}
