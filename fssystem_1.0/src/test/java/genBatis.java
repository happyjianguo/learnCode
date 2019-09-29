
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;


public class genBatis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> warnings = new ArrayList<String>();
		// 覆盖已有的重名文件
		boolean overwrite = true;
		// 准备 配置文件
		File configFile = new File("E:/InstallJavaWorkPlace/fssystem_1.0_zonghe/src/main/resources/generatorConfig.xml");
		
		// 1.创建 配置解析器
		ConfigurationParser parser = new ConfigurationParser(warnings);
		// 2.获取 配置信息
		Configuration config;
		try {
			config = parser.parseConfiguration(configFile);
			// 3.创建 默认命令解释调回器
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			// 4.创建 mybatis的生成器
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			// 5.执行，关闭生成器
			myBatisGenerator.generate(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
