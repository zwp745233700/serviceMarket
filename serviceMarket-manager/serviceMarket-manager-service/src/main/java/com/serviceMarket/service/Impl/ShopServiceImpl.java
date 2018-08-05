package com.serviceMarket.service.Impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.serviceMarket.DTO.GoodListDTO;
import com.serviceMarket.DTO.GoodsDTO;
import com.serviceMarket.DTO.ShopDTO;
import com.serviceMarket.Vo.GoodVo;
import com.serviceMarket.Vo.ShopDetailVo;
import com.serviceMarket.Vo.ShowShopListVo;
import com.serviceMarket.common.pojo.PictureResult;
import com.serviceMarket.common.pojo.ServiceMarketResult;
import com.serviceMarket.common.util.ExceptionUtil;
import com.serviceMarket.common.util.FtpUtil;
import com.serviceMarket.common.util.IDUtils;
import com.serviceMarket.common.util.SFTPUtil;
import com.serviceMarket.mapper.GoodsMapper;
import com.serviceMarket.mapper.PermissionsMapper;
import com.serviceMarket.mapper.ShopMapper;
import com.serviceMarket.pojo.Goods;
import com.serviceMarket.pojo.GoodsExample;
import com.serviceMarket.pojo.Permissions;
import com.serviceMarket.pojo.PermissionsExample;
import com.serviceMarket.pojo.Shop;
import com.serviceMarket.pojo.ShopExample;
import com.serviceMarket.pojo.ShopExample.Criteria;
import com.serviceMarket.service.ShopService;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月23日 下午8:55:34 
* 类说明 
*/

