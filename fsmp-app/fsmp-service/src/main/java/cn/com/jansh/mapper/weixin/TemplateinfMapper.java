package cn.com.jansh.mapper.weixin;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.jansh.entity.weixin.TemplateinfEntity;


/**
 * 
 * @author hjm
 * 
 */
@Repository("templateinfMapper")
public interface TemplateinfMapper {
	public void insert(TemplateinfEntity templateinf);
	
	public List<TemplateinfEntity> query();
}
