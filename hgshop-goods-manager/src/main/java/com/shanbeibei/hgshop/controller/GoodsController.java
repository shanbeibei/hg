package com.shanbeibei.hgshop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.pojo.Brand;
import com.shanbeibei.hgshop.pojo.Sku;
import com.shanbeibei.hgshop.pojo.Spec;
import com.shanbeibei.hgshop.pojo.SpecOption;
import com.shanbeibei.hgshop.pojo.Spu;
import com.shanbeibei.hgshop.pojo.SpuVo;
import com.shanbeibei.hgshop.service.GoodsService;
import com.shanbeibei.hgshop.service.SpecService;

	@Controller
	@RequestMapping("goods")
public class GoodsController {
		
		@Reference
		GoodsService goodService;
		

		@Reference
		SpecService specService;
		
		/**
		 * 
		 * @param request
		 * @param page  ҳ��
		 * @param spuVo ��ѯ����
		 * @return
		 */
		@RequestMapping("list")
		public String list(HttpServletRequest request ,
				@RequestParam (defaultValue="1") int page,
				SpuVo spuVo) {
			PageInfo<Spu> listSpu = goodService.listSpu(page, spuVo);
			request.setAttribute("pageInfo", listSpu);
			return "goods/list";
		}
		
		@RequestMapping("toadd")
		public String toadd(HttpServletRequest request ) {
			List<Brand> allBrands = goodService.getAllBrands();
			request.setAttribute("brands", allBrands);
			return "goods/add";
		}
		
		
		@RequestMapping("add")
		@ResponseBody
		public String add(HttpServletRequest request,Spu spu,@RequestParam(value="file") MultipartFile file ) throws IllegalStateException, IOException {
			
			/**
			 * ���ص��ϴ��ļ���ŵ������ַ
			 */
			String filePath=processFile(file);
			// ���Ը��� �����ַ����
			spu.setSmallPic(filePath);
			
			int result = goodService.addSpu(spu);
			
			return result>0?"success":"failed";
			
		}
		
		/**
		 * 
		 * @param response
		 * @param file
		 * @throws FileNotFoundException 
		 */
		@RequestMapping("down")
		public void downLoad(HttpServletResponse response, String filename) throws FileNotFoundException {
			 /* // ���ر����ļ�
		    String fileName = "Operator.doc".toString(); // �ļ���Ĭ�ϱ�����
	*/	    // ��������
		    InputStream inStream = new FileInputStream("d:\\pic\\"+filename);// �ļ��Ĵ��·��
		    // ��������ĸ�ʽ
		    response.reset();
		    response.setContentType("bin");
		    response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		   
		    // ѭ��ȡ�����е�����
		    byte[] b = new byte[1024];
		    int len;
		    try {
		      while ((len = inStream.read(b)) > 0)
		        response.getOutputStream().write(b, 0, len);
		      inStream.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		
		}
		
		/**
		 *  �ϴ��ļ�
		 * @param file
		 * @return
		 * @throws IllegalStateException
		 * @throws IOException
		 */
		private String processFile(MultipartFile file) throws IllegalStateException, IOException {

			// ԭ�����ļ�����
			System.out.println("file.isEmpty() :" + file.isEmpty()  );
			System.out.println("file.name :" + file.getOriginalFilename());
			
			if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
				return "";
			}
				
			String originName = file.getOriginalFilename();
			String suffixName = originName.substring(originName.lastIndexOf('.'));
			SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
			String path = "d:/pic/" + sdf.format(new Date());
			File pathFile = new File(path);
			if(!pathFile.exists()) {
				pathFile.mkdir();
			}
			String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
			File distFile = new File( destFileName);
			file.transferTo(distFile);//�ļ���浽���Ŀ¼�±�
			return destFileName.substring(7);
			
			
		}
		
		@RequestMapping("skulist")
		public String skulist(HttpServletRequest request ,
				@RequestParam (defaultValue="1") int page,Sku sku) {
			PageInfo<Sku> listSku = goodService.listSku(page, sku);
			request.setAttribute("pageInfo", listSku);
			return "sku/list";
		}
		
		@RequestMapping("skuDetail")
		public String skulist(HttpServletRequest request ,int id) {
			Sku sku = goodService.getSku(id);
			System.err.println(sku);
			request.setAttribute("sku", sku);
			return "sku/detail";
		}
		
		/**
		 * ��ת��sku���ҳ��
		 * @param request
		 * @param id
		 * @return
		 */
		@RequestMapping("toaddSku")
		public String toaddSku(HttpServletRequest request ,int spuId) {
			// ��ȡҪ��ӵ���Ʒ
			Spu spu = goodService.getSpu(spuId);
			request.setAttribute("spu", spu);
			
		/*	// ��ȡ���е�Ʒ��
			List<Brand> brands = goodService.getAllBrands();
			request.setAttribute("brands", brands);*/
			
			// ��������
			List<Spec> list = specService.listAll();
			System.out.println("list is " + list);
			request.setAttribute("specs", list);
			
			return "sku/add";
		}
		
		@RequestMapping("addSku")
		@ResponseBody
		public String addSku(HttpServletRequest request ,Sku sku,int[] specIds,@RequestParam(value="specOptionIds") int[] specOptionIds) {
			// �����sku�����е������Լ�����ֵ
			List<SpecOption> specs = new ArrayList<SpecOption>();
			
			System.out.println("specIds + " + specIds.length + " and specOptionIds is " + specOptionIds.length);
			
			
			for (int i = 0; i < specIds.length && i < specOptionIds.length; i++) {
				int j = specIds[i];
				SpecOption specOption = new SpecOption();
				//���Ե�id
				specOption.setSpecId(specIds[i]);
				// ���������ֵ ��id
				specOption.setId(specOptionIds[i]);
				specs.add(specOption);
			}
			//������Ե�����
			sku.setSpecs(specs);
			int addSku = goodService.addSku(sku);
			
			return addSku>0?"success":"failed";
		}

}
