FROM ubuntu:18.04
MAINTAINER RenFei <i@renfei.net>

RUN set -ex; \
	apt-get update; \
	if ! which openjdk-8-jdk; then \
		apt-get install -y --no-install-recommends openjdk-8-jdk; \
	fi; \
	if ! which dnsutils; then \
    	apt-get install -y --no-install-recommends dnsutils; \
    fi; \
    if ! which ntp; then \
            apt-get install -y --no-install-recommends ntp; \
    fi; \
    if ! which whois; then \
        apt-get install -y --no-install-recommends whois; \
    fi; \
    if ! which curl; then \
    		apt-get install -y --no-install-recommends curl; \
    fi; \
    rm -rf /var/lib/apt/lists/*

RUN mkdir -p /opt/RenFeiNet/log;

COPY target/RENFEI.NET.jar /app/

HEALTHCHECK --interval=5s --timeout=5s CMD curl -f http://localhost:8099/HealthCheck || exit 1
#无奈：-Xms不等于-Xmx是由于我的内存实在不够用，生产项目请设置成一致大小，减少触发GC回收
ENTRYPOINT ["java","-Xms128M","-Xmx1024M","-XX:+UseCompressedOops","-XX:+UseConcMarkSweepGC","-XX:SoftRefLRUPolicyMSPerMB=50","-Dfile.encoding=UTF-8","-Xverify:none","-Ddruid.mysql.usePingMethod=false","-jar","/app/RENFEI.NET.jar"]

EXPOSE 8099