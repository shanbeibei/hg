package com.shanbeibei.hgshop.controller;
/**
 * 首页
 * @author lenovo
 *
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.pojo.Sku;
import com.shanbeibei.hgshop.pojo.Spu;
import com.shanbeibei.hgshop.pojo.SpuVo;
import com.shanbeibei.hgshop.service.GoodsService;
@Controller
public class IndexController {
	@Reference
	GoodsService goodsService;
	/**
	 * 进入首页
	 * @param request
	 * @return
	 */
	@RequestMapping({"/","index"})
	public String index(HttpServletRequest request,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "0") int catId) {
		PageInfo<Spu> listSpu = goodsService.listSpu(page, new SpuVo());
		request.setAttribute("pageInfo", listSpu);
		
		return "index";
		
	}
	/** 
	 * 显示商品详情
	 * @param request
	 * @param spuId
	 * @return
	 */
	@RequestMapping("detail")
	public String detail(HttpServletRequest request,int spuId) {
		//spu
		Spu spu = goodsService.getSpu(spuId);
		//sku
		List<Sku> skuList = goodsService.listSkuBySpu(spuId);
		request.setAttribute("spu", spu);
		request.setAttribute("skus", skuList);
		return "detail";
		}
}
