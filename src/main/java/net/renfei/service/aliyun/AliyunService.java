package net.renfei.service.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.IClientProfile;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;

/**
 * <p>Title: AliyunService</p>
 * <p>Description: 阿里云服务</p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2021-01-30 15:28
 */
public abstract class AliyunService extends BaseService {
    protected final RenFeiConfig renFeiConfig;
    protected IAcsClient client;

    protected AliyunService(RenFeiConfig renFeiConfig, IClientProfile profile) {
        this.renFeiConfig = renFeiConfig;
        if(profile!=null){
            client = new DefaultAcsClient(profile);
        }
    }
}
