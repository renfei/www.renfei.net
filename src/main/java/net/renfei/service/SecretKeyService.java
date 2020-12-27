package net.renfei.service;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.entity.ReportPublicKeyVO;
import net.renfei.exceptions.ServiceException;
import net.renfei.repository.SecretKeyDOMapper;
import net.renfei.repository.entity.SecretKeyDOExample;
import net.renfei.repository.entity.SecretKeyDOWithBLOBs;
import net.renfei.sdk.utils.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>Title: SecretKeyService</p>
 * <p>Description: 秘钥服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
public class SecretKeyService extends BaseService {
    private final SecretKeyDOMapper secretKeyDOMapper;

    public SecretKeyService(SecretKeyDOMapper secretKeyDOMapper) {
        this.secretKeyDOMapper = secretKeyDOMapper;
    }

    /**
     * 获取服务器公钥
     */
    public Map<Integer, String> secretKey() {
        Map<Integer, String> map = RSAUtils.genKeyPair(2048);
        if (BeanUtils.isEmpty(map)) {
            return null;
        }
        //保存
        String uuid = UUID.randomUUID().toString();
        SecretKeyDOWithBLOBs secretKeyDO = Builder.of(SecretKeyDOWithBLOBs::new)
                .with(SecretKeyDOWithBLOBs::setUuid, uuid)
                .with(SecretKeyDOWithBLOBs::setCreateTime, new Date())
                .with(SecretKeyDOWithBLOBs::setPrivateKey, map.get(1))
                .with(SecretKeyDOWithBLOBs::setPublicKey, map.get(0))
                .build();
        secretKeyDOMapper.insertSelective(secretKeyDO);
        map.put(1, uuid);
        return map;
    }

    /**
     * 上报客户端公钥，并下发AES秘钥
     */
    public Map<String, String> setSecretKey(ReportPublicKeyVO reportPublicKeyVO) throws ServiceException {
        SecretKeyDOExample secretKeyDOExample = new SecretKeyDOExample();
        secretKeyDOExample.createCriteria()
                .andUuidEqualTo(reportPublicKeyVO.getSecretKeyId());
        SecretKeyDOWithBLOBs secretKeyDO = ListUtils.getOne(secretKeyDOMapper.selectByExampleWithBLOBs(secretKeyDOExample));
        if (BeanUtils.isEmpty(secretKeyDO)) {
            throw new ServiceException("secretKeyId不正确");
        }
        String clentKey = null;
        try {
            clentKey = RSAUtils.decrypt(reportPublicKeyVO.getPublicKey(), secretKeyDO.getPrivateKey());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ServiceException("publicKey解密失败");
        }
        String aes = StringUtils.getRandomString(16);
        String aesEnc;
        try {
            aesEnc = RSAUtils.encrypt(aes, clentKey.replaceAll("\n", ""));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ServiceException("服务器内部错误，使用RSA客户端公钥加密失败");
        }
        //保存AES
        String uuid = UUID.randomUUID().toString();
        SecretKeyDOWithBLOBs secretKeyDTO = Builder.of(SecretKeyDOWithBLOBs::new)
                .with(SecretKeyDOWithBLOBs::setUuid, uuid)
                .with(SecretKeyDOWithBLOBs::setCreateTime, new Date())
                .with(SecretKeyDOWithBLOBs::setPrivateKey, aes)
                .build();
        secretKeyDOMapper.insertSelective(secretKeyDTO);
        Map<String, String> map = new HashMap<>();
        map.put("keyid", uuid);
        map.put("aeskey", aesEnc);
        return map;
    }

    /**
     * 根据秘钥ID解密AES密文
     */
    public String decrypt(String string, String keyId) {
        SecretKeyDOExample secretKeyExample = new SecretKeyDOExample();
        secretKeyExample.createCriteria()
                .andUuidEqualTo(keyId);
        SecretKeyDOWithBLOBs secretKey = ListUtils.getOne(secretKeyDOMapper.selectByExampleWithBLOBs(secretKeyExample));
        if (BeanUtils.isEmpty(secretKey)) {
            throw new ServiceException("AESKeyId不存在");
        } else {
            try {
                string = AESUtil.decrypt(string, secretKey.getPrivateKey());
            } catch (Exception ex) {
                throw new ServiceException("加密密文解密失败");
            }
            return string;
        }
    }
}
