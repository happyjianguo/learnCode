#!/usr/bin/perl
$uname="OPS\$YUFU_CHINA_PS_RUN/cortex\@cortex";
$kabinfileinfo="/home/mbatch/apache-tomcat-6.0.39/webapps/housekeep/WEB-INF/config/kabinfile";
if(@ARGV > 1){
   $kabinfileinfo=$ARGV[0];
}
system("sqlplus -S '$uname' <<!!
                        set head off
                        set feedback off
                        set linesize 300
                        spool $kabinfileinfo
                        select iid||'|'||descr  from crdrouting;
                        spool off
!!");
