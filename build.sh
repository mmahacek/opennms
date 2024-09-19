#!/bin/sh

RELEASE=`.circleci/scripts/pom2version.sh pom.xml`
ROOT=`pwd`
if [ ! -e $ROOT/build.sh ]; then
  echo "Invalid root" && exit 1
fi

./target/opennms/bin/opennms stop
rm -rf ./target
rm -rf ./features/minion/container/karaf/target
ulimit -n 20480
#./clean.pl && ./compile.pl -DskipTests=true && ./assemble.pl -DskipTests=true
./compile.pl -DskipTests=true && ./assemble.pl -DskipTests=true

rm -rf ./target/opennms-$RELEASE ./target/opennms
mkdir ./target/opennms-$RELEASE
ln -s $ROOT/target/opennms-$RELEASE $ROOT/target/opennms
tar zxvf ./target/opennms-$RELEASE.tar.gz -C $ROOT/target/opennms-$RELEASE
#rm -r target/opennms/jetty-webapps/opennms/assets/
#ln -s $ROOT/core/web-assets/target/dist/assets  $ROOT/target/opennms/jetty-webapps/opennms/assets
echo "RUNAS=$(id -u -n)" > "target/opennms/etc/opennms.conf"
./target/opennms/bin/runjava -s
./target/opennms/bin/install -dis
./target/opennms/bin/opennms -t start
