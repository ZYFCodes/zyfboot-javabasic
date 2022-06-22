package org.zyf.javabasic.designpatterns.template.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/24  21:32
 */
@Controller
@RequestMapping("/org/zyf")
@Slf4j
public class ZYFBizTemplateController {

    @RequestMapping("r/listSubBrand")
    @ResponseBody
    public BizResponse<ListSubBrandResult> listSubBrand(ListSubBrandCommand command, final BindingResult br)
            throws Exception {
        return new BizTemplateWithSpringValidator<ListSubBrandCommand, ListSubBrandResult>(command,
                new ListSubBrandResult(), br) {

            @Override
            public void doBefore() {

            }

            @Override
            public void doBiz() throws Exception {
                List<ListSubBrandResult.CRUXStandardProductLibBrand> brandList = Lists.newArrayList();
//                CRUXListSubBrandCommand cruxListSubBrandCommand = new CRUXListSubBrandCommand();
//                cruxListSubBrandCommand.setParentId(command.getParentId());
//                cruxListSubBrandCommand.setOpMid(user.getId());
//                CRUXStandardProductLibListBrandResult listBrandResult = cruxStandardProductLibBrandThriftService.listSubBrand(cruxListSubBrandCommand);
//                if (null != listBrandResult && CollectionUtils.isNotEmpty(listBrandResult.getBrandList())) {
//                    brandList = listBrandResult.getBrandList();
//                }
                result.convert2CruxBrand(brandList);
            }
        }.execute();
    }
}
