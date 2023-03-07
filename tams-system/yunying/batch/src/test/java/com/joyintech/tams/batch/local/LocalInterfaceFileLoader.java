package com.joyintech.tams.batch.local;

import com.joyintech.tams.common.InterfFile;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class LocalInterfaceFileLoader {

    private static final Logger logger = LoggerFactory.getLogger(LocalInterfaceFileLoader.class);

    private String filepath;

    public LocalInterfaceFileLoader(String filepath) {
        this.filepath = filepath;
    }

    public List<InterfFile> load(String currentDay, String interfaceName) {
        File path = new File(filepath, currentDay);

        if (!path.exists()) {
            path = new File(filepath);
        }

        // 不存在
        if (!path.exists()) {
            logger.warn("path {} not exists.", path.getAbsolutePath());
            return Collections.emptyList();
        }

        // 非目录
        if (!path.isDirectory()) {
            logger.warn("path {} not directory.", path.getAbsolutePath());
            return Collections.emptyList();
        }

        File[] files = path.listFiles(filter -> filter.getName().startsWith(interfaceName));
        List<InterfFile> results = Lists.newArrayList();
        for (File file : files) {
            InterfFile result = new InterfFile();
            result.setFileType("file");
            result.setFile(file);
            result.setRemotePath(file.getAbsolutePath());
            results.add(result);
        }

        return results;
    }

}
