package com.bill.model.vo.param;

import com.bill.model.vo.common.PageParamVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 分页查询订单列表参数
 *
 * @author f
 * @date 2019-03-17
 */
@ApiModel(value = "分页查询订单列表参数")
public class UserPageParamVO extends PageParamVO {

    @ApiModelProperty(value = "用户名称", example = "f")
    @NotBlank(message = "用户名称不能为空！")
    private String userName;

    @ApiModelProperty(value = "开始时间", example = "2019-04-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "开始时间不能为null！")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间", example = "2019-04-20 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "结束时间不能为null！")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "分类id")
    private Integer classificationId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }
}
