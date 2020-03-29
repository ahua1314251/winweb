#!/usr/bin/expect
set password [lindex $argv 0]
set timeout -1
exec cp ./target/winweb.war ./target/ROOT.war
spawn scp -o StrictHostKeyChecking=no  -P 22 -r ./target/ROOT.war admin@106.54.255.63:/home/admin/apache-tomcat-8.5.47
expect {
 "(yes/no)?" {
   send "yes\n"
   expect "*assword:" { send "$password\n"}
  }
  "*assword:" {
   send "$password\n"
  }
}
expect eof

#!/usr/bin/expect
spawn ssh admin@106.54.255.63
expect {
        "*yes/no" { send "yes\r"; exp_continue }
        "*password:" { send "$password\r"; exp_continue }
        "]*" {send "rm -rf /home/admin/apache-tomcat-8.5.47/webapps/ROOT && cp /home/admin/apache-tomcat-8.5.47/ROOT.war  /home/admin/apache-tomcat-8.5.47/webapps/ROOT.war\r" ;}

    }

expect   "]*" {send "exit\r"}

expect eof
