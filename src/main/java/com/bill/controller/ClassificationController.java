package com.bill.controller;

import com.bill.model.conversion.ClassificationConversion;
import com.bill.model.po.auto.Classification;
import com.bill.model.vo.common.PageParamVO;
import com.bill.model.vo.common.ResultVO;
import com.bill.model.vo.param.ClassificationVO;
import com.bill.service.ClassificationSerrvice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 商品分类api
 *
 * @author f
 * @date 2019-10-10
 */
@Api(description = "商品分类api")
@RestController
@RequestMapping(value = "/classification")
public class ClassificationController extends BaseController {

    @Autowired
    private ClassificationSerrvice classificationSerrvice;

    /**
     * 保存商品分类接口
     *
     * @param classificationVo
     * @return
     */
    @ApiOperation(value = "保存商品分类接口")
    @PostMapping(value = "/save_classification")
    public ResultVO saveClassification(@Valid @RequestBody ClassificationVO classificationVo) {
        Classification classification = ClassificationConversion.CLASSIFICATION_CONVERSION.voToDo(classificationVo);
        classification.setId(null);
        classificationSerrvice.saveClassification(classification);
        return super.resultSuccess();
    }

    /**
     * 分页查询商品分类接口
     *
     * @param pageParamVmo
     * @return
     */
    @ApiOperation(value = "分页查询商品分类接口")
    @GetMapping(value = "listClassification")
    public ResultVO<ClassificationVO> listClassification(@Valid PageParamVO pageParamVmo) {
        return super.resultSuccess(classificationSerrvice.listClassification(pageParamVmo));
    }
}
