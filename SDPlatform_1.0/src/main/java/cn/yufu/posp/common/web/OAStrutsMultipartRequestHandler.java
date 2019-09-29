package cn.yufu.posp.common.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.Globals;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.upload.CommonsMultipartRequestHandler;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;
import org.apache.struts.upload.MultipartRequestWrapper;

public class OAStrutsMultipartRequestHandler  extends CommonsMultipartRequestHandler {
	 /**
     * The combined text and file request parameters.
     */
    private Hashtable elementsAll;


    /**
     * The file request parameters.
     */
    private Hashtable elementsFile;


    /**
     * The text request parameters.
     */
    private Hashtable elementsText;
	 /**
     * Parses the input stream and partitions the parsed items into a set of
     * form fields and a set of file items. In the process, the parsed items
     * are translated from Commons FileUpload <code>FileItem</code> instances
     * to Struts <code>FormFile</code> instances.
     *
     * @param request The multipart request to be processed.
     *
     * @throws ServletException if an unrecoverable error occurs.
     */
    @Override
	public void handleRequest(HttpServletRequest request)
            throws ServletException {
    	
    	System.out.println("调用我了"+request.getRequestURI()+(new Date()));
		
        // Get the app config for the current request.
        ModuleConfig ac = (ModuleConfig) request.getAttribute(
                Globals.MODULE_KEY);
        System.out.println("strutsMyltipart 00000222"+request.getRequestURI()+(new Date()));
		
        // Create and configure a DIskFileUpload instance.
        ServletFileUpload upload = new ServletFileUpload();
        // The following line is to support an "EncodingFilter"
        // see http://nagoya.apache.org/bugzilla/show_bug.cgi?id=23255
        System.out.println("strutsMyltipart 00000333"+request.getRequestURI()+(new Date()));
		
        upload.setHeaderEncoding(request.getCharacterEncoding());
        System.out.println("strutsMyltipart 00000444"+request.getRequestURI()+(new Date()));
		
        // Set the maximum size before a FileUploadException will be thrown.
        upload.setSizeMax(getSizeMax(ac));
        System.out.println("strutsMyltipart 00000555"+request.getRequestURI()+(new Date()));
        DiskFileItemFactory dfif=new DiskFileItemFactory();
        // Set the maximum size that will be stored in memory.
        dfif.setSizeThreshold((int) getSizeThreshold(ac));
        // Set the the location for saving data on disk.
        System.out.println("strutsMyltipart 00000666"+request.getRequestURI()+(new Date()));
        upload.setFileItemFactory(dfif);
        //dfif.setRepositoryPath(getRepositoryPath(ac));
        System.out.println("strutsMyltipart 00000777"+request.getRequestURI()+(new Date()));
		
        // Create the hash tables to be populated.
        elementsText = new Hashtable();
        elementsFile = new Hashtable();
        elementsAll = new Hashtable();

        // Parse the request into file items.
        List items = null;
        try {
        	
            items = upload.parseRequest(request);
        } catch (ServletFileUpload.SizeLimitExceededException e) {
            // Special handling for uploads that are too big.
            request.setAttribute(
                    MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED,
                    Boolean.TRUE);
            return;
        } catch (FileUploadException e) {
            log.error("Failed to parse multipart request", e);
            throw new ServletException(e);
        }
        System.out.println("strutsMyltipart 000001888"+request.getRequestURI()+(new Date()));
		
        // Partition the items into form fields and files.
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();

            if (item.isFormField()) {
                addTextParameter(request, item);
            } else {
                addFileParameter(item);
            }
        }
        System.out.println("strutsMyltipart 000001999"+request.getRequestURI()+(new Date()));
		
    }
    /**
     * Adds a regular text parameter to the set of text parameters for this
     * request and also to the list of all parameters. Handles the case of
     * multiple values for the same parameter by using an array for the
     * parameter value.
     *
     * @param request The request in which the parameter was specified.
     * @param item    The file item for the parameter to add.
     */
    @Override
	protected void addTextParameter(HttpServletRequest request, FileItem item) {
        String name = item.getFieldName();
        String value = null;
        boolean haveValue = false;
        String encoding = request.getCharacterEncoding();
        System.out.println("1111");
        if (encoding != null) {
            try {
                value = item.getString(encoding);
                haveValue = true;
            } catch (Exception e) {
                // Handled below, since haveValue is false.
            }
        }
        System.out.println("2222");
        if (!haveValue) {
            try {
                 value = item.getString("ISO-8859-1");
            } catch (java.io.UnsupportedEncodingException uee) {
                 value = item.getString();
            }
            haveValue = true;
        }
        System.out.println("3333");
        if (request instanceof MultipartRequestWrapper) {
            MultipartRequestWrapper wrapper = (MultipartRequestWrapper) request;
            wrapper.setParameter(name, value);
        }
        System.out.println("4444");
        String[] oldArray = (String[]) elementsText.get(name);
        String[] newArray;

        if (oldArray != null) {
            newArray = new String[oldArray.length + 1];
            System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
            newArray[oldArray.length] = value;
        } else {
            newArray = new String[] { value };
        }
        System.out.println("5555");
        elementsText.put(name, newArray);
        elementsAll.put(name, newArray);
    }
    /**
     * Returns a hash table containing both text and file request parameters.
     *
     * @return The text and file request parameters.
     */
    @Override
	public Hashtable getAllElements() {
        return this.elementsAll;
    }
    /**
     * Adds a file parameter to the set of file parameters for this request
     * and also to the list of all parameters.
     *
     * @param item    The file item for the parameter to add.
     */
    @Override
	protected void addFileParameter(FileItem item) {
        FormFile formFile = new CommonsFormFile(item);

        elementsFile.put(item.getFieldName(), formFile);
        elementsAll.put(item.getFieldName(), formFile);
    }
    // ---------------------------------------------------------- Inner Classes


    /**
     * This class implements the Struts <code>FormFile</code> interface by
     * wrapping the Commons FileUpload <code>FileItem</code> interface. This
     * implementation is <i>read-only</i>; any attempt to modify an instance
     * of this class will result in an <code>UnsupportedOperationException</code>.
     */
    static class CommonsFormFile implements FormFile, Serializable {

        /**
         * The <code>FileItem</code> instance wrapped by this object.
         */
        FileItem fileItem;


        /**
         * Constructs an instance of this class which wraps the supplied
         * file item.
         *
         * @param fileItem The Commons file item to be wrapped.
         */
        public CommonsFormFile(FileItem fileItem) {
            this.fileItem = fileItem;
        }


        /**
         * Returns the content type for this file.
         *
         * @return A String representing content type.
         */
        public String getContentType() {
            return fileItem.getContentType();
        }


        /**
         * Sets the content type for this file.
         * <p>
         * NOTE: This method is not supported in this implementation.
         *
         * @param contentType A string representing the content type.
         */
        public void setContentType(String contentType) {
            throw new UnsupportedOperationException(
                    "The setContentType() method is not supported.");
        }


        /**
         * Returns the size, in bytes, of this file.
         *
         * @return The size of the file, in bytes.
         */
        public int getFileSize() {
            return (int)fileItem.getSize();
        }


        /**
         * Sets the size, in bytes, for this file.
         * <p>
         * NOTE: This method is not supported in this implementation.
         *
         * @param filesize The size of the file, in bytes.
         */
        public void setFileSize(int filesize) {
            throw new UnsupportedOperationException(
                    "The setFileSize() method is not supported.");
        }


        /**
         * Returns the (client-side) file name for this file.
         *
         * @return The client-size file name.
         */
        public String getFileName() {
            return getBaseFileName(fileItem.getName());
        }


        /**
         * Sets the (client-side) file name for this file.
         * <p>
         * NOTE: This method is not supported in this implementation.
         *
         * @param fileName The client-side name for the file.
         */
        public void setFileName(String fileName) {
            throw new UnsupportedOperationException(
                    "The setFileName() method is not supported.");
        }


        /**
         * Returns the data for this file as a byte array. Note that this may
         * result in excessive memory usage for large uploads. The use of the
         * {@link #getInputStream() getInputStream} method is encouraged
         * as an alternative.
         *
         * @return An array of bytes representing the data contained in this
         *         form file.
         *
         * @exception FileNotFoundException If some sort of file representation
         *                                  cannot be found for the FormFile
         * @exception IOException If there is some sort of IOException
         */
        public byte[] getFileData() throws FileNotFoundException, IOException {
            return fileItem.get();
        }


        /**
         * Get an InputStream that represents this file.  This is the preferred
         * method of getting file data.
         * @exception FileNotFoundException If some sort of file representation
         *                                  cannot be found for the FormFile
         * @exception IOException If there is some sort of IOException
         */
        public InputStream getInputStream() throws FileNotFoundException, IOException {
            return fileItem.getInputStream();
        }


        /**
         * Destroy all content for this form file.
         * Implementations should remove any temporary
         * files or any temporary file data stored somewhere
         */
        public void destroy() {
            fileItem.delete();
        }


        /**
         * Returns the base file name from the supplied file path. On the surface,
         * this would appear to be a trivial task. Apparently, however, some Linux
         * JDKs do not implement <code>File.getName()</code> correctly for Windows
         * paths, so we attempt to take care of that here.
         *
         * @param filePath The full path to the file.
         *
         * @return The base file name, from the end of the path.
         */
        protected String getBaseFileName(String filePath) {

            // First, ask the JDK for the base file name.
            String fileName = new File(filePath).getName();

            // Now check for a Windows file name parsed incorrectly.
            int colonIndex = fileName.indexOf(":");
            if (colonIndex == -1) {
                // Check for a Windows SMB file path.
                colonIndex = fileName.indexOf("\\\\");
            }
            int backslashIndex = fileName.lastIndexOf("\\");

            if (colonIndex > -1 && backslashIndex > -1) {
                // Consider this filename to be a full Windows path, and parse it
                // accordingly to retrieve just the base file name.
                fileName = fileName.substring(backslashIndex + 1);
            }

            return fileName;
        }

        /**
         * Returns the (client-side) file name for this file.
         *
         * @return The client-size file name.
         */
        @Override
		public String toString() {
            return getFileName();
        }
    }
}
