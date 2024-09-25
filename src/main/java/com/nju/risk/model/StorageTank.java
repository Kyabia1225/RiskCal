package com.nju.risk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageTank {
    // 储罐个数
    private Integer number;
    // 储罐区面积
    private Integer area;
    // 储罐名称
    private String name;
    // 储罐位置 - 经度
    private Double tankLongitude;
    // 储罐位置 - 纬度
    private Double tankLatitude;
    // 储罐形状
    private String shape;
    // 储罐形式
    private String form;
    // 浮顶密度
    private Double floatingRoofDensity;
    // 浮顶厚度
    private Double floatingRoofThickness;
    // 浮顶重量
    private Double floatingRoofWeight;
    // 排水管道直径
    private Double drainagePipeDiameter;
    // 排水系统效率
    private Double drainageSystemEfficiency;
    // 安装形式
    private Integer installationType;
    // 支架高度, 0表示无支架
    private Double supportHeight;
    // 储罐材质
    private String material;
    // 储罐壳体重量
    private Double shellWeight;
    // 直径
    private Double diameter;
    // 高度
    private Double height;
    // 容积
    private Double volume;
    // 工作压力, true表示为常压
    private boolean workingAsConstantPressure;
    // 储存物质名称
    private String storedSubstanceName;
    // 储存物质密度
    private Double storedSubstanceDensity;
    // 物质状态, 1，2 or 3
    private Integer substanceState;
    // 气体密度
    private Double substanceDensity;
    // 日常最大/最小填充水平， todo：？
    private Double maxFillingLevel;
    private Double minFillingLevel;


}
