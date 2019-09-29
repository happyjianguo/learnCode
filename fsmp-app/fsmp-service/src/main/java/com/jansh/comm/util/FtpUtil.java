/**
 * FtpUtil.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:端木英男 2016年6月22日
 */
package com.jansh.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FTP上传文件
 * @author 端木英男
 * @version 1.0
 */
public class FtpUtil {
    
    private static final Logger logger = LogManager.getLogger(FtpUtil.class);
    
    private static FTPClient ftp;

    public static FTPClient ftp_conn(String server, String user, String password) {
        ftp = new FTPClient();
        //ftp.setDefaultTimeout(20000);
        ftp.setDataTimeout(900000);
        try {
            int reply;
            ftp.connect(server);
            // ftp.connect(server,21,InetAddress.getLocalHost(),21);
            logger.info("Connected to " + server + ".");
            logger.info(ftp.getReplyString());

            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                logger.info("FTP server refused connection.");
                return null;
            } else {
        	ftp.login(user, password);
        	logger.info("Login success.");
                
                ftp.pasv();
                ftp.enterLocalPassiveMode();
            }
        } catch (SocketTimeoutException ste) {
            logger.error(ste);
        } catch (Exception e) {
            logger.error(e);
        }

        return ftp;
    }

    /**
     * @param ftp
     * @param remoteFile
     * @param localFile
     * @return
     * @throws FileNotFoundException
     */
    public static boolean uploadToFtp(FTPClient ftp, String remoteFile,
        String localFile) throws FileNotFoundException {
        boolean result = false;

        if (ftp == null) {
            return result;
        }

        String dir = "/";
        remoteFile = remoteFile.replaceAll("\\\\", "/");

        if (remoteFile.indexOf("/") != -1) {
            dir = (String) remoteFile.subSequence(0, remoteFile.lastIndexOf("/"));
        }

        FileInputStream fis = new FileInputStream(new File(localFile));
        logger.info("Upload " + localFile + " To " + remoteFile);

        try {
            ftp.makeDirectory(dir);
            ftp.changeWorkingDirectory(dir);
            ftp.setFileType(FTP.BINARY_FILE_TYPE); // ?BINARY??????

            if (ftp.storeFile(remoteFile, fis)) {
                result = true;
            }

            fis.close();
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    public static void logout(FTPClient ftp) {
        try {
            if (ftp != null) {
                ftp.logout();
                ftp.disconnect();
            }

            ftp = null;
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static void main(String[] args) {
        String ftp_server = "192.168.23.12";
        String ftp_user = "dmyn";
        String ftp_password = "zmxn";
        FTPClient ftp = FtpUtil.ftp_conn(ftp_server, ftp_user, ftp_password);

        String localFilename = "D:/logs/wx_server_jeewx.debug.log";

        String remoteFilename = "/wx_server_jeewx.debug.log";

        System.out.println("upload ...");
        System.out.println(localFilename + " to " + remoteFilename);

        try {
            FtpUtil.uploadToFtp(ftp, remoteFilename, localFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}

