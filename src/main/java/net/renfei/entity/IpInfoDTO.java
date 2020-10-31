package net.renfei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Title: IpInfoDTO</p>
 * <p>Description: IP信息数据传输对象</p>
 *
 * @author RenFei
 */
@Data
@ApiModel(value = "IP信息数据传输对象", description = "IP信息数据传输对象")
public class IpInfoDTO {
    @ApiModelProperty(value = "IP地址")
    String ipAddress;
    @ApiModelProperty(value = "国家缩写")
    String countryShort;
    @ApiModelProperty(value = "国家名称")
    String countryLong;
    @ApiModelProperty(value = "省")
    String region;
    @ApiModelProperty(value = "市")
    String city;
    @ApiModelProperty(value = "运营商")
    String isp;
    @ApiModelProperty(value = "纬度")
    Float latitude;
    @ApiModelProperty(value = "经度")
    Float longitude;
    @ApiModelProperty(value = "所属域")
    String domain;
    @ApiModelProperty(value = "邮编")
    String zipcode;
    @ApiModelProperty(value = "网络类型")
    String netspeed;
    @ApiModelProperty(value = "时区")
    String timezone;
    @ApiModelProperty(value = "国际区号")
    String iddcode;
    @ApiModelProperty(value = "国际区号")
    String areacode;
    String weatherstationcode;
    String weatherstationname;
    String mcc;
    String mnc;
    String mobilebrand;
    Float elevation;
    String usagetype;
    String status;
    Boolean delay = false;
}
