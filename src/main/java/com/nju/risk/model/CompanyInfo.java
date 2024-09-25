package com.nju.risk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInfo {
    // 通讯地址
    private String address;
    // 位置信息 - 经度
    private Double longitude;
    // 位置信息 - 纬度
    private Double latitude;
    // 占地面积
    private Integer area;
    // 行业类型
    private String industryType;
    // 企业Q值（环境风险物质数量与其临界量比）
    private Double qValue;
}