package com.serviceMarket.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.serviceMarket.DTO.GoodListDTO;
import com.serviceMarket.DTO.GoodsDTO;
import com.serviceMarket.DTO.ShopDTO;
import com.serviceMarket.Vo.ShopDetailVo;
import com.serviceMarket.Vo.ShowShopListVo;
import com.serviceMarket.common.pojo.PictureResult;
import com.serviceMarket.common.pojo.ServiceMarketResult;
import com.serviceMarket.pojo.Goods;
import com.serviceMarket.service.ShopService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月23日 下午8:38:21 
* 类说明 :有关商家的Controller
*/

@Controller
@Api(tags="ShopController",description="商家的相关操作")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	//（分页）通过集市id获取该集市中的商家列表:
	@RequestMapping(value="/GET/shopList")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="获取商家列表",notes="需要集市id")
	//public List<ShowShopListVo> getShopList(@PathVariable(value="id")Integer id){因为@PathVariable不能设置默认值
	public ServiceMarketResult getShopList(@RequestParam(value="marketId",required=true,defaultValue="0")Integer marketId){
		List<ShowShopListVo> list = shopService.getShopList(marketId);
		ServiceMarketResult result = null;
		if(list.size()>0){
			result  = new ServiceMarketResult(list);
		}
		else {
			result  = new ServiceMarketResult(null);
		}
		return result;
	}
	
	//根据商家id获取商家详情：
	@RequestMapping(value="/GET/shopDetail/{shopId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="商家详情",notes="需要商家id")
	public ServiceMarketResult getShopDetail(@PathVariable(value="shopId") Integer shopId){
		
		//获取商家的基本信息以及商家上架商品的基本信息
		ShopDetailVo shopDetailVo=shopService.getShopDetail(shopId);
		
		ServiceMarketResult result = null;
		
		if(shopDetailVo!=null){
			result=new ServiceMarketResult(shopDetailVo);
		}
		else{
			result= new ServiceMarketResult(null);
		}
		return result;
	}

	//注册成为商家
	@RequestMapping(value="/PUT/insertShop")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="开通商家功能",notes="除了商家id,satisfaction,logo,其他都要，json格式")
	public ServiceMarketResult insertShop(@RequestBody ShopDTO shopDTO){
		return shopService.insertShop(shopDTO);
	}
	
	//修改商店头像
	@RequestMapping(method=RequestMethod.POST,value="/POST/updateShopImage/{shopId}",produces = {"application/json"},consumes="multipart/*",headers="content-type=multipart/form-data")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="上传商店头像到服务器接口,会返回图片地址",notes="需要头像和商店id")
	public PictureResult updateShopImage (@ApiParam(value="商家头像") MultipartFile uploadFile,final HttpServletRequest request,@PathVariable(value="shopId") Integer shopId){
		return shopService.updateShopImage(uploadFile, shopId);
	}
	
	//修改商店资料
	@RequestMapping(value="/PUT/updateShopInfo")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="修改商家信息",notes="除了satisfaction和userid,使用shopDTO的json格式")
	public ServiceMarketResult updateShopInfo(@RequestBody ShopDTO shopDTO){
		
		boolean flag=shopService.updateShopInfo(shopDTO);
			return ServiceMarketResult.ok(flag);
	}
	
	//根据商家名模糊查询商家
	@RequestMapping(value="/GET/getShopListByName/{markId}/{sname}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="根据商家名模糊查询",notes="需要商家名sname,markId")
	public ServiceMarketResult getShopListByName(@PathVariable(value="markId") Integer markId,@PathVariable(value="sname") String sname){
		try {
			sname = new String(sname.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ShopDTO shopDTO=new ShopDTO();
		shopDTO.setMarketId(markId);
		shopDTO.setName(sname);
		
		List<ShowShopListVo> shopListVo=shopService.getShopListByName(shopDTO);
		ServiceMarketResult result=null;
		
		result=new ServiceMarketResult(shopListVo);
		return result;
	}
	
	//根据商家类别进行查询
	@RequestMapping(value="/GET/getShopListByType/{markId}/{type}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="根据商家类别查询",notes="需要type,marketId")
	public ServiceMarketResult getShopListByType(@PathVariable(value="markId") Integer markId,@PathVariable(value="type") String type){
		try {
			type = new String(type.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ShopDTO shopDTO=new ShopDTO();
		shopDTO.setMarketId(markId);
		shopDTO.setType(type);
		
		List<ShowShopListVo> shopListVo=shopService.getShopListByType(shopDTO);
		ServiceMarketResult result=null;
		
		result=new ServiceMarketResult(shopListVo);
		
		return result;
	}
	
	
	//根据userid查询我的商店
	@RequestMapping(value="/GET/getMyShopDetail/{userId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="查询我的商店",notes="需要用户的userId")
	public ServiceMarketResult getMyShopDetail(@PathVariable(value="userId") Integer userId){
		
		ServiceMarketResult result=null;
		
		ShopDetailVo shopDetailVo = shopService.getShopDetailByUserid(userId);
		
		result=new ServiceMarketResult(shopDetailVo);
		return result;
	}

	//上传商品图片
	@RequestMapping(method=RequestMethod.POST,value="/POST/insertGoodPic/{shopId}",produces = {"application/json"},consumes="multipart/*",headers="content-type=multipart/form-data")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="上传商品图片到服务器接口,会返回图片地址",notes="需要商品图片和商店id")
	public PictureResult insertGoodPic(@ApiParam(value="商品图片") MultipartFile uploadFile,final HttpServletRequest request,@PathVariable(value="shopId") Integer shopId){

		return shopService.insertGoodPicture(uploadFile, shopId);
	}
	
	//单个添加商品(包括商品图片)
	@RequestMapping(value="/POST/insertGood")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="单个添加商品",notes="除了商品id,其他都要,json格式")
	public ServiceMarketResult insertGood(@RequestBody GoodsDTO goodsDTO){
		boolean flag=shopService.insertGood(goodsDTO);
		return ServiceMarketResult.ok(flag);
	}
	
	//批量添加商品
	@RequestMapping(value="/POST/insertGoods")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="批量添加商品",notes="除了商品id,其他都要,json格式")
	public ServiceMarketResult insertGoods(@RequestBody GoodListDTO goodListDTO){
		boolean flag=shopService.insertGoods(goodListDTO);
		return ServiceMarketResult.ok(flag);
	}
	
	//单个修改商品信息
	@RequestMapping(value="/PUT/updateGood")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="单个修改商品",notes="除了商店id,所有字段都要,json格式")
	public ServiceMarketResult updateGood(@RequestBody GoodsDTO goodsDTO){
		
		boolean flag=shopService.updateGood(goodsDTO);
		return ServiceMarketResult.ok(flag);
	}
	
	//批量修改商品信息
	@RequestMapping(value="/PUT/updateGoods")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="批量修改商品",notes="除了shopid,其他都要,json格式")
	public ServiceMarketResult updateGoods(@RequestBody GoodListDTO goodListDTO){
		boolean flag=shopService.updateGoods(goodListDTO);
		return ServiceMarketResult.ok(flag);
	}
	
	//删除商品
	@RequestMapping(value="/DELETE/deleteGoodById/{goodId}")
	@ResponseBody
	@ApiOperation(httpMethod="DELETE",value="根据id删除商品",notes="需要商品id")
	public ServiceMarketResult deleteGoodById(@PathVariable(value="goodId") Integer goodId){
		boolean flag=shopService.deleteGoodById(goodId);
		return ServiceMarketResult.ok(flag);
	}
	
	//根据商品名查询商品
	@RequestMapping(value="/GET/getGoodsByName/{shopId}/{goodsName}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="根据商品名查询商品(商店范围)",notes="需要商品商店ID和商品名字")
	public ServiceMarketResult getGoodsByName(@PathVariable(value="goodsName") String goodsName,@PathVariable(value="shopId")Integer shopId){
		
		try {
			goodsName = new String(goodsName.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		List<Goods> list=shopService.getGoodsByName(goodsName, shopId);
		return ServiceMarketResult.ok(list);
	}
	
	//根据商品名称查询商品(集市范围)
	@RequestMapping(value="/GET/getShopAndGoodByName/{markId}/{sname}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="根据商品名称查询商品(集市范围)",notes="需要商品集市ID和name")
	public ServiceMarketResult getShopAndGoodByName(@PathVariable(value="sname") String sname,@PathVariable(value="markId")Integer markId){
		
		try {
			sname = new String(sname.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		List<ShopDetailVo> list=shopService.getMarGoodsByName(markId,sname);

		return ServiceMarketResult.ok(list);
	}
	
	
	
	//商品分类(数据库没有相应的表)
	//添加分类
	//修改分类
	//删除分类
	//根据类别名称查询
}
