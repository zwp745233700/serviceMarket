package com.serviceMarket.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.serviceMarket.DTO.GoodListDTO;
import com.serviceMarket.DTO.GoodsDTO;
import com.serviceMarket.DTO.ShopDTO;
import com.serviceMarket.Vo.ShopDetailVo;
import com.serviceMarket.Vo.ShowShopListVo;
import com.serviceMarket.common.pojo.PictureResult;
import com.serviceMarket.common.pojo.ServiceMarketResult;
import com.serviceMarket.pojo.Goods;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月23日 下午8:51:02 
* 类说明 
*/

public interface ShopService {
	
	//获取商家的列表
	List<ShowShopListVo> getShopList(Integer marketId);
	
	//获取商家详情
	ShopDetailVo getShopDetail(Integer shopId);
	
	//注册成为商家
	public ServiceMarketResult insertShop(ShopDTO shopDTO);
	
	//修改商店资料
	public boolean updateShopInfo(ShopDTO shopDTO);
	
	//修改商店头像
	public PictureResult updateShopImage(MultipartFile uploadFile,Integer shopId);
	
	//根据用户名查询商家信息
	public List<ShowShopListVo> getShopListByName(ShopDTO shopDTO);
	
	//根据商家类别进行查询
	public List<ShowShopListVo> getShopListByType(ShopDTO shopDTO);
	
	//根据userid查询我的商店
	public ShopDetailVo getShopDetailByUserid(Integer userId);
	
	//插入商品图片
	public PictureResult insertGoodPicture(MultipartFile uploadFile,Integer shopId);
	
	//单个添加商品
	public boolean insertGood(GoodsDTO goodsDTO);

	//批量添加商品
	public boolean insertGoods(GoodListDTO goodListDTO);
	
	//修改商品信息
	public boolean updateGood(GoodsDTO goodsDTO);

	//批量修改商品信息
	boolean updateGoods(GoodListDTO goodListDTO);

	//删除商品
	boolean deleteGoodById(Integer goodId);
	
	//根据商品名查询商品(商店范围)
	public List<Goods> getGoodsByName(String goodsName,Integer shopId);
	
	//根据商品名称查询商品(集市范围)
	public List<ShopDetailVo> getMarGoodsByName(Integer markId,String name);
	
	
	//商品分类(数据库没有相应的表格)
	//添加分类
	//修改分类
	//删除分类
	//根据类别名称查询
}