@Service
public class ShopServiceImpl implements ShopService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private PermissionsMapper permissionsMapper;
	
	//获取商家列表
	@Override
	public List<ShowShopListVo> getShopList(Integer marketId) {
		ShopExample shopExample=new ShopExample();
		Criteria criteria=shopExample.createCriteria();
		criteria.andMarketIdEqualTo(marketId);
		
		List<Shop> shopList=shopMapper.selectByExample(shopExample);
		List<ShowShopListVo> showShopListVoList=new ArrayList<ShowShopListVo>();
		for(Shop shop:shopList){
			ShowShopListVo showShopListVo=new ShowShopListVo();
			showShopListVo.setDescripe(shop.getDes());
			showShopListVo.setName(shop.getName());
			showShopListVo.setPic(shop.getLogo());
			showShopListVo.setSatisfaction(shop.getSatisfaction());
			showShopListVo.setShopId(shop.getId());
			showShopListVo.setType(shop.getType());
			
			showShopListVoList.add(showShopListVo);
		}
		return showShopListVoList;
	}

	//获取商店详情：
	@Override
	public ShopDetailVo getShopDetail(Integer shopId) {
		
		ShopDetailVo shopDetailVo=new ShopDetailVo();
		
		//通过商店ID获取商家基本信息
		Shop shop=shopMapper.selectByPrimaryKey(shopId);
		if(shop!=null){
			shopDetailVo.setAddress(shop.getAddress());
			shopDetailVo.setDes(shop.getDes());
			shopDetailVo.setNotice(shop.getNotice());
			shopDetailVo.setPhone(shop.getPhone());
		}else{
			return null;
		}
		
		//获取商品列表的基本信息：
		List<GoodVo> goodVoList=getGoodList(shopId);
		shopDetailVo.setGoodList(goodVoList);
		
		return shopDetailVo;
	}

	//获取商品列表的基本信息
	private List<GoodVo> getGoodList(Integer shopId){
		GoodsExample goodsExample=new GoodsExample();
		com.serviceMarket.pojo.GoodsExample.Criteria criteria=goodsExample.createCriteria();
		criteria.andShopIdEqualTo(shopId);
		List<Goods> goodsList=goodsMapper.selectByExample(goodsExample);
		
		//赋值
		if(goodsList.size()==0 || goodsList.isEmpty() ){
			return null;
		}else{
			List<GoodVo> goodVoList=new ArrayList<>();
			for(Goods good:goodsList){
				GoodVo goodVo=new GoodVo();
				goodVo.setGoodId(good.getId());
				goodVo.setImg(good.getPicture());
				goodVo.setName(good.getName());
				goodVo.setNumber(good.getNumber());
				goodVo.setPrice(good.getPrice());
				
				goodVoList.add(goodVo);
			}
			return goodVoList;
		}
	}

	//注册成为商家
	@Override
	public ServiceMarketResult insertShop(ShopDTO shopDTO) {
		Shop shop =new Shop();
		
		shop.setName(shopDTO.getName());
		shop.setLogo("");
		shop.setPhone(shopDTO.getPhone());
		shop.setType(shopDTO.getType());
		shop.setAddress(shopDTO.getAddress());
		shop.setNotice(shopDTO.getNotice());
		shop.setMarketId(shopDTO.getMarketId());
		shop.setUserId(shopDTO.getUserId());
		shop.setDes(shopDTO.getDes());
		shop.setSatisfaction("5");//默认5星
		
		try{
			int result=shopMapper.insertSelective(shop);
			
			if(result==1){
				int result2=insertShopPerm(shop.getUserId());
				if(result2==1){
					return ServiceMarketResult.ok("true");
				}else{
					return ServiceMarketResult.ok("false");
				}
			}else{
				return ServiceMarketResult.ok("false");
			}
		}catch(Exception e){
			return ServiceMarketResult.ok("false");
		}
	}
	
	//在权限表中添加商家权限
	private int insertShopPerm(Integer userId){
		//修改权限表
		PermissionsExample example=new PermissionsExample();
		com.serviceMarket.pojo.PermissionsExample.Criteria criteria=example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		
		List<Permissions> list=permissionsMapper.selectByExample(example);
		
		if(!list.isEmpty()){
			Permissions permissions=list.get(0);
			permissions.setIsShop("Y");
			int result=permissionsMapper.updateByPrimaryKeySelective(permissions);
			
			return result;
		}
		return 0;
	}

	//修改商家信息
	@Override
	public boolean updateShopInfo(ShopDTO shopDTO) {
		Shop shop=new Shop();
		
		if(shopDTO.getLogo()!=null){
			shop.setLogo(shopDTO.getLogo());
		}
		
		shop.setId(shopDTO.getId());
		shop.setName(shopDTO.getName());
		shop.setPhone(shopDTO.getPhone());
		shop.setAddress(shopDTO.getAddress());
		shop.setType(shopDTO.getType());
		shop.setNotice(shopDTO.getNotice());
		shop.setDes(shopDTO.getDes());
		shop.setMarketId(shopDTO.getMarketId());
		
		int result=shopMapper.updateByPrimaryKeySelective(shop);
		
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	//上传商店头像
	@Override
	public PictureResult updateShopImage(MultipartFile uploadFile, Integer shopId) {
		//判断上传图片是否为空
		if(null==uploadFile || uploadFile.isEmpty()){
			return PictureResult.error("上传图片为空");
		}
		
		//获取文件拓展名
		String originalFilename=uploadFile.getOriginalFilename();
		String ext=originalFilename.substring(originalFilename.lastIndexOf("."));
		//生成新的文件名
		String imageName=IDUtils.genImageName();
		
		//把图片上传到ftp服务器(图片服务器)
		String filePath="/shop"+shopId+"/head";
		try {
			SFTPUtil sftp = new SFTPUtil(FTP_USERNAME, FTP_PASSWORD, FTP_ADDRESS, FTP_PORT);  
			sftp.login();
			sftp.upload(FTP_BASE_PATH, filePath, imageName+ext, uploadFile.getInputStream());
			sftp.logout();  
			
			/*FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
					FTP_BASE_PATH, filePath, imageName + ext, uploadFile.getInputStream());*/
		} catch (Exception e) {
			return PictureResult.error(ExceptionUtil.getStackTrace(e));
		}
		
		//可以访问到图片的url
		String url=IMAGE_BASE_URL + filePath + "/" + imageName + ext;
		
		return PictureResult.ok(url);
	}

	
	@Override
	public List<ShowShopListVo> getShopListByName(ShopDTO shopDTO) {
		
		//模糊查询
		ShopExample example=new ShopExample();
		Criteria criteria=example.createCriteria();
		criteria.andNameLike("%"+shopDTO.getName()+"%");
		criteria.andMarketIdEqualTo(shopDTO.getMarketId());
		
		List<Shop> shopList = shopMapper.selectByExample(example);
		
		List<ShowShopListVo> showShopListVoList=new ArrayList<ShowShopListVo>();
		for(Shop shop:shopList){
			ShowShopListVo showShopListVo=new ShowShopListVo();
			showShopListVo.setDescripe(shop.getDes());
			showShopListVo.setName(shop.getName());
			showShopListVo.setPic(shop.getLogo());
			showShopListVo.setSatisfaction(shop.getSatisfaction());
			showShopListVo.setShopId(shop.getId());
			showShopListVo.setType(shop.getType());
			
			showShopListVoList.add(showShopListVo);
		}
		return showShopListVoList;
		
	}
	
	//根据类别查询商店
	@Override
	public List<ShowShopListVo> getShopListByType(ShopDTO shopDTO) {
		
		ShopExample example = new ShopExample();
		Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo(shopDTO.getType());
		criteria.andMarketIdEqualTo(shopDTO.getMarketId());
		
		List<Shop> shopList = shopMapper.selectByExample(example);
		
		List<ShowShopListVo> showShopListVoList=new ArrayList<ShowShopListVo>();
		for(Shop shop:shopList){
			ShowShopListVo showShopListVo=new ShowShopListVo();
			showShopListVo.setDescripe(shop.getDes());
			showShopListVo.setName(shop.getName());
			showShopListVo.setPic(shop.getLogo());
			showShopListVo.setSatisfaction(shop.getSatisfaction());
			showShopListVo.setShopId(shop.getId());
			showShopListVo.setType(shop.getType());
			
			showShopListVoList.add(showShopListVo);
		}
		return showShopListVoList;
		
	}
	

	@Override
	public ShopDetailVo getShopDetailByUserid(Integer userId) {
		ShopExample example=new ShopExample();
		Criteria criteria=example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<Shop> shopList=shopMapper.selectByExample(example);
		
		ShopDetailVo shopDetailVo=new ShopDetailVo();
		if(!shopList.isEmpty()){
			Shop shop=shopList.get(0);
			
			shopDetailVo.setAddress(shop.getAddress());
			shopDetailVo.setDes(shop.getDes());
			shopDetailVo.setId(shop.getId());
			shopDetailVo.setLogo(shop.getLogo());
			shopDetailVo.setMarketId(shop.getMarketId());
			shopDetailVo.setName(shop.getName());
			shopDetailVo.setNotice(shop.getNotice());
			shopDetailVo.setPhone(shop.getPhone());
			shopDetailVo.setType(shop.getType());
			shopDetailVo.setUserId(shop.getUserId());
			shopDetailVo.setSatisfaction(shop.getSatisfaction());
			
			List<GoodVo> goodVoList=getGoodList(shop.getId());
			shopDetailVo.setGoodList(goodVoList);
			
			return shopDetailVo;
			
		}
		return shopDetailVo;
	}

	//单个商品添加
	@Override
	public boolean insertGood(GoodsDTO goodsDTO) {
		Goods good =new Goods();
		
		good.setName(goodsDTO.getName());
		good.setNumber(goodsDTO.getNumber());
		good.setShopId(goodsDTO.getShopId());
		good.setPrice(goodsDTO.getPrice());
		good.setPicture(goodsDTO.getPicture());
			
		int result=goodsMapper.insert(good);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}
	
	//批量商品添加
	@Override
	public boolean insertGoods(GoodListDTO goodListDTO) {
		List<GoodsDTO> goodsList=goodListDTO.getGoodsDTOList();
		
		if(goodsList.isEmpty()){
			return false;
		}else{
			int num=0;
			for(GoodsDTO goodsDTO:goodsList){
				Goods good=new Goods();
				good.setName(goodsDTO.getName());
				good.setNumber(goodsDTO.getNumber());
				good.setPicture(goodsDTO.getPicture());
				good.setPrice(goodsDTO.getPrice());
				good.setShopId(goodsDTO.getShopId());
				
				int result=goodsMapper.insert(good);
				num=num+result;
			}
			if(num==goodsList.size()){
				return true;
			}else{
				return false;
			}
		}
	}
	
	//插入商品图片
	public PictureResult insertGoodPicture(MultipartFile uploadFile,Integer shopId){
		if(null==uploadFile || uploadFile.isEmpty()){
			return PictureResult.error("上传图片为空");
		}
		
		String originalFilename=uploadFile.getOriginalFilename();
		String ext=originalFilename.substring(originalFilename.lastIndexOf("."));
		String imageName=IDUtils.genImageName();
		
		String filePath="/shop"+shopId+"/goods";
		try {
			SFTPUtil sftp = new SFTPUtil(FTP_USERNAME, FTP_PASSWORD, FTP_ADDRESS, FTP_PORT);  
			sftp.login();
			sftp.upload(FTP_BASE_PATH, filePath, imageName+ext, uploadFile.getInputStream());
			sftp.logout();  
			
			/*FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
					FTP_BASE_PATH, filePath, imageName + ext, uploadFile.getInputStream());*/
		} catch (Exception e) {
			return PictureResult.error(ExceptionUtil.getStackTrace(e));
		}
		
		String url=IMAGE_BASE_URL + filePath + "/" + imageName + ext;
		
		return PictureResult.ok(url);
	}

	@Override
	public boolean updateGood(GoodsDTO goodsDTO) {
		
		Goods good =new Goods();
		
		good.setId(goodsDTO.getId());
		good.setName(goodsDTO.getName());
		good.setNumber(goodsDTO.getNumber());
		good.setPrice(goodsDTO.getPrice());
		good.setPicture(goodsDTO.getPicture());
		
		int result=goodsMapper.updateByPrimaryKeySelective(good);
		if(result==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateGoods(GoodListDTO goodListDTO) {
		List<GoodsDTO> goodsList=goodListDTO.getGoodsDTOList();
	
		if(goodsList.isEmpty()){
			return false;
		}else{
			int num=0;
			for(GoodsDTO goodsDTO:goodsList){
				Goods good=new Goods();
				good.setName(goodsDTO.getName());
				good.setNumber(goodsDTO.getNumber());
				good.setPicture(goodsDTO.getPicture());
				good.setPrice(goodsDTO.getPrice());
				good.setId(goodsDTO.getId());
				
				int result=goodsMapper.updateByPrimaryKeySelective(good);
				num=num+result;
			}
			if(num==goodsList.size()){
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public boolean deleteGoodById(Integer goodId) {
		int result = goodsMapper.deleteByPrimaryKey(goodId);
		if(result==1){
			return true;
		}
		return false;
	}

	@Override
	public List<Goods> getGoodsByName(String goodsName, Integer shopId) {
		
		GoodsExample example=new GoodsExample();
		com.serviceMarket.pojo.GoodsExample.Criteria criteria = example.createCriteria();
		criteria.andNameLike("%"+goodsName+"%");
		criteria.andShopIdEqualTo(shopId);
		List<Goods> list=goodsMapper.selectByExample(example);
		
		return list;
	}

	@Override
	public List<ShopDetailVo> getMarGoodsByName(Integer markId, String name) {
		
		GoodsDTO goodsDTO=new GoodsDTO();
		goodsDTO.setMarkId(markId);
		goodsDTO.setName(name);
		
		List<ShopDetailVo> list=goodsMapper.getMarGoodsByName(goodsDTO);
		
		return list;
	}


}
