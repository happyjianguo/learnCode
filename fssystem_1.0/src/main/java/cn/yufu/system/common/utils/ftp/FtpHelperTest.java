package cn.yufu.system.common.utils.ftp;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.yufu.system.common.utils.DateUtils;

/**
 *
 * @author xxj
 */
public class FtpHelperTest {
	FtpHelper ftp = null;

	@Before
	public void InitBinder() {
		ftp = FtpHelper.getInstance();
		ftp.login("localhost", 21);// 匿名登录
	}

	@After
	public void finish() {
		ftp.close();
	}

	@Test
	public void testUplodFileStream() {

		try {
			String localFile = "D:/Temp/resource/img.jpg";
			String ftpDir = "temp";
			String ftpFile = StringExtend.format("img-{0}.jpg",
					DateUtils.getDate("yyyyMMddHHmmss"));

			FileInputStream fi = new FileInputStream(localFile);

			boolean success = ftp.uploadFile(fi, ftpDir, ftpFile);
			assertTrue(success);

			localFile = "D:/Temp/resource/文本.txt";
			ftpDir = "temp";
			ftpFile = StringExtend.format("文本-{0}.txt",
					DateUtils.getDate("yyyyMMddHHmmss"));

			fi = new FileInputStream(localFile);
			success = ftp.uploadFile(fi, ftpDir, ftpFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOnAfter() {
		String localFile="D:/Temp/resource/img.jpg";
		String ftpDir="/aa/bb";
		String ftpFile=StringExtend.format("img-{0}.jpg"
				, DateUtils.getDate("yyyyMMddHHmmss"));	


		// 上传后事件
//		ftp.onUploadFileAfter = (ftpFileInfo) -> {
//			try(FileInputStream fstream = new FileInputStream(new File(ftpFileInfo.getSrcFile()))) {
//				ByteArrayOutputStream bstream = new ByteArrayOutputStream();
//				byte[] buffer = new byte[1000];
//				int n = 0;
//				while ((n = fstream.read(buffer)) != -1) {
//					bstream.write(buffer, 0, n);
//				}
//				System.out.println("onafter:stream lenth = "+bstream.size());
//				bstream.close();
//				fstream.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//
//			return true;
//		};
		//上传
		boolean success = ftp.uploadFile(localFile,ftpDir, ftpFile);
		assertTrue(success);
		
	}

	@Test
	public void testUplodaFile() {
		String localFile = "D:/Temp/resource/img.jpg";
		String ftpDir = "/aa/bb";
		String ftpFile = StringExtend.format("img-{0}.jpg",
				DateUtils.getDate("yyyyMMddHHmmss"));
		boolean success = ftp.uploadFile(localFile, ftpDir, ftpFile);
		assertTrue(success);

		localFile = "D:/Temp/resource/text.txt";
		ftpDir = "/aa/bb/c1";
		ftpFile = StringExtend.format("text-{0}.txt",
				DateUtils.getDate("yyyyMMddHHmmss"));
		success = ftp.uploadFile(localFile, ftpDir, ftpFile);
		assertTrue(success);

		localFile = "D:/Temp/resource/文本.txt";
		ftpDir = "/aa/bb/c2";
		ftpFile = StringExtend.format("文本-{0}.txt",
				DateUtils.getDate("yyyyMMddHHmmss"));
		success = ftp.uploadFile(localFile, ftpDir, ftpFile);
		assertTrue(success);

	}

	@Test
	public void testRemove() {
		boolean success = false;
		String file = "/temp/文本-20170509175507.txt";
		success = ftp.removeFile(file);

		assertTrue(success);
	}

	@Test
	public void crateDir() {
		String dir = "aa/bb";
		boolean success = ftp.createDir(dir);
		System.out.print(success ? "成功" : "失败");
		System.out.println(dir);

		dir = "aa/bb/c1";
		success = ftp.createDir(dir);
		System.out.print(success ? "成功" : "失败");
		System.out.println(dir);

		dir = "aa/bb/c2";
		success = ftp.createDir(dir);
		System.out.print(success ? "成功" : "失败");
		System.out.println(dir);

		assertTrue(success);
	}

	@Test
	public void removeDir() {
		String dir = "aa/bb";
		boolean success = ftp.removeDir(dir);
		System.out.print(success ? "成功" : "失败");
		System.out.println(dir);

		dir = "aa/bb/c1";
		success = ftp.removeDir(dir);
		System.out.print(success ? "成功" : "失败");
		System.out.println(dir);

		dir = "aa/bb/c2";
		success = ftp.removeDir(dir);
		System.out.print(success ? "成功" : "失败");
		System.out.println(dir);

		assertTrue(success);
	}

	@Test
	public void downloadFile() {
		String ftpDirName = "/aa/bb";
		String ftpFileName = "img-20170606110355.jpg";
		String localFileFullName = StringExtend.format(
				"D:/Temp/pdf/down/down-{0}.jpg",
				DateUtils.getDate("yyyyMMddHHmmss"));
		boolean result = ftp.downloadFile(ftpDirName, ftpFileName,
				localFileFullName);
		assertTrue(result);

		ftpDirName = "/";
		ftpFileName = "文本.txt";
		localFileFullName = StringExtend.format(
				"D:/Temp/pdf/down/down-{0}.txt",
				DateUtils.getDate("yyyyMMddHHmmss"));
		result = ftp.downloadFile(ftpDirName, ftpFileName, localFileFullName);
		assertTrue(result);
	}
}