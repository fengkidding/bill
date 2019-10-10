package com.bill.model.vo.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 统计账单参数
 *
 * @author f
 * @date 2019-03-23
 */
@ApiModel(value = "统计账单参数")
public class StatisticsBillParamVO {

    @ApiModelProperty(value = "用户名称", example = "f")
    @NotBlank(message = "用户不能为空！")
    private String userName;

    @ApiModelProperty(value = "开始时间", example = "2019-07-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "开始时间不能为null！")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间", example = "2019-07-30 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "结束时间不能为null！")
    private LocalDateTime endTime;

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
}
