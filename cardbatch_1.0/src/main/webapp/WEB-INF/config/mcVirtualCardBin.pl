#!/usr/bin/perl
$uname="OPS\$YUFU_CHINA_PS_RUN/cortex\@cortex";
$kabinfileinfo="/home/mbatch/apache-tomcat-6.0.39/webapps/housekeep/WEB-INF/config/mcVirtualCardBinFile";
if(@ARGV > 1){
   $kabinfileinfo=$ARGV[0];
}
system("sqlplus -S '$uname' <<!!
                        set head off
                        set feedback off
                        set linesize 300
                        spool $kabinfileinfo
                        select f.iid||'|'||p.descr from crdformat f,crdproduct p where f.id=p.crdformat_id and p.usrdata1='VCN';
                        spool off
!!");
