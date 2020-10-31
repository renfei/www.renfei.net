FROM ubuntu:focal
MAINTAINER RenFei <i@renfei.net>

RUN groupadd -r renfei && useradd -r -g renfei renfei

RUN set -ex; \
	apt-get update; \
	if ! which openjdk-8-jdk; then \
		apt-get install -y --no-install-recommends openjdk-8-jdk; \
	fi; \
	if ! which dnsutils; then \
    	apt-get install -y --no-install-recommends dnsutils; \
    fi; \
    if ! which whois; then \
        apt-get install -y --no-install-recommends whois; \
    fi; \
    rm -rf /var/lib/apt/lists/*

RUN mkdir -p /opt/RenFeiNet/log; \
    chown -R renfei:renfei /opt/RenFeiNet /opt/RenFeiNet/log;

VOLUME /opt/RenFeiNet

COPY ./target/RENFEI.NET.jar /opt/RenFeiNet/

ENTRYPOINT ["sudo","-u","renfei","java","-jar","-ea -Xms128m -Xmx512m -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:SoftRefLRUPolicyMSPerMB=50 -Dfile.encoding=UTF-8 -Xverify:none","/opt/RenFeiNet/RENFEI.NET.jar"]

EXPOSE 8099