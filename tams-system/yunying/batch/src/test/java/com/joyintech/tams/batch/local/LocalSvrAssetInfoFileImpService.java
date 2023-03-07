package com.joyintech.tams.batch.local;

import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.svreport.interf.chb.assetinfo.service.SvrAssetInfoFileImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component
public class LocalSvrAssetInfoFileImpService extends SvrAssetInfoFileImpService {

    @Autowired
    private LocalInterfaceFileLoader interfaceFileLoader;

    @Override
    public List<InterfFile> queryAndDownloadFiles(String currentDate, List<String> containsFile) {
        return interfaceFileLoader.load(currentDate, "CHB_ASSET_INFO");
    }

}
