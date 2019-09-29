<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"  indent="yes" encoding="UTF-8"/>
    <xsl:param name="yPos"/><!-- 初始页面高度, 根据所在页数调整 -->
    <xsl:param name="seq" /><!-- 单据序列号 -->  
    <xsl:param name="merName" /><!-- 商户名称  -->  
    <xsl:param name="stlDate" /><!-- -结算帐期 -->  
    <xsl:param name="consumAmt" /><!-- 算法-->  
    <xsl:param name="consumAmt2" /><!-- 算法-->  
    <!-- <xsl:param name="fee" /> --><!--  手续费 -->  
    <xsl:param name="tranAmt" /><!-- 实际付款金额 -->  
    <xsl:param name="tranAmtToUpperCase" /><!-- 人民币大写 -->  
    <xsl:param name="createYear" /><!-- 实际付款金额 -->  
    <xsl:param name="createMonth" /><!-- 实际付款金额 -->  
    <xsl:param name="chuna" /><!-- 出纳 -->  
    <xsl:param name="shenhe" /><!-- 审核 -->  
    <xsl:param name="zhidan" /><!-- 制单 -->  
    
    <xsl:template match="/">
        <div style="position:absolute;top:{82+$yPos}pt;left:330pt">
            <font style="font-size:20px"><xsl:value-of select="$createYear"/></font><!--制表时间 -->
        </div>
        <div style="position:absolute;top:{82+$yPos}pt;left:390pt">
            <font style="font-size:20px"><xsl:value-of select="$createMonth"/></font><!--制表时间 -->
        </div>
        <div style="position:absolute;top:{82+$yPos}pt;left:450pt">
            <font style="font-size:20px"><xsl:value-of select="createDate"/></font><!--制表时间 -->
        </div>        
        <div style="position:absolute;width:600px;height:10px;top:{138+$yPos}pt;left:200pt;">
            <font style="font-size:20px"><xsl:value-of select="$merName"/></font><!-- 商户名称 -->
        </div>        
        <div style="position:absolute;width:240px;height:10px;top:{138+$yPos}pt;left:550pt;">
            <font style="font-size:20px"><xsl:value-of select="$stlDate"/></font><!--结算帐期  -->
        </div>         
        <div style="position:absolute;width:500px;height:20px;top:{184+$yPos}pt;left:200pt;">
            <font style="font-size:20px"><xsl:value-of select="$consumAmt"/><br /><!-- 算法 -->
            <xsl:value-of select="$consumAmt2"/></font><!-- 算法 -->
        </div>
        <div style="position:absolute;width:500px;height:10px;top:{250+$yPos}pt;left:220pt;">
            <font style="font-size:20px"><xsl:value-of select="$tranAmtToUpperCase"/></font><!-- 人民币大写 -->
        </div>  
        <div style="position:absolute;width:240px;height:10px;top:{250+$yPos}pt;left:600pt;">
            <font style="font-size:20px"><xsl:value-of select="$tranAmt"/></font><!-- 实际付款金额 -->
        </div>          
        <div style="position:absolute;width:240px;height:10px;top:{380+$yPos}pt;left:450pt;">
            <font style="font-size:20px"><xsl:value-of select="$chuna"/></font><!-- 出纳-->
        </div>  
        <div style="position:absolute;width:240px;height:10px;top:{380+$yPos}pt;left:560pt;">
            <font style="font-size:20px"><xsl:value-of select="$shenhe"/></font><!-- 审核-->
        </div>     
        <div style="position:absolute;width:240px;height:10px;top:{380+$yPos}pt;left:660pt;">
            <font style="font-size:20px"><xsl:value-of select="$zhidan"/></font><!-- 制单-->
        </div>    
      </xsl:template>
</xsl:stylesheet>